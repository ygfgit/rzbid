/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import JfWorkDetailComponent from '@/entities/jf-work/jf-work-details.vue';
import JfWorkClass from '@/entities/jf-work/jf-work-details.component';
import JfWorkService from '@/entities/jf-work/jf-work.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('JfWork Management Detail Component', () => {
    let wrapper: Wrapper<JfWorkClass>;
    let comp: JfWorkClass;
    let jfWorkServiceStub: SinonStubbedInstance<JfWorkService>;

    beforeEach(() => {
      jfWorkServiceStub = sinon.createStubInstance<JfWorkService>(JfWorkService);

      wrapper = shallowMount<JfWorkClass>(JfWorkDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { jfWorkService: () => jfWorkServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundJfWork = { id: 123 };
        jfWorkServiceStub.find.resolves(foundJfWork);

        // WHEN
        comp.retrieveJfWork(123);
        await comp.$nextTick();

        // THEN
        expect(comp.jfWork).toBe(foundJfWork);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundJfWork = { id: 123 };
        jfWorkServiceStub.find.resolves(foundJfWork);

        // WHEN
        comp.beforeRouteEnter({ params: { jfWorkId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.jfWork).toBe(foundJfWork);
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
