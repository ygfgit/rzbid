/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import JfCustomerComponent from '@/entities/jf-customer/jf-customer.vue';
import JfCustomerClass from '@/entities/jf-customer/jf-customer.component';
import JfCustomerService from '@/entities/jf-customer/jf-customer.service';

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
  describe('JfCustomer Management Component', () => {
    let wrapper: Wrapper<JfCustomerClass>;
    let comp: JfCustomerClass;
    let jfCustomerServiceStub: SinonStubbedInstance<JfCustomerService>;

    beforeEach(() => {
      jfCustomerServiceStub = sinon.createStubInstance<JfCustomerService>(JfCustomerService);
      jfCustomerServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<JfCustomerClass>(JfCustomerComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          jfCustomerService: () => jfCustomerServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      jfCustomerServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllJfCustomers();
      await comp.$nextTick();

      // THEN
      expect(jfCustomerServiceStub.retrieve.called).toBeTruthy();
      expect(comp.jfCustomers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      jfCustomerServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeJfCustomer();
      await comp.$nextTick();

      // THEN
      expect(jfCustomerServiceStub.delete.called).toBeTruthy();
      expect(jfCustomerServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
