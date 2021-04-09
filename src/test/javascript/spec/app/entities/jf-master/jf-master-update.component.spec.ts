/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import JfMasterUpdateComponent from '@/entities/jf-master/jf-master-update.vue';
import JfMasterClass from '@/entities/jf-master/jf-master-update.component';
import JfMasterService from '@/entities/jf-master/jf-master.service';

import BtJfJtJfmxService from '@/entities/bt-jf-jt-jfmx/bt-jf-jt-jfmx.service';

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
  describe('JfMaster Management Update Component', () => {
    let wrapper: Wrapper<JfMasterClass>;
    let comp: JfMasterClass;
    let jfMasterServiceStub: SinonStubbedInstance<JfMasterService>;

    beforeEach(() => {
      jfMasterServiceStub = sinon.createStubInstance<JfMasterService>(JfMasterService);

      wrapper = shallowMount<JfMasterClass>(JfMasterUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          jfMasterService: () => jfMasterServiceStub,

          btJfJtJfmxService: () => new BtJfJtJfmxService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.jfMaster = entity;
        jfMasterServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(jfMasterServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.jfMaster = entity;
        jfMasterServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(jfMasterServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundJfMaster = { id: 123 };
        jfMasterServiceStub.find.resolves(foundJfMaster);
        jfMasterServiceStub.retrieve.resolves([foundJfMaster]);

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
