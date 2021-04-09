/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import JfCustomerDetailComponent from '@/entities/jf-customer/jf-customer-details.vue';
import JfCustomerClass from '@/entities/jf-customer/jf-customer-details.component';
import JfCustomerService from '@/entities/jf-customer/jf-customer.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('JfCustomer Management Detail Component', () => {
    let wrapper: Wrapper<JfCustomerClass>;
    let comp: JfCustomerClass;
    let jfCustomerServiceStub: SinonStubbedInstance<JfCustomerService>;

    beforeEach(() => {
      jfCustomerServiceStub = sinon.createStubInstance<JfCustomerService>(JfCustomerService);

      wrapper = shallowMount<JfCustomerClass>(JfCustomerDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { jfCustomerService: () => jfCustomerServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundJfCustomer = { id: 123 };
        jfCustomerServiceStub.find.resolves(foundJfCustomer);

        // WHEN
        comp.retrieveJfCustomer(123);
        await comp.$nextTick();

        // THEN
        expect(comp.jfCustomer).toBe(foundJfCustomer);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundJfCustomer = { id: 123 };
        jfCustomerServiceStub.find.resolves(foundJfCustomer);

        // WHEN
        comp.beforeRouteEnter({ params: { jfCustomerId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.jfCustomer).toBe(foundJfCustomer);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
