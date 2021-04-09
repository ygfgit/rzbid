import { Component, Vue, Inject } from 'vue-property-decorator';

import BtJfJtJfmxService from '@/entities/bt-jf-jt-jfmx/bt-jf-jt-jfmx.service';
import { IBtJfJtJfmx } from '@/shared/model/bt-jf-jt-jfmx.model';

import { IJfMaster, JfMaster } from '@/shared/model/jf-master.model';
import JfMasterService from './jf-master.service';

const validations: any = {
  jfMaster: {
    zygs: {},
    je: {},
    she: {},
    shhje: {},
    jmje: {},
    jmhje: {},
    idHw: {},
    htid: {},
    mb: {},
    zywtrid: {},
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
export default class JfMasterUpdate extends Vue {
  @Inject('jfMasterService') private jfMasterService: () => JfMasterService;
  public jfMaster: IJfMaster = new JfMaster();

  @Inject('btJfJtJfmxService') private btJfJtJfmxService: () => BtJfJtJfmxService;

  public btJfJtJfmxes: IBtJfJtJfmx[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.jfMasterId) {
        vm.retrieveJfMaster(to.params.jfMasterId);
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
    if (this.jfMaster.id) {
      this.jfMasterService()
        .update(this.jfMaster)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('rzbidApp.jfMaster.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.jfMasterService()
        .create(this.jfMaster)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('rzbidApp.jfMaster.created', { param: param.id });
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

  public retrieveJfMaster(jfMasterId): void {
    this.jfMasterService()
      .find(jfMasterId)
      .then(res => {
        this.jfMaster = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.btJfJtJfmxService()
      .retrieve()
      .then(res => {
        this.btJfJtJfmxes = res.data;
      });
  }
}
