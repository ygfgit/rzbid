/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import JfWorkDetailsDetailComponent from '@/entities/jf-work-details/jf-work-details-details.vue';
import JfWorkDetailsClass from '@/entities/jf-work-details/jf-work-details-details.component';
import JfWorkDetailsService from '@/entities/jf-work-details/jf-work-details.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('JfWorkDetails Management Detail Component', () => {
    let wrapper: Wrapper<JfWorkDetailsClass>;
    let comp: JfWorkDetailsClass;
    let jfWorkDetailsServiceStub: SinonStubbedInstance<JfWorkDetailsService>;

    beforeEach(() => {
      jfWorkDetailsServiceStub = sinon.createStubInstance<JfWorkDetailsService>(JfWorkDetailsService);

      wrapper = shallowMount<JfWorkDetailsClass>(JfWorkDetailsDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { jfWorkDetailsService: () => jfWorkDetailsServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundJfWorkDetails = { id: 123 };
        jfWorkDetailsServiceStub.find.resolves(foundJfWorkDetails);

        // WHEN
        comp.retrieveJfWorkDetails(123);
        await comp.$nextTick();

        // THEN
        expect(comp.jfWorkDetails).toBe(foundJfWorkDetails);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundJfWorkDetails = { id: 123 };
        jfWorkDetailsServiceStub.find.resolves(foundJfWorkDetails);

        // WHEN
        comp.beforeRouteEnter({ params: { jfWorkDetailsId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.jfWorkDetails).toBe(foundJfWorkDetails);
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
