/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import JfCompanyComponent from '@/entities/jf-company/jf-company.vue';
import JfCompanyClass from '@/entities/jf-company/jf-company.component';
import JfCompanyService from '@/entities/jf-company/jf-company.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('JfCompany Management Component', () => {
    let wrapper: Wrapper<JfCompanyClass>;
    let comp: JfCompanyClass;
    let jfCompanyServiceStub: SinonStubbedInstance<JfCompanyService>;

    beforeEach(() => {
      jfCompanyServiceStub = sinon.createStubInstance<JfCompanyService>(JfCompanyService);
      jfCompanyServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<JfCompanyClass>(JfCompanyComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          jfCompanyService: () => jfCompanyServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      jfCompanyServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllJfCompanys();
      await comp.$nextTick();

      // THEN
      expect(jfCompanyServiceStub.retrieve.called).toBeTruthy();
      expect(comp.jfCompanies[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      jfCompanyServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeJfCompany();
      await comp.$nextTick();

      // THEN
      expect(jfCompanyServiceStub.delete.called).toBeTruthy();
      expect(jfCompanyServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
