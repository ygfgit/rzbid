/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import JfMasterDetailComponent from '@/entities/jf-master/jf-master-details.vue';
import JfMasterClass from '@/entities/jf-master/jf-master-details.component';
import JfMasterService from '@/entities/jf-master/jf-master.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('JfMaster Management Detail Component', () => {
    let wrapper: Wrapper<JfMasterClass>;
    let comp: JfMasterClass;
    let jfMasterServiceStub: SinonStubbedInstance<JfMasterService>;

    beforeEach(() => {
      jfMasterServiceStub = sinon.createStubInstance<JfMasterService>(JfMasterService);

      wrapper = shallowMount<JfMasterClass>(JfMasterDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { jfMasterService: () => jfMasterServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundJfMaster = { id: 123 };
        jfMasterServiceStub.find.resolves(foundJfMaster);

        // WHEN
        comp.retrieveJfMaster(123);
        await comp.$nextTick();

        // THEN
        expect(comp.jfMaster).toBe(foundJfMaster);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundJfMaster = { id: 123 };
        jfMasterServiceStub.find.resolves(foundJfMaster);

        // WHEN
        comp.beforeRouteEnter({ params: { jfMasterId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.jfMaster).toBe(foundJfMaster);
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
