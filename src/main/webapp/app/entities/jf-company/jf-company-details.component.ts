import { Component, Vue, Inject } from 'vue-property-decorator';

import { IJfCompany } from '@/shared/model/jf-company.model';
import JfCompanyService from './jf-company.service';

@Component
export default class JfCompanyDetails extends Vue {
  @Inject('jfCompanyService') private jfCompanyService: () => JfCompanyService;
  public jfCompany: IJfCompany = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.jfCompanyId) {
        vm.retrieveJfCompany(to.params.jfCompanyId);
      }
    });
  }

  public retrieveJfCompany(jfCompanyId) {
    this.jfCompanyService()
      .find(jfCompanyId)
      .then(res => {
        this.jfCompany = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
