/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import JfWorkDetailsUpdateComponent from '@/entities/jf-work-details/jf-work-details-update.vue';
import JfWorkDetailsClass from '@/entities/jf-work-details/jf-work-details-update.component';
import JfWorkDetailsService from '@/entities/jf-work-details/jf-work-details.service';

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
  describe('JfWorkDetails Management Update Component', () => {
    let wrapper: Wrapper<JfWorkDetailsClass>;
    let comp: JfWorkDetailsClass;
    let jfWorkDetailsServiceStub: SinonStubbedInstance<JfWorkDetailsService>;

    beforeEach(() => {
      jfWorkDetailsServiceStub = sinon.createStubInstance<JfWorkDetailsService>(JfWorkDetailsService);

      wrapper = shallowMount<JfWorkDetailsClass>(JfWorkDetailsUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          jfWorkDetailsService: () => jfWorkDetailsServiceStub,

          jfWorkService: () => new JfWorkService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.jfWorkDetails = entity;
        jfWorkDetailsServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(jfWorkDetailsServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.jfWorkDetails = entity;
        jfWorkDetailsServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(jfWorkDetailsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundJfWorkDetails = { id: 123 };
        jfWorkDetailsServiceStub.find.resolves(foundJfWorkDetails);
        jfWorkDetailsServiceStub.retrieve.resolves([foundJfWorkDetails]);

        // WHEN
        comp.beforeRouteEnter({ params: { jfWorkDetailsId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.jfWorkDetails).toBe(foundJfWorkDetails);
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
