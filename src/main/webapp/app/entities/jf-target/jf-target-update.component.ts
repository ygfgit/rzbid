import { Component, Vue, Inject } from 'vue-property-decorator';

import JfWorkService from '@/entities/jf-work/jf-work.service';
import { IJfWork } from '@/shared/model/jf-work.model';

import { IJfTarget, JfTarget } from '@/shared/model/jf-target.model';
import JfTargetService from './jf-target.service';

const validations: any = {
  jfTarget: {
    flzl: {},
  },
};

@Component({
  validations,
})
export default class JfTargetUpdate extends Vue {
  @Inject('jfTargetService') private jfTargetService: () => JfTargetService;
  public jfTarget: IJfTarget = new JfTarget();

  @Inject('jfWorkService') private jfWorkService: () => JfWorkService;

  public jfWorks: IJfWork[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.jfTargetId) {
        vm.retrieveJfTarget(to.params.jfTargetId);
      }
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.jfTarget.id) {
      this.jfTargetService()
        .update(this.jfTarget)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('rzbidApp.jfTarget.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.jfTargetService()
        .create(this.jfTarget)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('rzbidApp.jfTarget.created', { param: param.id });
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    }
  }

  public retrieveJfTarget(jfTargetId): void {
    this.jfTargetService()
      .find(jfTargetId)
      .then(res => {
        this.jfTarget = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.jfWorkService()
      .retrieve()
      .then(res => {
        this.jfWorks = res.data;
      });
  }
}
