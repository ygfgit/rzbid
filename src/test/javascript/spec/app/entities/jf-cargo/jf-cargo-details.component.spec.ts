/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import JfCargoDetailComponent from '@/entities/jf-cargo/jf-cargo-details.vue';
import JfCargoClass from '@/entities/jf-cargo/jf-cargo-details.component';
import JfCargoService from '@/entities/jf-cargo/jf-cargo.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('JfCargo Management Detail Component', () => {
    let wrapper: Wrapper<JfCargoClass>;
    let comp: JfCargoClass;
    let jfCargoServiceStub: SinonStubbedInstance<JfCargoService>;

    beforeEach(() => {
      jfCargoServiceStub = sinon.createStubInstance<JfCargoService>(JfCargoService);

      wrapper = shallowMount<JfCargoClass>(JfCargoDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { jfCargoService: () => jfCargoServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundJfCargo = { id: 123 };
        jfCargoServiceStub.find.resolves(foundJfCargo);

        // WHEN
        comp.retrieveJfCargo(123);
        await comp.$nextTick();

        // THEN
        expect(comp.jfCargo).toBe(foundJfCargo);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundJfCargo = { id: 123 };
        jfCargoServiceStub.find.resolves(foundJfCargo);

        // WHEN
        comp.beforeRouteEnter({ params: { jfCargoId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.jfCargo).toBe(foundJfCargo);
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
