import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IJfCustomer } from '@/shared/model/jf-customer.model';

import JfCustomerService from './jf-customer.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class JfCustomer extends Vue {
  @Inject('jfCustomerService') private jfCustomerService: () => JfCustomerService;
  private removeId: number = null;

  public jfCustomers: IJfCustomer[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllJfCustomers();
  }

  public clear(): void {
    this.retrieveAllJfCustomers();
  }

  public retrieveAllJfCustomers(): void {
    this.isFetching = true;

    this.jfCustomerService()
      .retrieve()
      .then(
        res => {
          this.jfCustomers = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: IJfCustomer): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeJfCustomer(): void {
    this.jfCustomerService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('rzbidApp.jfCustomer.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllJfCustomers();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
