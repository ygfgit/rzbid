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
// prettier-ignore
const JfCargo = () => import('@/entities/jf-cargo/jf-cargo.vue');
// prettier-ignore
const JfCargoUpdate = () => import('@/entities/jf-cargo/jf-cargo-update.vue');
// prettier-ignore
const JfCargoDetails = () => import('@/entities/jf-cargo/jf-cargo-details.vue');
// prettier-ignore
const JfCustomer = () => import('@/entities/jf-customer/jf-customer.vue');
// prettier-ignore
const JfCustomerUpdate = () => import('@/entities/jf-customer/jf-customer-update.vue');
// prettier-ignore
const JfCustomerDetails = () => import('@/entities/jf-customer/jf-customer-details.vue');
// prettier-ignore
const JfCompany = () => import('@/entities/jf-company/jf-company.vue');
// prettier-ignore
const JfCompanyUpdate = () => import('@/entities/jf-company/jf-company-update.vue');
// prettier-ignore
const JfCompanyDetails = () => import('@/entities/jf-company/jf-company-details.vue');
// prettier-ignore
const JfTank = () => import('@/entities/jf-tank/jf-tank.vue');
// prettier-ignore
const JfTankUpdate = () => import('@/entities/jf-tank/jf-tank-update.vue');
// prettier-ignore
const JfTankDetails = () => import('@/entities/jf-tank/jf-tank-details.vue');
// prettier-ignore
const JfWork = () => import('@/entities/jf-work/jf-work.vue');
// prettier-ignore
const JfWorkUpdate = () => import('@/entities/jf-work/jf-work-update.vue');
// prettier-ignore
const JfWorkDetails = () => import('@/entities/jf-work/jf-work-details.vue');
// prettier-ignore
const JfTarget = () => import('@/entities/jf-target/jf-target.vue');
// prettier-ignore
const JfTargetUpdate = () => import('@/entities/jf-target/jf-target-update.vue');
// prettier-ignore
const JfTargetDetails = () => import('@/entities/jf-target/jf-target-details.vue');
// prettier-ignore
const JfWorkDetails = () => import('@/entities/jf-work-details/jf-work-details.vue');
// prettier-ignore
const JfWorkDetailsUpdate = () => import('@/entities/jf-work-details/jf-work-details-update.vue');
// prettier-ignore
const JfWorkDetailsDetails = () => import('@/entities/jf-work-details/jf-work-details-details.vue');
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
  {
    path: '/jf-cargo',
    name: 'JfCargo',
    component: JfCargo,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-cargo/new',
    name: 'JfCargoCreate',
    component: JfCargoUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-cargo/:jfCargoId/edit',
    name: 'JfCargoEdit',
    component: JfCargoUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-cargo/:jfCargoId/view',
    name: 'JfCargoView',
    component: JfCargoDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-customer',
    name: 'JfCustomer',
    component: JfCustomer,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-customer/new',
    name: 'JfCustomerCreate',
    component: JfCustomerUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-customer/:jfCustomerId/edit',
    name: 'JfCustomerEdit',
    component: JfCustomerUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-customer/:jfCustomerId/view',
    name: 'JfCustomerView',
    component: JfCustomerDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-company',
    name: 'JfCompany',
    component: JfCompany,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-company/new',
    name: 'JfCompanyCreate',
    component: JfCompanyUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-company/:jfCompanyId/edit',
    name: 'JfCompanyEdit',
    component: JfCompanyUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-company/:jfCompanyId/view',
    name: 'JfCompanyView',
    component: JfCompanyDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-tank',
    name: 'JfTank',
    component: JfTank,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-tank/new',
    name: 'JfTankCreate',
    component: JfTankUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-tank/:jfTankId/edit',
    name: 'JfTankEdit',
    component: JfTankUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-tank/:jfTankId/view',
    name: 'JfTankView',
    component: JfTankDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-work',
    name: 'JfWork',
    component: JfWork,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-work/new',
    name: 'JfWorkCreate',
    component: JfWorkUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-work/:jfWorkId/edit',
    name: 'JfWorkEdit',
    component: JfWorkUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-work/:jfWorkId/view',
    name: 'JfWorkView',
    component: JfWorkDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-target',
    name: 'JfTarget',
    component: JfTarget,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-target/new',
    name: 'JfTargetCreate',
    component: JfTargetUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-target/:jfTargetId/edit',
    name: 'JfTargetEdit',
    component: JfTargetUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-target/:jfTargetId/view',
    name: 'JfTargetView',
    component: JfTargetDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-work-details',
    name: 'JfWorkDetails',
    component: JfWorkDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-work-details/new',
    name: 'JfWorkDetailsCreate',
    component: JfWorkDetailsUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-work-details/:jfWorkDetailsId/edit',
    name: 'JfWorkDetailsEdit',
    component: JfWorkDetailsUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/jf-work-details/:jfWorkDetailsId/view',
    name: 'JfWorkDetailsView',
    component: JfWorkDetailsDetails,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
