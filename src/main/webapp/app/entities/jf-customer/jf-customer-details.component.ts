import { Component, Vue, Inject } from 'vue-property-decorator';

import { IJfCustomer } from '@/shared/model/jf-customer.model';
import JfCustomerService from './jf-customer.service';

@Component
export default class JfCustomerDetails extends Vue {
  @Inject('jfCustomerService') private jfCustomerService: () => JfCustomerService;
  public jfCustomer: IJfCustomer = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.jfCustomerId) {
        vm.retrieveJfCustomer(to.params.jfCustomerId);
      }
    });
  }

  public retrieveJfCustomer(jfCustomerId) {
    this.jfCustomerService()
      .find(jfCustomerId)
      .then(res => {
        this.jfCustomer = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
