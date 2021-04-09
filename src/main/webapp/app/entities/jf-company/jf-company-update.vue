<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="rzbidApp.jfCompany.home.createOrEditLabel"
          data-cy="JfCompanyCreateUpdateHeading"
          v-text="$t('rzbidApp.jfCompany.home.createOrEditLabel')"
        >
          Create or edit a JfCompany
        </h2>
        <div>
          <div class="form-group" v-if="jfCompany.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="jfCompany.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfCompany.name')" for="jf-company-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="jf-company-name"
              data-cy="name"
              :class="{ valid: !$v.jfCompany.name.$invalid, invalid: $v.jfCompany.name.$invalid }"
              v-model="$v.jfCompany.name.$model"
            />
            <div v-if="$v.jfCompany.name.$anyDirty && $v.jfCompany.name.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.jfCompany.name.maxLength"
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
            :disabled="$v.jfCompany.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./jf-company-update.component.ts"></script>
