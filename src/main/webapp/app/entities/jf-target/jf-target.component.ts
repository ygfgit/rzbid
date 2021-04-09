import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IJfTarget } from '@/shared/model/jf-target.model';

import JfTargetService from './jf-target.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class JfTarget extends Vue {
  @Inject('jfTargetService') private jfTargetService: () => JfTargetService;
  private removeId: number = null;

  public jfTargets: IJfTarget[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllJfTargets();
  }

  public clear(): void {
    this.retrieveAllJfTargets();
  }

  public retrieveAllJfTargets(): void {
    this.isFetching = true;

    this.jfTargetService()
      .retrieve()
      .then(
        res => {
          this.jfTargets = res.data;
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

  public prepareRemove(instance: IJfTarget): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeJfTarget(): void {
    this.jfTargetService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('rzbidApp.jfTarget.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllJfTargets();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
