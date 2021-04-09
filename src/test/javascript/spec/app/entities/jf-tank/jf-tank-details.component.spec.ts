/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import JfTankDetailComponent from '@/entities/jf-tank/jf-tank-details.vue';
import JfTankClass from '@/entities/jf-tank/jf-tank-details.component';
import JfTankService from '@/entities/jf-tank/jf-tank.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('JfTank Management Detail Component', () => {
    let wrapper: Wrapper<JfTankClass>;
    let comp: JfTankClass;
    let jfTankServiceStub: SinonStubbedInstance<JfTankService>;

    beforeEach(() => {
      jfTankServiceStub = sinon.createStubInstance<JfTankService>(JfTankService);

      wrapper = shallowMount<JfTankClass>(JfTankDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { jfTankService: () => jfTankServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundJfTank = { id: 123 };
        jfTankServiceStub.find.resolves(foundJfTank);

        // WHEN
        comp.retrieveJfTank(123);
        await comp.$nextTick();

        // THEN
        expect(comp.jfTank).toBe(foundJfTank);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundJfTank = { id: 123 };
        jfTankServiceStub.find.resolves(foundJfTank);

        // WHEN
        comp.beforeRouteEnter({ params: { jfTankId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.jfTank).toBe(foundJfTank);
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
