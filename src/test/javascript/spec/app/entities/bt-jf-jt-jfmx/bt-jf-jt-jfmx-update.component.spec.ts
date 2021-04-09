/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import BtJfJtJfmxUpdateComponent from '@/entities/bt-jf-jt-jfmx/bt-jf-jt-jfmx-update.vue';
import BtJfJtJfmxClass from '@/entities/bt-jf-jt-jfmx/bt-jf-jt-jfmx-update.component';
import BtJfJtJfmxService from '@/entities/bt-jf-jt-jfmx/bt-jf-jt-jfmx.service';

import JfMasterService from '@/entities/jf-master/jf-master.service';

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
  describe('BtJfJtJfmx Management Update Component', () => {
    let wrapper: Wrapper<BtJfJtJfmxClass>;
    let comp: BtJfJtJfmxClass;
    let btJfJtJfmxServiceStub: SinonStubbedInstance<BtJfJtJfmxService>;

    beforeEach(() => {
      btJfJtJfmxServiceStub = sinon.createStubInstance<BtJfJtJfmxService>(BtJfJtJfmxService);

      wrapper = shallowMount<BtJfJtJfmxClass>(BtJfJtJfmxUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          btJfJtJfmxService: () => btJfJtJfmxServiceStub,

          jfMasterService: () => new JfMasterService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.btJfJtJfmx = entity;
        btJfJtJfmxServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(btJfJtJfmxServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.btJfJtJfmx = entity;
        btJfJtJfmxServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(btJfJtJfmxServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundBtJfJtJfmx = { id: 123 };
        btJfJtJfmxServiceStub.find.resolves(foundBtJfJtJfmx);
        btJfJtJfmxServiceStub.retrieve.resolves([foundBtJfJtJfmx]);

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
