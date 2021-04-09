import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IJfCompany } from '@/shared/model/jf-company.model';

import JfCompanyService from './jf-company.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class JfCompany extends Vue {
  @Inject('jfCompanyService') private jfCompanyService: () => JfCompanyService;
  private removeId: number = null;

  public jfCompanies: IJfCompany[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllJfCompanys();
  }

  public clear(): void {
    this.retrieveAllJfCompanys();
  }

  public retrieveAllJfCompanys(): void {
    this.isFetching = true;

    this.jfCompanyService()
      .retrieve()
      .then(
        res => {
          this.jfCompanies = res.data;
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

  public prepareRemove(instance: IJfCompany): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeJfCompany(): void {
    this.jfCompanyService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('rzbidApp.jfCompany.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllJfCompanys();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
