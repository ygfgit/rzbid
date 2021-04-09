import { Component, Vue, Inject } from 'vue-property-decorator';

import JfCargoService from '@/entities/jf-cargo/jf-cargo.service';
import { IJfCargo } from '@/shared/model/jf-cargo.model';

import JfTargetService from '@/entities/jf-target/jf-target.service';
import { IJfTarget } from '@/shared/model/jf-target.model';

import JfTankService from '@/entities/jf-tank/jf-tank.service';
import { IJfTank } from '@/shared/model/jf-tank.model';

import JfWorkDetailsService from '@/entities/jf-work-details/jf-work-details.service';
import { IJfWorkDetails } from '@/shared/model/jf-work-details.model';

import { IJfWork, JfWork } from '@/shared/model/jf-work.model';
import JfWorkService from './jf-work.service';

const validations: any = {
  jfWork: {
    hwmch: {},
    jhsl: {},
    bz: {},
  },
};

@Component({
  validations,
})
export default class JfWorkUpdate extends Vue {
  @Inject('jfWorkService') private jfWorkService: () => JfWorkService;
  public jfWork: IJfWork = new JfWork();

  @Inject('jfCargoService') private jfCargoService: () => JfCargoService;

  public jfCargos: IJfCargo[] = [];

  @Inject('jfTargetService') private jfTargetService: () => JfTargetService;

  public jfTargets: IJfTarget[] = [];

  @Inject('jfTankService') private jfTankService: () => JfTankService;

  public jfTanks: IJfTank[] = [];

  @Inject('jfWorkDetailsService') private jfWorkDetailsService: () => JfWorkDetailsService;

  public jfWorkDetails: IJfWorkDetails[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.jfWorkId) {
        vm.retrieveJfWork(to.params.jfWorkId);
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
    if (this.jfWork.id) {
      this.jfWorkService()
        .update(this.jfWork)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('rzbidApp.jfWork.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.jfWorkService()
        .create(this.jfWork)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('rzbidApp.jfWork.created', { param: param.id });
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

  public retrieveJfWork(jfWorkId): void {
    this.jfWorkService()
      .find(jfWorkId)
      .then(res => {
        this.jfWork = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.jfCargoService()
      .retrieve()
      .then(res => {
        this.jfCargos = res.data;
      });
    this.jfTargetService()
      .retrieve()
      .then(res => {
        this.jfTargets = res.data;
      });
    this.jfTankService()
      .retrieve()
      .then(res => {
        this.jfTanks = res.data;
      });
    this.jfWorkDetailsService()
      .retrieve()
      .then(res => {
        this.jfWorkDetails = res.data;
      });
  }
}
