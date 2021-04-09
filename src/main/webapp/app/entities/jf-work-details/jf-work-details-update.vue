<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="rzbidApp.jfWorkDetails.home.createOrEditLabel"
          data-cy="JfWorkDetailsCreateUpdateHeading"
          v-text="$t('rzbidApp.jfWorkDetails.home.createOrEditLabel')"
        >
          Create or edit a JfWorkDetails
        </h2>
        <div>
          <div class="form-group" v-if="jfWorkDetails.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="jfWorkDetails.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfWorkDetails.rq')" for="jf-work-details-rq">Rq</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="jf-work-details-rq"
                  v-model="$v.jfWorkDetails.rq.$model"
                  name="rq"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="jf-work-details-rq"
                data-cy="rq"
                type="text"
                class="form-control"
                name="rq"
                :class="{ valid: !$v.jfWorkDetails.rq.$invalid, invalid: $v.jfWorkDetails.rq.$invalid }"
                v-model="$v.jfWorkDetails.rq.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfWorkDetails.lb')" for="jf-work-details-lb">Lb</label>
            <input
              type="text"
              class="form-control"
              name="lb"
              id="jf-work-details-lb"
              data-cy="lb"
              :class="{ valid: !$v.jfWorkDetails.lb.$invalid, invalid: $v.jfWorkDetails.lb.$invalid }"
              v-model="$v.jfWorkDetails.lb.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfWorkDetails.shl')" for="jf-work-details-shl">Shl</label>
            <input
              type="number"
              class="form-control"
              name="shl"
              id="jf-work-details-shl"
              data-cy="shl"
              :class="{ valid: !$v.jfWorkDetails.shl.$invalid, invalid: $v.jfWorkDetails.shl.$invalid }"
              v-model.number="$v.jfWorkDetails.shl.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfWorkDetails.gq')" for="jf-work-details-gq">Gq</label>
            <input
              type="text"
              class="form-control"
              name="gq"
              id="jf-work-details-gq"
              data-cy="gq"
              :class="{ valid: !$v.jfWorkDetails.gq.$invalid, invalid: $v.jfWorkDetails.gq.$invalid }"
              v-model="$v.jfWorkDetails.gq.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfWorkDetails.syl')" for="jf-work-details-syl">Syl</label>
            <input
              type="number"
              class="form-control"
              name="syl"
              id="jf-work-details-syl"
              data-cy="syl"
              :class="{ valid: !$v.jfWorkDetails.syl.$invalid, invalid: $v.jfWorkDetails.syl.$invalid }"
              v-model.number="$v.jfWorkDetails.syl.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfWorkDetails.master')" for="jf-work-details-master">Master</label>
            <select class="form-control" id="jf-work-details-master" data-cy="master" name="master" v-model="jfWorkDetails.master">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="jfWorkDetails.master && jfWorkOption.id === jfWorkDetails.master.id ? jfWorkDetails.master : jfWorkOption"
                v-for="jfWorkOption in jfWorks"
                :key="jfWorkOption.id"
              >
                {{ jfWorkOption.id }}
              </option>
            </select>
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
            :disabled="$v.jfWorkDetails.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./jf-work-details-update.component.ts"></script>
