<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="rzbidApp.btJfJtJfmx.home.createOrEditLabel"
          data-cy="BtJfJtJfmxCreateUpdateHeading"
          v-text="$t('rzbidApp.btJfJtJfmx.home.createOrEditLabel')"
        >
          Create or edit a BtJfJtJfmx
        </h2>
        <div>
          <div class="form-group" v-if="btJfJtJfmx.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="btJfJtJfmx.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.btJfJtJfmx.zygs')" for="bt-jf-jt-jfmx-zygs">Zygs</label>
            <input
              type="number"
              class="form-control"
              name="zygs"
              id="bt-jf-jt-jfmx-zygs"
              data-cy="zygs"
              :class="{ valid: !$v.btJfJtJfmx.zygs.$invalid, invalid: $v.btJfJtJfmx.zygs.$invalid }"
              v-model.number="$v.btJfJtJfmx.zygs.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.btJfJtJfmx.zyetrid')" for="bt-jf-jt-jfmx-zyetrid">Zyetrid</label>
            <input
              type="number"
              class="form-control"
              name="zyetrid"
              id="bt-jf-jt-jfmx-zyetrid"
              data-cy="zyetrid"
              :class="{ valid: !$v.btJfJtJfmx.zyetrid.$invalid, invalid: $v.btJfJtJfmx.zyetrid.$invalid }"
              v-model.number="$v.btJfJtJfmx.zyetrid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.btJfJtJfmx.flmch')" for="bt-jf-jt-jfmx-flmch">Flmch</label>
            <input
              type="text"
              class="form-control"
              name="flmch"
              id="bt-jf-jt-jfmx-flmch"
              data-cy="flmch"
              :class="{ valid: !$v.btJfJtJfmx.flmch.$invalid, invalid: $v.btJfJtJfmx.flmch.$invalid }"
              v-model="$v.btJfJtJfmx.flmch.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.btJfJtJfmx.fl')" for="bt-jf-jt-jfmx-fl">Fl</label>
            <input
              type="number"
              class="form-control"
              name="fl"
              id="bt-jf-jt-jfmx-fl"
              data-cy="fl"
              :class="{ valid: !$v.btJfJtJfmx.fl.$invalid, invalid: $v.btJfJtJfmx.fl.$invalid }"
              v-model.number="$v.btJfJtJfmx.fl.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.btJfJtJfmx.shl')" for="bt-jf-jt-jfmx-shl">Shl</label>
            <input
              type="number"
              class="form-control"
              name="shl"
              id="bt-jf-jt-jfmx-shl"
              data-cy="shl"
              :class="{ valid: !$v.btJfJtJfmx.shl.$invalid, invalid: $v.btJfJtJfmx.shl.$invalid }"
              v-model.number="$v.btJfJtJfmx.shl.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.btJfJtJfmx.je')" for="bt-jf-jt-jfmx-je">Je</label>
            <input
              type="number"
              class="form-control"
              name="je"
              id="bt-jf-jt-jfmx-je"
              data-cy="je"
              :class="{ valid: !$v.btJfJtJfmx.je.$invalid, invalid: $v.btJfJtJfmx.je.$invalid }"
              v-model.number="$v.btJfJtJfmx.je.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.btJfJtJfmx.shlv')" for="bt-jf-jt-jfmx-shlv">Shlv</label>
            <input
              type="number"
              class="form-control"
              name="shlv"
              id="bt-jf-jt-jfmx-shlv"
              data-cy="shlv"
              :class="{ valid: !$v.btJfJtJfmx.shlv.$invalid, invalid: $v.btJfJtJfmx.shlv.$invalid }"
              v-model.number="$v.btJfJtJfmx.shlv.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.btJfJtJfmx.she')" for="bt-jf-jt-jfmx-she">She</label>
            <input
              type="number"
              class="form-control"
              name="she"
              id="bt-jf-jt-jfmx-she"
              data-cy="she"
              :class="{ valid: !$v.btJfJtJfmx.she.$invalid, invalid: $v.btJfJtJfmx.she.$invalid }"
              v-model.number="$v.btJfJtJfmx.she.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.btJfJtJfmx.shhje')" for="bt-jf-jt-jfmx-shhje">Shhje</label>
            <input
              type="number"
              class="form-control"
              name="shhje"
              id="bt-jf-jt-jfmx-shhje"
              data-cy="shhje"
              :class="{ valid: !$v.btJfJtJfmx.shhje.$invalid, invalid: $v.btJfJtJfmx.shhje.$invalid }"
              v-model.number="$v.btJfJtJfmx.shhje.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.btJfJtJfmx.jmje')" for="bt-jf-jt-jfmx-jmje">Jmje</label>
            <input
              type="number"
              class="form-control"
              name="jmje"
              id="bt-jf-jt-jfmx-jmje"
              data-cy="jmje"
              :class="{ valid: !$v.btJfJtJfmx.jmje.$invalid, invalid: $v.btJfJtJfmx.jmje.$invalid }"
              v-model.number="$v.btJfJtJfmx.jmje.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.btJfJtJfmx.startd')" for="bt-jf-jt-jfmx-startd">Startd</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="bt-jf-jt-jfmx-startd"
                  v-model="$v.btJfJtJfmx.startd.$model"
                  name="startd"
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
                id="bt-jf-jt-jfmx-startd"
                data-cy="startd"
                type="text"
                class="form-control"
                name="startd"
                :class="{ valid: !$v.btJfJtJfmx.startd.$invalid, invalid: $v.btJfJtJfmx.startd.$invalid }"
                v-model="$v.btJfJtJfmx.startd.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.btJfJtJfmx.endd')" for="bt-jf-jt-jfmx-endd">Endd</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="bt-jf-jt-jfmx-endd"
                  v-model="$v.btJfJtJfmx.endd.$model"
                  name="endd"
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
                id="bt-jf-jt-jfmx-endd"
                data-cy="endd"
                type="text"
                class="form-control"
                name="endd"
                :class="{ valid: !$v.btJfJtJfmx.endd.$invalid, invalid: $v.btJfJtJfmx.endd.$invalid }"
                v-model="$v.btJfJtJfmx.endd.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.btJfJtJfmx.mark1')" for="bt-jf-jt-jfmx-mark1">Mark 1</label>
            <input
              type="text"
              class="form-control"
              name="mark1"
              id="bt-jf-jt-jfmx-mark1"
              data-cy="mark1"
              :class="{ valid: !$v.btJfJtJfmx.mark1.$invalid, invalid: $v.btJfJtJfmx.mark1.$invalid }"
              v-model="$v.btJfJtJfmx.mark1.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.btJfJtJfmx.mark2')" for="bt-jf-jt-jfmx-mark2">Mark 2</label>
            <input
              type="text"
              class="form-control"
              name="mark2"
              id="bt-jf-jt-jfmx-mark2"
              data-cy="mark2"
              :class="{ valid: !$v.btJfJtJfmx.mark2.$invalid, invalid: $v.btJfJtJfmx.mark2.$invalid }"
              v-model="$v.btJfJtJfmx.mark2.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.btJfJtJfmx.createby')" for="bt-jf-jt-jfmx-createby">Createby</label>
            <input
              type="text"
              class="form-control"
              name="createby"
              id="bt-jf-jt-jfmx-createby"
              data-cy="createby"
              :class="{ valid: !$v.btJfJtJfmx.createby.$invalid, invalid: $v.btJfJtJfmx.createby.$invalid }"
              v-model="$v.btJfJtJfmx.createby.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.btJfJtJfmx.createbyid')" for="bt-jf-jt-jfmx-createbyid"
              >Createbyid</label
            >
            <input
              type="number"
              class="form-control"
              name="createbyid"
              id="bt-jf-jt-jfmx-createbyid"
              data-cy="createbyid"
              :class="{ valid: !$v.btJfJtJfmx.createbyid.$invalid, invalid: $v.btJfJtJfmx.createbyid.$invalid }"
              v-model.number="$v.btJfJtJfmx.createbyid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.btJfJtJfmx.createon')" for="bt-jf-jt-jfmx-createon">Createon</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="bt-jf-jt-jfmx-createon"
                  v-model="$v.btJfJtJfmx.createon.$model"
                  name="createon"
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
                id="bt-jf-jt-jfmx-createon"
                data-cy="createon"
                type="text"
                class="form-control"
                name="createon"
                :class="{ valid: !$v.btJfJtJfmx.createon.$invalid, invalid: $v.btJfJtJfmx.createon.$invalid }"
                v-model="$v.btJfJtJfmx.createon.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.btJfJtJfmx.createGsid')" for="bt-jf-jt-jfmx-createGsid"
              >Create Gsid</label
            >
            <input
              type="text"
              class="form-control"
              name="createGsid"
              id="bt-jf-jt-jfmx-createGsid"
              data-cy="createGsid"
              :class="{ valid: !$v.btJfJtJfmx.createGsid.$invalid, invalid: $v.btJfJtJfmx.createGsid.$invalid }"
              v-model="$v.btJfJtJfmx.createGsid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.btJfJtJfmx.createGsmch')" for="bt-jf-jt-jfmx-createGsmch"
              >Create Gsmch</label
            >
            <input
              type="text"
              class="form-control"
              name="createGsmch"
              id="bt-jf-jt-jfmx-createGsmch"
              data-cy="createGsmch"
              :class="{ valid: !$v.btJfJtJfmx.createGsmch.$invalid, invalid: $v.btJfJtJfmx.createGsmch.$invalid }"
              v-model="$v.btJfJtJfmx.createGsmch.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.btJfJtJfmx.createBmid')" for="bt-jf-jt-jfmx-createBmid"
              >Create Bmid</label
            >
            <input
              type="number"
              class="form-control"
              name="createBmid"
              id="bt-jf-jt-jfmx-createBmid"
              data-cy="createBmid"
              :class="{ valid: !$v.btJfJtJfmx.createBmid.$invalid, invalid: $v.btJfJtJfmx.createBmid.$invalid }"
              v-model.number="$v.btJfJtJfmx.createBmid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.btJfJtJfmx.createBmm')" for="bt-jf-jt-jfmx-createBmm">Create Bmm</label>
            <input
              type="text"
              class="form-control"
              name="createBmm"
              id="bt-jf-jt-jfmx-createBmm"
              data-cy="createBmm"
              :class="{ valid: !$v.btJfJtJfmx.createBmm.$invalid, invalid: $v.btJfJtJfmx.createBmm.$invalid }"
              v-model="$v.btJfJtJfmx.createBmm.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.btJfJtJfmx.createGwid')" for="bt-jf-jt-jfmx-createGwid"
              >Create Gwid</label
            >
            <input
              type="number"
              class="form-control"
              name="createGwid"
              id="bt-jf-jt-jfmx-createGwid"
              data-cy="createGwid"
              :class="{ valid: !$v.btJfJtJfmx.createGwid.$invalid, invalid: $v.btJfJtJfmx.createGwid.$invalid }"
              v-model.number="$v.btJfJtJfmx.createGwid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.btJfJtJfmx.createGwm')" for="bt-jf-jt-jfmx-createGwm">Create Gwm</label>
            <input
              type="text"
              class="form-control"
              name="createGwm"
              id="bt-jf-jt-jfmx-createGwm"
              data-cy="createGwm"
              :class="{ valid: !$v.btJfJtJfmx.createGwm.$invalid, invalid: $v.btJfJtJfmx.createGwm.$invalid }"
              v-model="$v.btJfJtJfmx.createGwm.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.btJfJtJfmx.modifyby')" for="bt-jf-jt-jfmx-modifyby">Modifyby</label>
            <input
              type="text"
              class="form-control"
              name="modifyby"
              id="bt-jf-jt-jfmx-modifyby"
              data-cy="modifyby"
              :class="{ valid: !$v.btJfJtJfmx.modifyby.$invalid, invalid: $v.btJfJtJfmx.modifyby.$invalid }"
              v-model="$v.btJfJtJfmx.modifyby.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.btJfJtJfmx.modifybyid')" for="bt-jf-jt-jfmx-modifybyid"
              >Modifybyid</label
            >
            <input
              type="number"
              class="form-control"
              name="modifybyid"
              id="bt-jf-jt-jfmx-modifybyid"
              data-cy="modifybyid"
              :class="{ valid: !$v.btJfJtJfmx.modifybyid.$invalid, invalid: $v.btJfJtJfmx.modifybyid.$invalid }"
              v-model.number="$v.btJfJtJfmx.modifybyid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.btJfJtJfmx.modifyon')" for="bt-jf-jt-jfmx-modifyon">Modifyon</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="bt-jf-jt-jfmx-modifyon"
                  v-model="$v.btJfJtJfmx.modifyon.$model"
                  name="modifyon"
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
                id="bt-jf-jt-jfmx-modifyon"
                data-cy="modifyon"
                type="text"
                class="form-control"
                name="modifyon"
                :class="{ valid: !$v.btJfJtJfmx.modifyon.$invalid, invalid: $v.btJfJtJfmx.modifyon.$invalid }"
                v-model="$v.btJfJtJfmx.modifyon.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.btJfJtJfmx.master')" for="bt-jf-jt-jfmx-master">Master</label>
            <select class="form-control" id="bt-jf-jt-jfmx-master" data-cy="master" name="master" v-model="btJfJtJfmx.master">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="btJfJtJfmx.master && jfMasterOption.id === btJfJtJfmx.master.id ? btJfJtJfmx.master : jfMasterOption"
                v-for="jfMasterOption in jfMasters"
                :key="jfMasterOption.id"
              >
                {{ jfMasterOption.id }}
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
            :disabled="$v.btJfJtJfmx.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./bt-jf-jt-jfmx-update.component.ts"></script>
