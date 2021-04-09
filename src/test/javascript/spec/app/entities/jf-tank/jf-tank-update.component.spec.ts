/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import JfTankUpdateComponent from '@/entities/jf-tank/jf-tank-update.vue';
import JfTankClass from '@/entities/jf-tank/jf-tank-update.component';
import JfTankService from '@/entities/jf-tank/jf-tank.service';

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
  describe('JfTank Management Update Component', () => {
    let wrapper: Wrapper<JfTankClass>;
    let comp: JfTankClass;
    let jfTankServiceStub: SinonStubbedInstance<JfTankService>;

    beforeEach(() => {
      jfTankServiceStub = sinon.createStubInstance<JfTankService>(JfTankService);

      wrapper = shallowMount<JfTankClass>(JfTankUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          jfTankService: () => jfTankServiceStub,

          jfWorkService: () => new JfWorkService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.jfTank = entity;
        jfTankServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(jfTankServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.jfTank = entity;
        jfTankServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(jfTankServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundJfTank = { id: 123 };
        jfTankServiceStub.find.resolves(foundJfTank);
        jfTankServiceStub.retrieve.resolves([foundJfTank]);

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
