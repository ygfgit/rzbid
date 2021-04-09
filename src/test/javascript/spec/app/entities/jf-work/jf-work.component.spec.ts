/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import JfWorkComponent from '@/entities/jf-work/jf-work.vue';
import JfWorkClass from '@/entities/jf-work/jf-work.component';
import JfWorkService from '@/entities/jf-work/jf-work.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.component('jhi-sort-indicator', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('JfWork Management Component', () => {
    let wrapper: Wrapper<JfWorkClass>;
    let comp: JfWorkClass;
    let jfWorkServiceStub: SinonStubbedInstance<JfWorkService>;

    beforeEach(() => {
      jfWorkServiceStub = sinon.createStubInstance<JfWorkService>(JfWorkService);
      jfWorkServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<JfWorkClass>(JfWorkComponent, {
        store,
        i18n,
        localVue,
        stubs: { jhiItemCount: true, bPagination: true, bModal: bModalStub as any },
        provide: {
          jfWorkService: () => jfWorkServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      jfWorkServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllJfWorks();
      await comp.$nextTick();

      // THEN
      expect(jfWorkServiceStub.retrieve.called).toBeTruthy();
      expect(comp.jfWorks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', async () => {
      // GIVEN
      jfWorkServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();

      // THEN
      expect(jfWorkServiceStub.retrieve.called).toBeTruthy();
      expect(comp.jfWorks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should not load a page if the page is the same as the previous page', () => {
      // GIVEN
      jfWorkServiceStub.retrieve.reset();
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(jfWorkServiceStub.retrieve.called).toBeFalsy();
    });

    it('should re-initialize the page', async () => {
      // GIVEN
      jfWorkServiceStub.retrieve.reset();
      jfWorkServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();
      comp.clear();
      await comp.$nextTick();

      // THEN
      expect(jfWorkServiceStub.retrieve.callCount).toEqual(3);
      expect(comp.page).toEqual(1);
      expect(comp.jfWorks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,asc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // GIVEN
      comp.propOrder = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,asc', 'id']);
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      jfWorkServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeJfWork();
      await comp.$nextTick();

      // THEN
      expect(jfWorkServiceStub.delete.called).toBeTruthy();
      expect(jfWorkServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
