<template>
  <div>
    <h2 id="page-heading" data-cy="BtJfJtJfmxHeading">
      <span v-text="$t('rzbidApp.btJfJtJfmx.home.title')" id="bt-jf-jt-jfmx-heading">Bt Jf Jt Jfmxes</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('rzbidApp.btJfJtJfmx.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'BtJfJtJfmxCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-bt-jf-jt-jfmx"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('rzbidApp.btJfJtJfmx.home.createLabel')"> Create a new Bt Jf Jt Jfmx </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && btJfJtJfmxes && btJfJtJfmxes.length === 0">
      <span v-text="$t('rzbidApp.btJfJtJfmx.home.notFound')">No btJfJtJfmxes found</span>
    </div>
    <div class="table-responsive" v-if="btJfJtJfmxes && btJfJtJfmxes.length > 0">
      <table class="table table-striped" aria-describedby="btJfJtJfmxes">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('zygs')">
              <span v-text="$t('rzbidApp.btJfJtJfmx.zygs')">Zygs</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'zygs'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('zyetrid')">
              <span v-text="$t('rzbidApp.btJfJtJfmx.zyetrid')">Zyetrid</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'zyetrid'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('flmch')">
              <span v-text="$t('rzbidApp.btJfJtJfmx.flmch')">Flmch</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'flmch'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('fl')">
              <span v-text="$t('rzbidApp.btJfJtJfmx.fl')">Fl</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'fl'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shl')">
              <span v-text="$t('rzbidApp.btJfJtJfmx.shl')">Shl</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shl'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('je')">
              <span v-text="$t('rzbidApp.btJfJtJfmx.je')">Je</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'je'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shlv')">
              <span v-text="$t('rzbidApp.btJfJtJfmx.shlv')">Shlv</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shlv'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('she')">
              <span v-text="$t('rzbidApp.btJfJtJfmx.she')">She</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'she'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shhje')">
              <span v-text="$t('rzbidApp.btJfJtJfmx.shhje')">Shhje</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shhje'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('jmje')">
              <span v-text="$t('rzbidApp.btJfJtJfmx.jmje')">Jmje</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'jmje'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('startd')">
              <span v-text="$t('rzbidApp.btJfJtJfmx.startd')">Startd</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'startd'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('endd')">
              <span v-text="$t('rzbidApp.btJfJtJfmx.endd')">Endd</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'endd'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('mark1')">
              <span v-text="$t('rzbidApp.btJfJtJfmx.mark1')">Mark 1</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'mark1'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('mark2')">
              <span v-text="$t('rzbidApp.btJfJtJfmx.mark2')">Mark 2</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'mark2'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createby')">
              <span v-text="$t('rzbidApp.btJfJtJfmx.createby')">Createby</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createby'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createbyid')">
              <span v-text="$t('rzbidApp.btJfJtJfmx.createbyid')">Createbyid</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createbyid'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createon')">
              <span v-text="$t('rzbidApp.btJfJtJfmx.createon')">Createon</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createon'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createGsid')">
              <span v-text="$t('rzbidApp.btJfJtJfmx.createGsid')">Create Gsid</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createGsid'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createGsmch')">
              <span v-text="$t('rzbidApp.btJfJtJfmx.createGsmch')">Create Gsmch</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createGsmch'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createBmid')">
              <span v-text="$t('rzbidApp.btJfJtJfmx.createBmid')">Create Bmid</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createBmid'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createBmm')">
              <span v-text="$t('rzbidApp.btJfJtJfmx.createBmm')">Create Bmm</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createBmm'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createGwid')">
              <span v-text="$t('rzbidApp.btJfJtJfmx.createGwid')">Create Gwid</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createGwid'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createGwm')">
              <span v-text="$t('rzbidApp.btJfJtJfmx.createGwm')">Create Gwm</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createGwm'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('modifyby')">
              <span v-text="$t('rzbidApp.btJfJtJfmx.modifyby')">Modifyby</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'modifyby'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('modifybyid')">
              <span v-text="$t('rzbidApp.btJfJtJfmx.modifybyid')">Modifybyid</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'modifybyid'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('modifyon')">
              <span v-text="$t('rzbidApp.btJfJtJfmx.modifyon')">Modifyon</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'modifyon'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('master.id')">
              <span v-text="$t('rzbidApp.btJfJtJfmx.master')">Master</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'master.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="btJfJtJfmx in btJfJtJfmxes" :key="btJfJtJfmx.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'BtJfJtJfmxView', params: { btJfJtJfmxId: btJfJtJfmx.id } }">{{ btJfJtJfmx.id }}</router-link>
            </td>
            <td>{{ btJfJtJfmx.zygs }}</td>
            <td>{{ btJfJtJfmx.zyetrid }}</td>
            <td>{{ btJfJtJfmx.flmch }}</td>
            <td>{{ btJfJtJfmx.fl }}</td>
            <td>{{ btJfJtJfmx.shl }}</td>
            <td>{{ btJfJtJfmx.je }}</td>
            <td>{{ btJfJtJfmx.shlv }}</td>
            <td>{{ btJfJtJfmx.she }}</td>
            <td>{{ btJfJtJfmx.shhje }}</td>
            <td>{{ btJfJtJfmx.jmje }}</td>
            <td>{{ btJfJtJfmx.startd }}</td>
            <td>{{ btJfJtJfmx.endd }}</td>
            <td>{{ btJfJtJfmx.mark1 }}</td>
            <td>{{ btJfJtJfmx.mark2 }}</td>
            <td>{{ btJfJtJfmx.createby }}</td>
            <td>{{ btJfJtJfmx.createbyid }}</td>
            <td>{{ btJfJtJfmx.createon }}</td>
            <td>{{ btJfJtJfmx.createGsid }}</td>
            <td>{{ btJfJtJfmx.createGsmch }}</td>
            <td>{{ btJfJtJfmx.createBmid }}</td>
            <td>{{ btJfJtJfmx.createBmm }}</td>
            <td>{{ btJfJtJfmx.createGwid }}</td>
            <td>{{ btJfJtJfmx.createGwm }}</td>
            <td>{{ btJfJtJfmx.modifyby }}</td>
            <td>{{ btJfJtJfmx.modifybyid }}</td>
            <td>{{ btJfJtJfmx.modifyon }}</td>
            <td>
              <div v-if="btJfJtJfmx.master">
                <router-link :to="{ name: 'JfMasterView', params: { jfMasterId: btJfJtJfmx.master.id } }">{{
                  btJfJtJfmx.master.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'BtJfJtJfmxView', params: { btJfJtJfmxId: btJfJtJfmx.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'BtJfJtJfmxEdit', params: { btJfJtJfmxId: btJfJtJfmx.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(btJfJtJfmx)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="rzbidApp.btJfJtJfmx.delete.question" data-cy="btJfJtJfmxDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-btJfJtJfmx-heading" v-text="$t('rzbidApp.btJfJtJfmx.delete.question', { id: removeId })">
          Are you sure you want to delete this Bt Jf Jt Jfmx?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-btJfJtJfmx"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeBtJfJtJfmx()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="btJfJtJfmxes && btJfJtJfmxes.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./bt-jf-jt-jfmx.component.ts"></script>
