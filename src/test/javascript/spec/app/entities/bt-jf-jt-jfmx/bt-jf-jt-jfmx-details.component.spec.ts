/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import BtJfJtJfmxDetailComponent from '@/entities/bt-jf-jt-jfmx/bt-jf-jt-jfmx-details.vue';
import BtJfJtJfmxClass from '@/entities/bt-jf-jt-jfmx/bt-jf-jt-jfmx-details.component';
import BtJfJtJfmxService from '@/entities/bt-jf-jt-jfmx/bt-jf-jt-jfmx.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('BtJfJtJfmx Management Detail Component', () => {
    let wrapper: Wrapper<BtJfJtJfmxClass>;
    let comp: BtJfJtJfmxClass;
    let btJfJtJfmxServiceStub: SinonStubbedInstance<BtJfJtJfmxService>;

    beforeEach(() => {
      btJfJtJfmxServiceStub = sinon.createStubInstance<BtJfJtJfmxService>(BtJfJtJfmxService);

      wrapper = shallowMount<BtJfJtJfmxClass>(BtJfJtJfmxDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { btJfJtJfmxService: () => btJfJtJfmxServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundBtJfJtJfmx = { id: 123 };
        btJfJtJfmxServiceStub.find.resolves(foundBtJfJtJfmx);

        // WHEN
        comp.retrieveBtJfJtJfmx(123);
        await comp.$nextTick();

        // THEN
        expect(comp.btJfJtJfmx).toBe(foundBtJfJtJfmx);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundBtJfJtJfmx = { id: 123 };
        btJfJtJfmxServiceStub.find.resolves(foundBtJfJtJfmx);

        // WHEN
        comp.beforeRouteEnter({ params: { btJfJtJfmxId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.btJfJtJfmx).toBe(foundBtJfJtJfmx);
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
