<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="rzbidApp.jfCustomer.home.createOrEditLabel"
          data-cy="JfCustomerCreateUpdateHeading"
          v-text="$t('rzbidApp.jfCustomer.home.createOrEditLabel')"
        >
          Create or edit a JfCustomer
        </h2>
        <div>
          <div class="form-group" v-if="jfCustomer.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="jfCustomer.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfCustomer.name')" for="jf-customer-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="jf-customer-name"
              data-cy="name"
              :class="{ valid: !$v.jfCustomer.name.$invalid, invalid: $v.jfCustomer.name.$invalid }"
              v-model="$v.jfCustomer.name.$model"
            />
            <div v-if="$v.jfCustomer.name.$anyDirty && $v.jfCustomer.name.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.jfCustomer.name.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 50 })"
              >
                This field cannot be longer than 50 characters.
              </small>
            </div>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.jfCustomer.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./jf-customer-update.component.ts"></script>
