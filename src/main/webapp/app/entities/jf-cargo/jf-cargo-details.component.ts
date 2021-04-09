import { Component, Vue, Inject } from 'vue-property-decorator';

import { IJfCargo } from '@/shared/model/jf-cargo.model';
import JfCargoService from './jf-cargo.service';

@Component
export default class JfCargoDetails extends Vue {
  @Inject('jfCargoService') private jfCargoService: () => JfCargoService;
  public jfCargo: IJfCargo = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.jfCargoId) {
        vm.retrieveJfCargo(to.params.jfCargoId);
      }
    });
  }

  public retrieveJfCargo(jfCargoId) {
    this.jfCargoService()
      .find(jfCargoId)
      .then(res => {
        this.jfCargo = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
