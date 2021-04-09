import { Component, Vue, Inject } from 'vue-property-decorator';

import { maxLength } from 'vuelidate/lib/validators';

import { IJfCompany, JfCompany } from '@/shared/model/jf-company.model';
import JfCompanyService from './jf-company.service';

const validations: any = {
  jfCompany: {
    name: {
      maxLength: maxLength(50),
    },
  },
};

@Component({
  validations,
})
export default class JfCompanyUpdate extends Vue {
  @Inject('jfCompanyService') private jfCompanyService: () => JfCompanyService;
  public jfCompany: IJfCompany = new JfCompany();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.jfCompanyId) {
        vm.retrieveJfCompany(to.params.jfCompanyId);
      }
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
    if (this.jfCompany.id) {
      this.jfCompanyService()
        .update(this.jfCompany)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('rzbidApp.jfCompany.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.jfCompanyService()
        .create(this.jfCompany)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('rzbidApp.jfCompany.created', { param: param.id });
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

  public retrieveJfCompany(jfCompanyId): void {
    this.jfCompanyService()
      .find(jfCompanyId)
      .then(res => {
        this.jfCompany = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
