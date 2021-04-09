import { Component, Vue, Inject } from 'vue-property-decorator';

import { IJfWorkDetails } from '@/shared/model/jf-work-details.model';
import JfWorkDetailsService from './jf-work-details.service';

@Component
export default class JfWorkDetailsDetails extends Vue {
  @Inject('jfWorkDetailsService') private jfWorkDetailsService: () => JfWorkDetailsService;
  public jfWorkDetails: IJfWorkDetails = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.jfWorkDetailsId) {
        vm.retrieveJfWorkDetails(to.params.jfWorkDetailsId);
      }
    });
  }

  public retrieveJfWorkDetails(jfWorkDetailsId) {
    this.jfWorkDetailsService()
      .find(jfWorkDetailsId)
      .then(res => {
        this.jfWorkDetails = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
