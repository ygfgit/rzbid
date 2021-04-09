/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import JfTargetUpdateComponent from '@/entities/jf-target/jf-target-update.vue';
import JfTargetClass from '@/entities/jf-target/jf-target-update.component';
import JfTargetService from '@/entities/jf-target/jf-target.service';

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
  describe('JfTarget Management Update Component', () => {
    let wrapper: Wrapper<JfTargetClass>;
    let comp: JfTargetClass;
    let jfTargetServiceStub: SinonStubbedInstance<JfTargetService>;

    beforeEach(() => {
      jfTargetServiceStub = sinon.createStubInstance<JfTargetService>(JfTargetService);

      wrapper = shallowMount<JfTargetClass>(JfTargetUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          jfTargetService: () => jfTargetServiceStub,

          jfWorkService: () => new JfWorkService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.jfTarget = entity;
        jfTargetServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(jfTargetServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.jfTarget = entity;
        jfTargetServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(jfTargetServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundJfTarget = { id: 123 };
        jfTargetServiceStub.find.resolves(foundJfTarget);
        jfTargetServiceStub.retrieve.resolves([foundJfTarget]);

        // WHEN
        comp.beforeRouteEnter({ params: { jfTargetId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.jfTarget).toBe(foundJfTarget);
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
