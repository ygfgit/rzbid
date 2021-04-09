/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import JfCompanyUpdateComponent from '@/entities/jf-company/jf-company-update.vue';
import JfCompanyClass from '@/entities/jf-company/jf-company-update.component';
import JfCompanyService from '@/entities/jf-company/jf-company.service';

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
  describe('JfCompany Management Update Component', () => {
    let wrapper: Wrapper<JfCompanyClass>;
    let comp: JfCompanyClass;
    let jfCompanyServiceStub: SinonStubbedInstance<JfCompanyService>;

    beforeEach(() => {
      jfCompanyServiceStub = sinon.createStubInstance<JfCompanyService>(JfCompanyService);

      wrapper = shallowMount<JfCompanyClass>(JfCompanyUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          jfCompanyService: () => jfCompanyServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.jfCompany = entity;
        jfCompanyServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(jfCompanyServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.jfCompany = entity;
        jfCompanyServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(jfCompanyServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundJfCompany = { id: 123 };
        jfCompanyServiceStub.find.resolves(foundJfCompany);
        jfCompanyServiceStub.retrieve.resolves([foundJfCompany]);

        // WHEN
        comp.beforeRouteEnter({ params: { jfCompanyId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.jfCompany).toBe(foundJfCompany);
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
