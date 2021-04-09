/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import JfCustomerUpdateComponent from '@/entities/jf-customer/jf-customer-update.vue';
import JfCustomerClass from '@/entities/jf-customer/jf-customer-update.component';
import JfCustomerService from '@/entities/jf-customer/jf-customer.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('JfCustomer Management Update Component', () => {
    let wrapper: Wrapper<JfCustomerClass>;
    let comp: JfCustomerClass;
    let jfCustomerServiceStub: SinonStubbedInstance<JfCustomerService>;

    beforeEach(() => {
      jfCustomerServiceStub = sinon.createStubInstance<JfCustomerService>(JfCustomerService);

      wrapper = shallowMount<JfCustomerClass>(JfCustomerUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          jfCustomerService: () => jfCustomerServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.jfCustomer = entity;
        jfCustomerServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(jfCustomerServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.jfCustomer = entity;
        jfCustomerServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(jfCustomerServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundJfCustomer = { id: 123 };
        jfCustomerServiceStub.find.resolves(foundJfCustomer);
        jfCustomerServiceStub.retrieve.resolves([foundJfCustomer]);

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
