/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import JfWorkUpdateComponent from '@/entities/jf-work/jf-work-update.vue';
import JfWorkClass from '@/entities/jf-work/jf-work-update.component';
import JfWorkService from '@/entities/jf-work/jf-work.service';

import JfCargoService from '@/entities/jf-cargo/jf-cargo.service';

import JfTargetService from '@/entities/jf-target/jf-target.service';

import JfTankService from '@/entities/jf-tank/jf-tank.service';

import JfWorkDetailsService from '@/entities/jf-work-details/jf-work-details.service';

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
  describe('JfWork Management Update Component', () => {
    let wrapper: Wrapper<JfWorkClass>;
    let comp: JfWorkClass;
    let jfWorkServiceStub: SinonStubbedInstance<JfWorkService>;

    beforeEach(() => {
      jfWorkServiceStub = sinon.createStubInstance<JfWorkService>(JfWorkService);

      wrapper = shallowMount<JfWorkClass>(JfWorkUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          jfWorkService: () => jfWorkServiceStub,

          jfCargoService: () => new JfCargoService(),

          jfTargetService: () => new JfTargetService(),

          jfTankService: () => new JfTankService(),

          jfWorkDetailsService: () => new JfWorkDetailsService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.jfWork = entity;
        jfWorkServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(jfWorkServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.jfWork = entity;
        jfWorkServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(jfWorkServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundJfWork = { id: 123 };
        jfWorkServiceStub.find.resolves(foundJfWork);
        jfWorkServiceStub.retrieve.resolves([foundJfWork]);

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
