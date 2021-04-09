<template>
  <div>
    <h2 id="page-heading" data-cy="JfMasterHeading">
      <span v-text="$t('rzbidApp.jfMaster.home.title')" id="jf-master-heading">Jf Masters</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('rzbidApp.jfMaster.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'JfMasterCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-jf-master"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('rzbidApp.jfMaster.home.createLabel')"> Create a new Jf Master </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && jfMasters && jfMasters.length === 0">
      <span v-text="$t('rzbidApp.jfMaster.home.notFound')">No jfMasters found</span>
    </div>
    <div class="table-responsive" v-if="jfMasters && jfMasters.length > 0">
      <table class="table table-striped" aria-describedby="jfMasters">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('zygs')">
              <span v-text="$t('rzbidApp.jfMaster.zygs')">Zygs</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'zygs'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('je')">
              <span v-text="$t('rzbidApp.jfMaster.je')">Je</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'je'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('she')">
              <span v-text="$t('rzbidApp.jfMaster.she')">She</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'she'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shhje')">
              <span v-text="$t('rzbidApp.jfMaster.shhje')">Shhje</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shhje'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('jmje')">
              <span v-text="$t('rzbidApp.jfMaster.jmje')">Jmje</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'jmje'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('jmhje')">
              <span v-text="$t('rzbidApp.jfMaster.jmhje')">Jmhje</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'jmhje'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('idHw')">
              <span v-text="$t('rzbidApp.jfMaster.idHw')">Id Hw</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'idHw'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('htid')">
              <span v-text="$t('rzbidApp.jfMaster.htid')">Htid</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'htid'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('mb')">
              <span v-text="$t('rzbidApp.jfMaster.mb')">Mb</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'mb'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('zywtrid')">
              <span v-text="$t('rzbidApp.jfMaster.zywtrid')">Zywtrid</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'zywtrid'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('mark1')">
              <span v-text="$t('rzbidApp.jfMaster.mark1')">Mark 1</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'mark1'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('mark2')">
              <span v-text="$t('rzbidApp.jfMaster.mark2')">Mark 2</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'mark2'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createby')">
              <span v-text="$t('rzbidApp.jfMaster.createby')">Createby</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createby'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createbyid')">
              <span v-text="$t('rzbidApp.jfMaster.createbyid')">Createbyid</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createbyid'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createon')">
              <span v-text="$t('rzbidApp.jfMaster.createon')">Createon</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createon'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createGsid')">
              <span v-text="$t('rzbidApp.jfMaster.createGsid')">Create Gsid</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createGsid'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createGsmch')">
              <span v-text="$t('rzbidApp.jfMaster.createGsmch')">Create Gsmch</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createGsmch'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createBmid')">
              <span v-text="$t('rzbidApp.jfMaster.createBmid')">Create Bmid</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createBmid'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createBmm')">
              <span v-text="$t('rzbidApp.jfMaster.createBmm')">Create Bmm</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createBmm'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createGwid')">
              <span v-text="$t('rzbidApp.jfMaster.createGwid')">Create Gwid</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createGwid'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createGwm')">
              <span v-text="$t('rzbidApp.jfMaster.createGwm')">Create Gwm</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createGwm'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('modifyby')">
              <span v-text="$t('rzbidApp.jfMaster.modifyby')">Modifyby</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'modifyby'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('modifybyid')">
              <span v-text="$t('rzbidApp.jfMaster.modifybyid')">Modifybyid</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'modifybyid'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('modifyon')">
              <span v-text="$t('rzbidApp.jfMaster.modifyon')">Modifyon</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'modifyon'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="jfMaster in jfMasters" :key="jfMaster.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'JfMasterView', params: { jfMasterId: jfMaster.id } }">{{ jfMaster.id }}</router-link>
            </td>
            <td>{{ jfMaster.zygs }}</td>
            <td>{{ jfMaster.je }}</td>
            <td>{{ jfMaster.she }}</td>
            <td>{{ jfMaster.shhje }}</td>
            <td>{{ jfMaster.jmje }}</td>
            <td>{{ jfMaster.jmhje }}</td>
            <td>{{ jfMaster.idHw }}</td>
            <td>{{ jfMaster.htid }}</td>
            <td>{{ jfMaster.mb }}</td>
            <td>{{ jfMaster.zywtrid }}</td>
            <td>{{ jfMaster.mark1 }}</td>
            <td>{{ jfMaster.mark2 }}</td>
            <td>{{ jfMaster.createby }}</td>
            <td>{{ jfMaster.createbyid }}</td>
            <td>{{ jfMaster.createon }}</td>
            <td>{{ jfMaster.createGsid }}</td>
            <td>{{ jfMaster.createGsmch }}</td>
            <td>{{ jfMaster.createBmid }}</td>
            <td>{{ jfMaster.createBmm }}</td>
            <td>{{ jfMaster.createGwid }}</td>
            <td>{{ jfMaster.createGwm }}</td>
            <td>{{ jfMaster.modifyby }}</td>
            <td>{{ jfMaster.modifybyid }}</td>
            <td>{{ jfMaster.modifyon }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'JfMasterView', params: { jfMasterId: jfMaster.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'JfMasterEdit', params: { jfMasterId: jfMaster.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(jfMaster)"
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
        ><span id="rzbidApp.jfMaster.delete.question" data-cy="jfMasterDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-jfMaster-heading" v-text="$t('rzbidApp.jfMaster.delete.question', { id: removeId })">
          Are you sure you want to delete this Jf Master?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-jfMaster"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeJfMaster()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="jfMasters && jfMasters.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./jf-master.component.ts"></script>
