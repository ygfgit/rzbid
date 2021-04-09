<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="rzbidApp.jfTarget.home.createOrEditLabel"
          data-cy="JfTargetCreateUpdateHeading"
          v-text="$t('rzbidApp.jfTarget.home.createOrEditLabel')"
        >
          Create or edit a JfTarget
        </h2>
        <div>
          <div class="form-group" v-if="jfTarget.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="jfTarget.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfTarget.flzl')" for="jf-target-flzl">Flzl</label>
            <input
              type="number"
              class="form-control"
              name="flzl"
              id="jf-target-flzl"
              data-cy="flzl"
              :class="{ valid: !$v.jfTarget.flzl.$invalid, invalid: $v.jfTarget.flzl.$invalid }"
              v-model.number="$v.jfTarget.flzl.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfTarget.master')" for="jf-target-master">Master</label>
            <select class="form-control" id="jf-target-master" data-cy="master" name="master" v-model="jfTarget.master">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="jfTarget.master && jfWorkOption.id === jfTarget.master.id ? jfTarget.master : jfWorkOption"
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
            :disabled="$v.jfTarget.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./jf-target-update.component.ts"></script>
