import { Component, Vue, Inject } from 'vue-property-decorator';

import { maxLength } from 'vuelidate/lib/validators';

import { IJfCustomer, JfCustomer } from '@/shared/model/jf-customer.model';
import JfCustomerService from './jf-customer.service';

const validations: any = {
  jfCustomer: {
    name: {
      maxLength: maxLength(50),
    },
  },
};

@Component({
  validations,
})
export default class JfCustomerUpdate extends Vue {
  @Inject('jfCustomerService') private jfCustomerService: () => JfCustomerService;
  public jfCustomer: IJfCustomer = new JfCustomer();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.jfCustomerId) {
        vm.retrieveJfCustomer(to.params.jfCustomerId);
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
    if (this.jfCustomer.id) {
      this.jfCustomerService()
        .update(this.jfCustomer)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('rzbidApp.jfCustomer.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.jfCustomerService()
        .create(this.jfCustomer)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('rzbidApp.jfCustomer.created', { param: param.id });
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

  public retrieveJfCustomer(jfCustomerId): void {
    this.jfCustomerService()
      .find(jfCustomerId)
      .then(res => {
        this.jfCustomer = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
