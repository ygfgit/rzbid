import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IJfTank } from '@/shared/model/jf-tank.model';

import JfTankService from './jf-tank.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class JfTank extends Vue {
  @Inject('jfTankService') private jfTankService: () => JfTankService;
  private removeId: number = null;

  public jfTanks: IJfTank[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllJfTanks();
  }

  public clear(): void {
    this.retrieveAllJfTanks();
  }

  public retrieveAllJfTanks(): void {
    this.isFetching = true;

    this.jfTankService()
      .retrieve()
      .then(
        res => {
          this.jfTanks = res.data;
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

  public prepareRemove(instance: IJfTank): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeJfTank(): void {
    this.jfTankService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('rzbidApp.jfTank.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllJfTanks();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
