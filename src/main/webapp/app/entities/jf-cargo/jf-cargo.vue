<template>
  <div>
    <h2 id="page-heading" data-cy="JfCargoHeading">
      <span v-text="$t('rzbidApp.jfCargo.home.title')" id="jf-cargo-heading">Jf Cargos</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('rzbidApp.jfCargo.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'JfCargoCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-jf-cargo"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('rzbidApp.jfCargo.home.createLabel')"> Create a new Jf Cargo </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && jfCargos && jfCargos.length === 0">
      <span v-text="$t('rzbidApp.jfCargo.home.notFound')">No jfCargos found</span>
    </div>
    <div class="table-responsive" v-if="jfCargos && jfCargos.length > 0">
      <table class="table table-striped" aria-describedby="jfCargos">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('rzbidApp.jfCargo.rq')">Rq</span></th>
            <th scope="row"><span v-text="$t('rzbidApp.jfCargo.zygsdm')">Zygsdm</span></th>
            <th scope="row"><span v-text="$t('rzbidApp.jfCargo.hth')">Hth</span></th>
            <th scope="row"><span v-text="$t('rzbidApp.jfCargo.zywtrid')">Zywtrid</span></th>
            <th scope="row"><span v-text="$t('rzbidApp.jfCargo.zywtr')">Zywtr</span></th>
            <th scope="row"><span v-text="$t('rzbidApp.jfCargo.zhwchm')">Zhwchm</span></th>
            <th scope="row"><span v-text="$t('rzbidApp.jfCargo.hwmch')">Hwmch</span></th>
            <th scope="row"><span v-text="$t('rzbidApp.jfCargo.gq')">Gq</span></th>
            <th scope="row"><span v-text="$t('rzbidApp.jfCargo.shl')">Shl</span></th>
            <th scope="row"><span v-text="$t('rzbidApp.jfCargo.syl')">Syl</span></th>
            <th scope="row"><span v-text="$t('rzbidApp.jfCargo.master')">Master</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="jfCargo in jfCargos" :key="jfCargo.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'JfCargoView', params: { jfCargoId: jfCargo.id } }">{{ jfCargo.id }}</router-link>
            </td>
            <td>{{ jfCargo.rq }}</td>
            <td>{{ jfCargo.zygsdm }}</td>
            <td>{{ jfCargo.hth }}</td>
            <td>{{ jfCargo.zywtrid }}</td>
            <td>{{ jfCargo.zywtr }}</td>
            <td>{{ jfCargo.zhwchm }}</td>
            <td>{{ jfCargo.hwmch }}</td>
            <td>{{ jfCargo.gq }}</td>
            <td>{{ jfCargo.shl }}</td>
            <td>{{ jfCargo.syl }}</td>
            <td>
              <div v-if="jfCargo.master">
                <router-link :to="{ name: 'JfWorkView', params: { jfWorkId: jfCargo.master.id } }">{{ jfCargo.master.id }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'JfCargoView', params: { jfCargoId: jfCargo.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'JfCargoEdit', params: { jfCargoId: jfCargo.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(jfCargo)"
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
        ><span id="rzbidApp.jfCargo.delete.question" data-cy="jfCargoDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-jfCargo-heading" v-text="$t('rzbidApp.jfCargo.delete.question', { id: removeId })">
          Are you sure you want to delete this Jf Cargo?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-jfCargo"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeJfCargo()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./jf-cargo.component.ts"></script>
