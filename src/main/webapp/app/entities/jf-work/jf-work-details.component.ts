import { Component, Vue, Inject } from 'vue-property-decorator';

import { IJfWork } from '@/shared/model/jf-work.model';
import JfWorkService from './jf-work.service';

@Component
export default class JfWorkDetails extends Vue {
  @Inject('jfWorkService') private jfWorkService: () => JfWorkService;
  public jfWork: IJfWork = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.jfWorkId) {
        vm.retrieveJfWork(to.params.jfWorkId);
      }
    });
  }

  public retrieveJfWork(jfWorkId) {
    this.jfWorkService()
      .find(jfWorkId)
      .then(res => {
        this.jfWork = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
