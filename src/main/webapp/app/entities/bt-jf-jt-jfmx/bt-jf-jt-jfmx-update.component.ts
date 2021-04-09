import { Component, Vue, Inject } from 'vue-property-decorator';

import JfMasterService from '@/entities/jf-master/jf-master.service';
import { IJfMaster } from '@/shared/model/jf-master.model';

import { IBtJfJtJfmx, BtJfJtJfmx } from '@/shared/model/bt-jf-jt-jfmx.model';
import BtJfJtJfmxService from './bt-jf-jt-jfmx.service';

const validations: any = {
  btJfJtJfmx: {
    zygs: {},
    zyetrid: {},
    flmch: {},
    fl: {},
    shl: {},
    je: {},
    shlv: {},
    she: {},
    shhje: {},
    jmje: {},
    startd: {},
    endd: {},
    mark1: {},
    mark2: {},
    createby: {},
    createbyid: {},
    createon: {},
    createGsid: {},
    createGsmch: {},
    createBmid: {},
    createBmm: {},
    createGwid: {},
    createGwm: {},
    modifyby: {},
    modifybyid: {},
    modifyon: {},
  },
};

@Component({
  validations,
})
export default class BtJfJtJfmxUpdate extends Vue {
  @Inject('btJfJtJfmxService') private btJfJtJfmxService: () => BtJfJtJfmxService;
  public btJfJtJfmx: IBtJfJtJfmx = new BtJfJtJfmx();

  @Inject('jfMasterService') private jfMasterService: () => JfMasterService;

  public jfMasters: IJfMaster[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.btJfJtJfmxId) {
        vm.retrieveBtJfJtJfmx(to.params.btJfJtJfmxId);
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
    if (this.btJfJtJfmx.id) {
      this.btJfJtJfmxService()
        .update(this.btJfJtJfmx)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('rzbidApp.btJfJtJfmx.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.btJfJtJfmxService()
        .create(this.btJfJtJfmx)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('rzbidApp.btJfJtJfmx.created', { param: param.id });
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

  public retrieveBtJfJtJfmx(btJfJtJfmxId): void {
    this.btJfJtJfmxService()
      .find(btJfJtJfmxId)
      .then(res => {
        this.btJfJtJfmx = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.jfMasterService()
      .retrieve()
      .then(res => {
        this.jfMasters = res.data;
      });
  }
}
