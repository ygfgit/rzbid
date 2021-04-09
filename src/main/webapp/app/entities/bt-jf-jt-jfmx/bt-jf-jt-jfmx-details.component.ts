import { Component, Vue, Inject } from 'vue-property-decorator';

import { IBtJfJtJfmx } from '@/shared/model/bt-jf-jt-jfmx.model';
import BtJfJtJfmxService from './bt-jf-jt-jfmx.service';

@Component
export default class BtJfJtJfmxDetails extends Vue {
  @Inject('btJfJtJfmxService') private btJfJtJfmxService: () => BtJfJtJfmxService;
  public btJfJtJfmx: IBtJfJtJfmx = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.btJfJtJfmxId) {
        vm.retrieveBtJfJtJfmx(to.params.btJfJtJfmxId);
      }
    });
  }

  public retrieveBtJfJtJfmx(btJfJtJfmxId) {
    this.btJfJtJfmxService()
      .find(btJfJtJfmxId)
      .then(res => {
        this.btJfJtJfmx = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
