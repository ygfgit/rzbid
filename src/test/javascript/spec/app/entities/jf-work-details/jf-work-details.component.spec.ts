/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import JfWorkDetailsComponent from '@/entities/jf-work-details/jf-work-details.vue';
import JfWorkDetailsClass from '@/entities/jf-work-details/jf-work-details.component';
import JfWorkDetailsService from '@/entities/jf-work-details/jf-work-details.service';

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
  describe('JfWorkDetails Management Component', () => {
    let wrapper: Wrapper<JfWorkDetailsClass>;
    let comp: JfWorkDetailsClass;
    let jfWorkDetailsServiceStub: SinonStubbedInstance<JfWorkDetailsService>;

    beforeEach(() => {
      jfWorkDetailsServiceStub = sinon.createStubInstance<JfWorkDetailsService>(JfWorkDetailsService);
      jfWorkDetailsServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<JfWorkDetailsClass>(JfWorkDetailsComponent, {
        store,
        i18n,
        localVue,
        stubs: { jhiItemCount: true, bPagination: true, bModal: bModalStub as any },
        provide: {
          jfWorkDetailsService: () => jfWorkDetailsServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      jfWorkDetailsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllJfWorkDetailss();
      await comp.$nextTick();

      // THEN
      expect(jfWorkDetailsServiceStub.retrieve.called).toBeTruthy();
      expect(comp.jfWorkDetails[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', async () => {
      // GIVEN
      jfWorkDetailsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();

      // THEN
      expect(jfWorkDetailsServiceStub.retrieve.called).toBeTruthy();
      expect(comp.jfWorkDetails[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should not load a page if the page is the same as the previous page', () => {
      // GIVEN
      jfWorkDetailsServiceStub.retrieve.reset();
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(jfWorkDetailsServiceStub.retrieve.called).toBeFalsy();
    });

    it('should re-initialize the page', async () => {
      // GIVEN
      jfWorkDetailsServiceStub.retrieve.reset();
      jfWorkDetailsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();
      comp.clear();
      await comp.$nextTick();

      // THEN
      expect(jfWorkDetailsServiceStub.retrieve.callCount).toEqual(3);
      expect(comp.page).toEqual(1);
      expect(comp.jfWorkDetails[0]).toEqual(jasmine.objectContaining({ id: 123 }));
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
      jfWorkDetailsServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeJfWorkDetails();
      await comp.$nextTick();

      // THEN
      expect(jfWorkDetailsServiceStub.delete.called).toBeTruthy();
      expect(jfWorkDetailsServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
