/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import JfTargetComponent from '@/entities/jf-target/jf-target.vue';
import JfTargetClass from '@/entities/jf-target/jf-target.component';
import JfTargetService from '@/entities/jf-target/jf-target.service';

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
  describe('JfTarget Management Component', () => {
    let wrapper: Wrapper<JfTargetClass>;
    let comp: JfTargetClass;
    let jfTargetServiceStub: SinonStubbedInstance<JfTargetService>;

    beforeEach(() => {
      jfTargetServiceStub = sinon.createStubInstance<JfTargetService>(JfTargetService);
      jfTargetServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<JfTargetClass>(JfTargetComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          jfTargetService: () => jfTargetServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      jfTargetServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllJfTargets();
      await comp.$nextTick();

      // THEN
      expect(jfTargetServiceStub.retrieve.called).toBeTruthy();
      expect(comp.jfTargets[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      jfTargetServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeJfTarget();
      await comp.$nextTick();

      // THEN
      expect(jfTargetServiceStub.delete.called).toBeTruthy();
      expect(jfTargetServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
