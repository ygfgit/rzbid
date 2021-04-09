import { Component, Vue, Inject } from 'vue-property-decorator';

import { IJfMaster } from '@/shared/model/jf-master.model';
import JfMasterService from './jf-master.service';

@Component
export default class JfMasterDetails extends Vue {
  @Inject('jfMasterService') private jfMasterService: () => JfMasterService;
  public jfMaster: IJfMaster = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.jfMasterId) {
        vm.retrieveJfMaster(to.params.jfMasterId);
      }
    });
  }

  public retrieveJfMaster(jfMasterId) {
    this.jfMasterService()
      .find(jfMasterId)
      .then(res => {
        this.jfMaster = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
