/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import JfTankComponent from '@/entities/jf-tank/jf-tank.vue';
import JfTankClass from '@/entities/jf-tank/jf-tank.component';
import JfTankService from '@/entities/jf-tank/jf-tank.service';

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
  describe('JfTank Management Component', () => {
    let wrapper: Wrapper<JfTankClass>;
    let comp: JfTankClass;
    let jfTankServiceStub: SinonStubbedInstance<JfTankService>;

    beforeEach(() => {
      jfTankServiceStub = sinon.createStubInstance<JfTankService>(JfTankService);
      jfTankServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<JfTankClass>(JfTankComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          jfTankService: () => jfTankServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      jfTankServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllJfTanks();
      await comp.$nextTick();

      // THEN
      expect(jfTankServiceStub.retrieve.called).toBeTruthy();
      expect(comp.jfTanks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      jfTankServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeJfTank();
      await comp.$nextTick();

      // THEN
      expect(jfTankServiceStub.delete.called).toBeTruthy();
      expect(jfTankServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
