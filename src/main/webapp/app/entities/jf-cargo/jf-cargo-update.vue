<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="rzbidApp.jfCargo.home.createOrEditLabel"
          data-cy="JfCargoCreateUpdateHeading"
          v-text="$t('rzbidApp.jfCargo.home.createOrEditLabel')"
        >
          Create or edit a JfCargo
        </h2>
        <div>
          <div class="form-group" v-if="jfCargo.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="jfCargo.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfCargo.rq')" for="jf-cargo-rq">Rq</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="jf-cargo-rq"
                  v-model="$v.jfCargo.rq.$model"
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
                id="jf-cargo-rq"
                data-cy="rq"
                type="text"
                class="form-control"
                name="rq"
                :class="{ valid: !$v.jfCargo.rq.$invalid, invalid: $v.jfCargo.rq.$invalid }"
                v-model="$v.jfCargo.rq.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfCargo.zygsdm')" for="jf-cargo-zygsdm">Zygsdm</label>
            <input
              type="text"
              class="form-control"
              name="zygsdm"
              id="jf-cargo-zygsdm"
              data-cy="zygsdm"
              :class="{ valid: !$v.jfCargo.zygsdm.$invalid, invalid: $v.jfCargo.zygsdm.$invalid }"
              v-model="$v.jfCargo.zygsdm.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfCargo.hth')" for="jf-cargo-hth">Hth</label>
            <input
              type="text"
              class="form-control"
              name="hth"
              id="jf-cargo-hth"
              data-cy="hth"
              :class="{ valid: !$v.jfCargo.hth.$invalid, invalid: $v.jfCargo.hth.$invalid }"
              v-model="$v.jfCargo.hth.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfCargo.zywtrid')" for="jf-cargo-zywtrid">Zywtrid</label>
            <input
              type="number"
              class="form-control"
              name="zywtrid"
              id="jf-cargo-zywtrid"
              data-cy="zywtrid"
              :class="{ valid: !$v.jfCargo.zywtrid.$invalid, invalid: $v.jfCargo.zywtrid.$invalid }"
              v-model.number="$v.jfCargo.zywtrid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfCargo.zywtr')" for="jf-cargo-zywtr">Zywtr</label>
            <input
              type="text"
              class="form-control"
              name="zywtr"
              id="jf-cargo-zywtr"
              data-cy="zywtr"
              :class="{ valid: !$v.jfCargo.zywtr.$invalid, invalid: $v.jfCargo.zywtr.$invalid }"
              v-model="$v.jfCargo.zywtr.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfCargo.zhwchm')" for="jf-cargo-zhwchm">Zhwchm</label>
            <input
              type="text"
              class="form-control"
              name="zhwchm"
              id="jf-cargo-zhwchm"
              data-cy="zhwchm"
              :class="{ valid: !$v.jfCargo.zhwchm.$invalid, invalid: $v.jfCargo.zhwchm.$invalid }"
              v-model="$v.jfCargo.zhwchm.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfCargo.hwmch')" for="jf-cargo-hwmch">Hwmch</label>
            <input
              type="text"
              class="form-control"
              name="hwmch"
              id="jf-cargo-hwmch"
              data-cy="hwmch"
              :class="{ valid: !$v.jfCargo.hwmch.$invalid, invalid: $v.jfCargo.hwmch.$invalid }"
              v-model="$v.jfCargo.hwmch.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfCargo.gq')" for="jf-cargo-gq">Gq</label>
            <input
              type="text"
              class="form-control"
              name="gq"
              id="jf-cargo-gq"
              data-cy="gq"
              :class="{ valid: !$v.jfCargo.gq.$invalid, invalid: $v.jfCargo.gq.$invalid }"
              v-model="$v.jfCargo.gq.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfCargo.shl')" for="jf-cargo-shl">Shl</label>
            <input
              type="number"
              class="form-control"
              name="shl"
              id="jf-cargo-shl"
              data-cy="shl"
              :class="{ valid: !$v.jfCargo.shl.$invalid, invalid: $v.jfCargo.shl.$invalid }"
              v-model.number="$v.jfCargo.shl.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfCargo.syl')" for="jf-cargo-syl">Syl</label>
            <input
              type="number"
              class="form-control"
              name="syl"
              id="jf-cargo-syl"
              data-cy="syl"
              :class="{ valid: !$v.jfCargo.syl.$invalid, invalid: $v.jfCargo.syl.$invalid }"
              v-model.number="$v.jfCargo.syl.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfCargo.master')" for="jf-cargo-master">Master</label>
            <select class="form-control" id="jf-cargo-master" data-cy="master" name="master" v-model="jfCargo.master">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="jfCargo.master && jfWorkOption.id === jfCargo.master.id ? jfCargo.master : jfWorkOption"
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
            :disabled="$v.jfCargo.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./jf-cargo-update.component.ts"></script>
