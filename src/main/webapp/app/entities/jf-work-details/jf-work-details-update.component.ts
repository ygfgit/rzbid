import { Component, Vue, Inject } from 'vue-property-decorator';

import JfWorkService from '@/entities/jf-work/jf-work.service';
import { IJfWork } from '@/shared/model/jf-work.model';

import { IJfWorkDetails, JfWorkDetails } from '@/shared/model/jf-work-details.model';
import JfWorkDetailsService from './jf-work-details.service';

const validations: any = {
  jfWorkDetails: {
    rq: {},
    lb: {},
    shl: {},
    gq: {},
    syl: {},
  },
};

@Component({
  validations,
})
export default class JfWorkDetailsUpdate extends Vue {
  @Inject('jfWorkDetailsService') private jfWorkDetailsService: () => JfWorkDetailsService;
  public jfWorkDetails: IJfWorkDetails = new JfWorkDetails();

  @Inject('jfWorkService') private jfWorkService: () => JfWorkService;

  public jfWorks: IJfWork[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.jfWorkDetailsId) {
        vm.retrieveJfWorkDetails(to.params.jfWorkDetailsId);
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
    if (this.jfWorkDetails.id) {
      this.jfWorkDetailsService()
        .update(this.jfWorkDetails)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('rzbidApp.jfWorkDetails.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.jfWorkDetailsService()
        .create(this.jfWorkDetails)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('rzbidApp.jfWorkDetails.created', { param: param.id });
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

  public retrieveJfWorkDetails(jfWorkDetailsId): void {
    this.jfWorkDetailsService()
      .find(jfWorkDetailsId)
      .then(res => {
        this.jfWorkDetails = res;
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
