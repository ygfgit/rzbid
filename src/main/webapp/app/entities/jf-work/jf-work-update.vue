<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="rzbidApp.jfWork.home.createOrEditLabel"
          data-cy="JfWorkCreateUpdateHeading"
          v-text="$t('rzbidApp.jfWork.home.createOrEditLabel')"
        >
          Create or edit a JfWork
        </h2>
        <div>
          <div class="form-group" v-if="jfWork.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="jfWork.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfWork.hwmch')" for="jf-work-hwmch">Hwmch</label>
            <input
              type="text"
              class="form-control"
              name="hwmch"
              id="jf-work-hwmch"
              data-cy="hwmch"
              :class="{ valid: !$v.jfWork.hwmch.$invalid, invalid: $v.jfWork.hwmch.$invalid }"
              v-model="$v.jfWork.hwmch.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfWork.jhsl')" for="jf-work-jhsl">Jhsl</label>
            <input
              type="number"
              class="form-control"
              name="jhsl"
              id="jf-work-jhsl"
              data-cy="jhsl"
              :class="{ valid: !$v.jfWork.jhsl.$invalid, invalid: $v.jfWork.jhsl.$invalid }"
              v-model.number="$v.jfWork.jhsl.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfWork.bz')" for="jf-work-bz">Bz</label>
            <input
              type="text"
              class="form-control"
              name="bz"
              id="jf-work-bz"
              data-cy="bz"
              :class="{ valid: !$v.jfWork.bz.$invalid, invalid: $v.jfWork.bz.$invalid }"
              v-model="$v.jfWork.bz.$model"
            />
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
            :disabled="$v.jfWork.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./jf-work-update.component.ts"></script>
