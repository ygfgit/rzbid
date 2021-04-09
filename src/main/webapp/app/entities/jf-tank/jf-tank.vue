<template>
  <div>
    <h2 id="page-heading" data-cy="JfTankHeading">
      <span v-text="$t('rzbidApp.jfTank.home.title')" id="jf-tank-heading">Jf Tanks</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('rzbidApp.jfTank.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'JfTankCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-jf-tank"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('rzbidApp.jfTank.home.createLabel')"> Create a new Jf Tank </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && jfTanks && jfTanks.length === 0">
      <span v-text="$t('rzbidApp.jfTank.home.notFound')">No jfTanks found</span>
    </div>
    <div class="table-responsive" v-if="jfTanks && jfTanks.length > 0">
      <table class="table table-striped" aria-describedby="jfTanks">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('rzbidApp.jfTank.zone')">Zone</span></th>
            <th scope="row"><span v-text="$t('rzbidApp.jfTank.code')">Code</span></th>
            <th scope="row"><span v-text="$t('rzbidApp.jfTank.master')">Master</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="jfTank in jfTanks" :key="jfTank.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'JfTankView', params: { jfTankId: jfTank.id } }">{{ jfTank.id }}</router-link>
            </td>
            <td>{{ jfTank.zone }}</td>
            <td>{{ jfTank.code }}</td>
            <td>
              <div v-if="jfTank.master">
                <router-link :to="{ name: 'JfWorkView', params: { jfWorkId: jfTank.master.id } }">{{ jfTank.master.id }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'JfTankView', params: { jfTankId: jfTank.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'JfTankEdit', params: { jfTankId: jfTank.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(jfTank)"
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
        ><span id="rzbidApp.jfTank.delete.question" data-cy="jfTankDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-jfTank-heading" v-text="$t('rzbidApp.jfTank.delete.question', { id: removeId })">
          Are you sure you want to delete this Jf Tank?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-jfTank"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeJfTank()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./jf-tank.component.ts"></script>
