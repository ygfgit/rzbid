import { Component, Vue, Inject } from 'vue-property-decorator';

import JfWorkService from '@/entities/jf-work/jf-work.service';
import { IJfWork } from '@/shared/model/jf-work.model';

import { IJfTank, JfTank } from '@/shared/model/jf-tank.model';
import JfTankService from './jf-tank.service';

const validations: any = {
  jfTank: {
    zone: {},
    code: {},
  },
};

@Component({
  validations,
})
export default class JfTankUpdate extends Vue {
  @Inject('jfTankService') private jfTankService: () => JfTankService;
  public jfTank: IJfTank = new JfTank();

  @Inject('jfWorkService') private jfWorkService: () => JfWorkService;

  public jfWorks: IJfWork[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.jfTankId) {
        vm.retrieveJfTank(to.params.jfTankId);
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
    if (this.jfTank.id) {
      this.jfTankService()
        .update(this.jfTank)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('rzbidApp.jfTank.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.jfTankService()
        .create(this.jfTank)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('rzbidApp.jfTank.created', { param: param.id });
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

  public retrieveJfTank(jfTankId): void {
    this.jfTankService()
      .find(jfTankId)
      .then(res => {
        this.jfTank = res;
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
