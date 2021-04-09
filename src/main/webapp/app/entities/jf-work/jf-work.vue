<template>
  <div>
    <h2 id="page-heading" data-cy="JfWorkHeading">
      <span v-text="$t('rzbidApp.jfWork.home.title')" id="jf-work-heading">Jf Works</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('rzbidApp.jfWork.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'JfWorkCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-jf-work"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('rzbidApp.jfWork.home.createLabel')"> Create a new Jf Work </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && jfWorks && jfWorks.length === 0">
      <span v-text="$t('rzbidApp.jfWork.home.notFound')">No jfWorks found</span>
    </div>
    <div class="table-responsive" v-if="jfWorks && jfWorks.length > 0">
      <table class="table table-striped" aria-describedby="jfWorks">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('hwmch')">
              <span v-text="$t('rzbidApp.jfWork.hwmch')">Hwmch</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'hwmch'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('jhsl')">
              <span v-text="$t('rzbidApp.jfWork.jhsl')">Jhsl</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'jhsl'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('bz')">
              <span v-text="$t('rzbidApp.jfWork.bz')">Bz</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'bz'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="jfWork in jfWorks" :key="jfWork.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'JfWorkView', params: { jfWorkId: jfWork.id } }">{{ jfWork.id }}</router-link>
            </td>
            <td>{{ jfWork.hwmch }}</td>
            <td>{{ jfWork.jhsl }}</td>
            <td>{{ jfWork.bz }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'JfWorkView', params: { jfWorkId: jfWork.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'JfWorkEdit', params: { jfWorkId: jfWork.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(jfWork)"
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
        ><span id="rzbidApp.jfWork.delete.question" data-cy="jfWorkDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-jfWork-heading" v-text="$t('rzbidApp.jfWork.delete.question', { id: removeId })">
          Are you sure you want to delete this Jf Work?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-jfWork"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeJfWork()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="jfWorks && jfWorks.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./jf-work.component.ts"></script>
