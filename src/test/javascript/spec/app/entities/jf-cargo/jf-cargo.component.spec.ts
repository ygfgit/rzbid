/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import JfCargoComponent from '@/entities/jf-cargo/jf-cargo.vue';
import JfCargoClass from '@/entities/jf-cargo/jf-cargo.component';
import JfCargoService from '@/entities/jf-cargo/jf-cargo.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
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
  describe('JfCargo Management Component', () => {
    let wrapper: Wrapper<JfCargoClass>;
    let comp: JfCargoClass;
    let jfCargoServiceStub: SinonStubbedInstance<JfCargoService>;

    beforeEach(() => {
      jfCargoServiceStub = sinon.createStubInstance<JfCargoService>(JfCargoService);
      jfCargoServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<JfCargoClass>(JfCargoComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          jfCargoService: () => jfCargoServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      jfCargoServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllJfCargos();
      await comp.$nextTick();

      // THEN
      expect(jfCargoServiceStub.retrieve.called).toBeTruthy();
      expect(comp.jfCargos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      jfCargoServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeJfCargo();
      await comp.$nextTick();

      // THEN
      expect(jfCargoServiceStub.delete.called).toBeTruthy();
      expect(jfCargoServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
