import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const JfMaster = () => import('@/entities/jf-master/jf-master.vue');
// prettier-ignore
const JfMasterUpdate = () => import('@/entities/jf-master/jf-master-update.vue');
// prettier-ignore
const JfMasterDetails = () => import('@/entities/jf-master/jf-master-details.vue');
// prettier-ignore
const BtJfJtJfmx = () => import('@/entities/bt-jf-jt-jfmx/bt-jf-jt-jfmx.vue');
// prettier-ignore
const BtJfJtJfmxUpdate = () => import('@/entities/bt-jf-jt-jfmx/bt-jf-jt-jfmx-update.vue');
// prettier-ignore
const BtJfJtJfmxDetails = () => import('@/entities/bt-jf-jt-jfmx/bt-jf-jt-jfmx-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/jf-master',
    name: 'JfMaster',
    component: JfMaster,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-master/new',
    name: 'JfMasterCreate',
    component: JfMasterUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-master/:jfMasterId/edit',
    name: 'JfMasterEdit',
    component: JfMasterUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-master/:jfMasterId/view',
    name: 'JfMasterView',
    component: JfMasterDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bt-jf-jt-jfmx',
    name: 'BtJfJtJfmx',
    component: BtJfJtJfmx,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bt-jf-jt-jfmx/new',
    name: 'BtJfJtJfmxCreate',
    component: BtJfJtJfmxUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bt-jf-jt-jfmx/:btJfJtJfmxId/edit',
    name: 'BtJfJtJfmxEdit',
    component: BtJfJtJfmxUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bt-jf-jt-jfmx/:btJfJtJfmxId/view',
    name: 'BtJfJtJfmxView',
    component: BtJfJtJfmxDetails,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
