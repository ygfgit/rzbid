/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import JfCompanyDetailComponent from '@/entities/jf-company/jf-company-details.vue';
import JfCompanyClass from '@/entities/jf-company/jf-company-details.component';
import JfCompanyService from '@/entities/jf-company/jf-company.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('JfCompany Management Detail Component', () => {
    let wrapper: Wrapper<JfCompanyClass>;
    let comp: JfCompanyClass;
    let jfCompanyServiceStub: SinonStubbedInstance<JfCompanyService>;

    beforeEach(() => {
      jfCompanyServiceStub = sinon.createStubInstance<JfCompanyService>(JfCompanyService);

      wrapper = shallowMount<JfCompanyClass>(JfCompanyDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { jfCompanyService: () => jfCompanyServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundJfCompany = { id: 123 };
        jfCompanyServiceStub.find.resolves(foundJfCompany);

        // WHEN
        comp.retrieveJfCompany(123);
        await comp.$nextTick();

        // THEN
        expect(comp.jfCompany).toBe(foundJfCompany);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundJfCompany = { id: 123 };
        jfCompanyServiceStub.find.resolves(foundJfCompany);

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
