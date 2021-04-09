import { Component, Vue, Inject } from 'vue-property-decorator';

import { IJfTarget } from '@/shared/model/jf-target.model';
import JfTargetService from './jf-target.service';

@Component
export default class JfTargetDetails extends Vue {
  @Inject('jfTargetService') private jfTargetService: () => JfTargetService;
  public jfTarget: IJfTarget = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.jfTargetId) {
        vm.retrieveJfTarget(to.params.jfTargetId);
      }
    });
  }

  public retrieveJfTarget(jfTargetId) {
    this.jfTargetService()
      .find(jfTargetId)
      .then(res => {
        this.jfTarget = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
