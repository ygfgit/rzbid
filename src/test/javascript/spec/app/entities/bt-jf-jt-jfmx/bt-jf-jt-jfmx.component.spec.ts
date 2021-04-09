/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import BtJfJtJfmxComponent from '@/entities/bt-jf-jt-jfmx/bt-jf-jt-jfmx.vue';
import BtJfJtJfmxClass from '@/entities/bt-jf-jt-jfmx/bt-jf-jt-jfmx.component';
import BtJfJtJfmxService from '@/entities/bt-jf-jt-jfmx/bt-jf-jt-jfmx.service';

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
  describe('BtJfJtJfmx Management Component', () => {
    let wrapper: Wrapper<BtJfJtJfmxClass>;
    let comp: BtJfJtJfmxClass;
    let btJfJtJfmxServiceStub: SinonStubbedInstance<BtJfJtJfmxService>;

    beforeEach(() => {
      btJfJtJfmxServiceStub = sinon.createStubInstance<BtJfJtJfmxService>(BtJfJtJfmxService);
      btJfJtJfmxServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<BtJfJtJfmxClass>(BtJfJtJfmxComponent, {
        store,
        i18n,
        localVue,
        stubs: { jhiItemCount: true, bPagination: true, bModal: bModalStub as any },
        provide: {
          btJfJtJfmxService: () => btJfJtJfmxServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      btJfJtJfmxServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllBtJfJtJfmxs();
      await comp.$nextTick();

      // THEN
      expect(btJfJtJfmxServiceStub.retrieve.called).toBeTruthy();
      expect(comp.btJfJtJfmxes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', async () => {
      // GIVEN
      btJfJtJfmxServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();

      // THEN
      expect(btJfJtJfmxServiceStub.retrieve.called).toBeTruthy();
      expect(comp.btJfJtJfmxes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should not load a page if the page is the same as the previous page', () => {
      // GIVEN
      btJfJtJfmxServiceStub.retrieve.reset();
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(btJfJtJfmxServiceStub.retrieve.called).toBeFalsy();
    });

    it('should re-initialize the page', async () => {
      // GIVEN
      btJfJtJfmxServiceStub.retrieve.reset();
      btJfJtJfmxServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();
      comp.clear();
      await comp.$nextTick();

      // THEN
      expect(btJfJtJfmxServiceStub.retrieve.callCount).toEqual(3);
      expect(comp.page).toEqual(1);
      expect(comp.btJfJtJfmxes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
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
      btJfJtJfmxServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeBtJfJtJfmx();
      await comp.$nextTick();

      // THEN
      expect(btJfJtJfmxServiceStub.delete.called).toBeTruthy();
      expect(btJfJtJfmxServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
