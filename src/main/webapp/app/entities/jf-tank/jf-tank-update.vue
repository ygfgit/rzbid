<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="rzbidApp.jfTank.home.createOrEditLabel"
          data-cy="JfTankCreateUpdateHeading"
          v-text="$t('rzbidApp.jfTank.home.createOrEditLabel')"
        >
          Create or edit a JfTank
        </h2>
        <div>
          <div class="form-group" v-if="jfTank.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="jfTank.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfTank.zone')" for="jf-tank-zone">Zone</label>
            <input
              type="text"
              class="form-control"
              name="zone"
              id="jf-tank-zone"
              data-cy="zone"
              :class="{ valid: !$v.jfTank.zone.$invalid, invalid: $v.jfTank.zone.$invalid }"
              v-model="$v.jfTank.zone.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfTank.code')" for="jf-tank-code">Code</label>
            <input
              type="text"
              class="form-control"
              name="code"
              id="jf-tank-code"
              data-cy="code"
              :class="{ valid: !$v.jfTank.code.$invalid, invalid: $v.jfTank.code.$invalid }"
              v-model="$v.jfTank.code.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfTank.master')" for="jf-tank-master">Master</label>
            <select class="form-control" id="jf-tank-master" data-cy="master" name="master" v-model="jfTank.master">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="jfTank.master && jfWorkOption.id === jfTank.master.id ? jfTank.master : jfWorkOption"
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
            :disabled="$v.jfTank.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./jf-tank-update.component.ts"></script>
