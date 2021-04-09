import { Component, Vue, Inject } from 'vue-property-decorator';

import JfWorkService from '@/entities/jf-work/jf-work.service';
import { IJfWork } from '@/shared/model/jf-work.model';

import { IJfCargo, JfCargo } from '@/shared/model/jf-cargo.model';
import JfCargoService from './jf-cargo.service';

const validations: any = {
  jfCargo: {
    rq: {},
    zygsdm: {},
    hth: {},
    zywtrid: {},
    zywtr: {},
    zhwchm: {},
    hwmch: {},
    gq: {},
    shl: {},
    syl: {},
  },
};

@Component({
  validations,
})
export default class JfCargoUpdate extends Vue {
  @Inject('jfCargoService') private jfCargoService: () => JfCargoService;
  public jfCargo: IJfCargo = new JfCargo();

  @Inject('jfWorkService') private jfWorkService: () => JfWorkService;

  public jfWorks: IJfWork[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.jfCargoId) {
        vm.retrieveJfCargo(to.params.jfCargoId);
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
    if (this.jfCargo.id) {
      this.jfCargoService()
        .update(this.jfCargo)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('rzbidApp.jfCargo.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.jfCargoService()
        .create(this.jfCargo)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('rzbidApp.jfCargo.created', { param: param.id });
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

  public retrieveJfCargo(jfCargoId): void {
    this.jfCargoService()
      .find(jfCargoId)
      .then(res => {
        this.jfCargo = res;
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
