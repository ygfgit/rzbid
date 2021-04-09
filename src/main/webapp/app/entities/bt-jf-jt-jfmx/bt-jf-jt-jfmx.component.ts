import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IBtJfJtJfmx } from '@/shared/model/bt-jf-jt-jfmx.model';

import BtJfJtJfmxService from './bt-jf-jt-jfmx.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class BtJfJtJfmx extends Vue {
  @Inject('btJfJtJfmxService') private btJfJtJfmxService: () => BtJfJtJfmxService;
  private removeId: number = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;

  public btJfJtJfmxes: IBtJfJtJfmx[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllBtJfJtJfmxs();
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllBtJfJtJfmxs();
  }

  public retrieveAllBtJfJtJfmxs(): void {
    this.isFetching = true;

    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort(),
    };
    this.btJfJtJfmxService()
      .retrieve(paginationQuery)
      .then(
        res => {
          this.btJfJtJfmxes = res.data;
          this.totalItems = Number(res.headers['x-total-count']);
          this.queryCount = this.totalItems;
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

  public prepareRemove(instance: IBtJfJtJfmx): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeBtJfJtJfmx(): void {
    this.btJfJtJfmxService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('rzbidApp.btJfJtJfmx.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllBtJfJtJfmxs();
        this.closeDialog();
      });
  }

  public sort(): Array<any> {
    const result = [this.propOrder + ',' + (this.reverse ? 'desc' : 'asc')];
    if (this.propOrder !== 'id') {
      result.push('id');
    }
    return result;
  }

  public loadPage(page: number): void {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  public transition(): void {
    this.retrieveAllBtJfJtJfmxs();
  }

  public changeOrder(propOrder): void {
    this.propOrder = propOrder;
    this.reverse = !this.reverse;
    this.transition();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
