/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import JfCargoUpdateComponent from '@/entities/jf-cargo/jf-cargo-update.vue';
import JfCargoClass from '@/entities/jf-cargo/jf-cargo-update.component';
import JfCargoService from '@/entities/jf-cargo/jf-cargo.service';

import JfWorkService from '@/entities/jf-work/jf-work.service';

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
  describe('JfCargo Management Update Component', () => {
    let wrapper: Wrapper<JfCargoClass>;
    let comp: JfCargoClass;
    let jfCargoServiceStub: SinonStubbedInstance<JfCargoService>;

    beforeEach(() => {
      jfCargoServiceStub = sinon.createStubInstance<JfCargoService>(JfCargoService);

      wrapper = shallowMount<JfCargoClass>(JfCargoUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          jfCargoService: () => jfCargoServiceStub,

          jfWorkService: () => new JfWorkService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.jfCargo = entity;
        jfCargoServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(jfCargoServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.jfCargo = entity;
        jfCargoServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(jfCargoServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundJfCargo = { id: 123 };
        jfCargoServiceStub.find.resolves(foundJfCargo);
        jfCargoServiceStub.retrieve.resolves([foundJfCargo]);

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
