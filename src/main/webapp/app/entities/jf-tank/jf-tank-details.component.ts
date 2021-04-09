import { Component, Vue, Inject } from 'vue-property-decorator';

import { IJfTank } from '@/shared/model/jf-tank.model';
import JfTankService from './jf-tank.service';

@Component
export default class JfTankDetails extends Vue {
  @Inject('jfTankService') private jfTankService: () => JfTankService;
  public jfTank: IJfTank = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.jfTankId) {
        vm.retrieveJfTank(to.params.jfTankId);
      }
    });
  }

  public retrieveJfTank(jfTankId) {
    this.jfTankService()
      .find(jfTankId)
      .then(res => {
        this.jfTank = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
