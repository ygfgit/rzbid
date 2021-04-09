/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import JfTargetDetailComponent from '@/entities/jf-target/jf-target-details.vue';
import JfTargetClass from '@/entities/jf-target/jf-target-details.component';
import JfTargetService from '@/entities/jf-target/jf-target.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('JfTarget Management Detail Component', () => {
    let wrapper: Wrapper<JfTargetClass>;
    let comp: JfTargetClass;
    let jfTargetServiceStub: SinonStubbedInstance<JfTargetService>;

    beforeEach(() => {
      jfTargetServiceStub = sinon.createStubInstance<JfTargetService>(JfTargetService);

      wrapper = shallowMount<JfTargetClass>(JfTargetDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { jfTargetService: () => jfTargetServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundJfTarget = { id: 123 };
        jfTargetServiceStub.find.resolves(foundJfTarget);

        // WHEN
        comp.retrieveJfTarget(123);
        await comp.$nextTick();

        // THEN
        expect(comp.jfTarget).toBe(foundJfTarget);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundJfTarget = { id: 123 };
        jfTargetServiceStub.find.resolves(foundJfTarget);

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
