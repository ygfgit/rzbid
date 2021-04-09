import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IJfCargo } from '@/shared/model/jf-cargo.model';

import JfCargoService from './jf-cargo.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class JfCargo extends Vue {
  @Inject('jfCargoService') private jfCargoService: () => JfCargoService;
  private removeId: number = null;

  public jfCargos: IJfCargo[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllJfCargos();
  }

  public clear(): void {
    this.retrieveAllJfCargos();
  }

  public retrieveAllJfCargos(): void {
    this.isFetching = true;

    this.jfCargoService()
      .retrieve()
      .then(
        res => {
          this.jfCargos = res.data;
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

  public prepareRemove(instance: IJfCargo): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeJfCargo(): void {
    this.jfCargoService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('rzbidApp.jfCargo.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllJfCargos();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
