<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="rzbidApp.jfMaster.home.createOrEditLabel"
          data-cy="JfMasterCreateUpdateHeading"
          v-text="$t('rzbidApp.jfMaster.home.createOrEditLabel')"
        >
          Create or edit a JfMaster
        </h2>
        <div>
          <div class="form-group" v-if="jfMaster.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="jfMaster.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfMaster.zygs')" for="jf-master-zygs">Zygs</label>
            <input
              type="text"
              class="form-control"
              name="zygs"
              id="jf-master-zygs"
              data-cy="zygs"
              :class="{ valid: !$v.jfMaster.zygs.$invalid, invalid: $v.jfMaster.zygs.$invalid }"
              v-model="$v.jfMaster.zygs.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfMaster.je')" for="jf-master-je">Je</label>
            <input
              type="number"
              class="form-control"
              name="je"
              id="jf-master-je"
              data-cy="je"
              :class="{ valid: !$v.jfMaster.je.$invalid, invalid: $v.jfMaster.je.$invalid }"
              v-model.number="$v.jfMaster.je.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfMaster.she')" for="jf-master-she">She</label>
            <input
              type="number"
              class="form-control"
              name="she"
              id="jf-master-she"
              data-cy="she"
              :class="{ valid: !$v.jfMaster.she.$invalid, invalid: $v.jfMaster.she.$invalid }"
              v-model.number="$v.jfMaster.she.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfMaster.shhje')" for="jf-master-shhje">Shhje</label>
            <input
              type="number"
              class="form-control"
              name="shhje"
              id="jf-master-shhje"
              data-cy="shhje"
              :class="{ valid: !$v.jfMaster.shhje.$invalid, invalid: $v.jfMaster.shhje.$invalid }"
              v-model.number="$v.jfMaster.shhje.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfMaster.jmje')" for="jf-master-jmje">Jmje</label>
            <input
              type="number"
              class="form-control"
              name="jmje"
              id="jf-master-jmje"
              data-cy="jmje"
              :class="{ valid: !$v.jfMaster.jmje.$invalid, invalid: $v.jfMaster.jmje.$invalid }"
              v-model.number="$v.jfMaster.jmje.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfMaster.jmhje')" for="jf-master-jmhje">Jmhje</label>
            <input
              type="number"
              class="form-control"
              name="jmhje"
              id="jf-master-jmhje"
              data-cy="jmhje"
              :class="{ valid: !$v.jfMaster.jmhje.$invalid, invalid: $v.jfMaster.jmhje.$invalid }"
              v-model.number="$v.jfMaster.jmhje.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfMaster.idHw')" for="jf-master-idHw">Id Hw</label>
            <input
              type="number"
              class="form-control"
              name="idHw"
              id="jf-master-idHw"
              data-cy="idHw"
              :class="{ valid: !$v.jfMaster.idHw.$invalid, invalid: $v.jfMaster.idHw.$invalid }"
              v-model.number="$v.jfMaster.idHw.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfMaster.htid')" for="jf-master-htid">Htid</label>
            <input
              type="number"
              class="form-control"
              name="htid"
              id="jf-master-htid"
              data-cy="htid"
              :class="{ valid: !$v.jfMaster.htid.$invalid, invalid: $v.jfMaster.htid.$invalid }"
              v-model.number="$v.jfMaster.htid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfMaster.mb')" for="jf-master-mb">Mb</label>
            <input
              type="text"
              class="form-control"
              name="mb"
              id="jf-master-mb"
              data-cy="mb"
              :class="{ valid: !$v.jfMaster.mb.$invalid, invalid: $v.jfMaster.mb.$invalid }"
              v-model="$v.jfMaster.mb.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfMaster.zywtrid')" for="jf-master-zywtrid">Zywtrid</label>
            <input
              type="number"
              class="form-control"
              name="zywtrid"
              id="jf-master-zywtrid"
              data-cy="zywtrid"
              :class="{ valid: !$v.jfMaster.zywtrid.$invalid, invalid: $v.jfMaster.zywtrid.$invalid }"
              v-model.number="$v.jfMaster.zywtrid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfMaster.mark1')" for="jf-master-mark1">Mark 1</label>
            <input
              type="text"
              class="form-control"
              name="mark1"
              id="jf-master-mark1"
              data-cy="mark1"
              :class="{ valid: !$v.jfMaster.mark1.$invalid, invalid: $v.jfMaster.mark1.$invalid }"
              v-model="$v.jfMaster.mark1.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfMaster.mark2')" for="jf-master-mark2">Mark 2</label>
            <input
              type="text"
              class="form-control"
              name="mark2"
              id="jf-master-mark2"
              data-cy="mark2"
              :class="{ valid: !$v.jfMaster.mark2.$invalid, invalid: $v.jfMaster.mark2.$invalid }"
              v-model="$v.jfMaster.mark2.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfMaster.createby')" for="jf-master-createby">Createby</label>
            <input
              type="text"
              class="form-control"
              name="createby"
              id="jf-master-createby"
              data-cy="createby"
              :class="{ valid: !$v.jfMaster.createby.$invalid, invalid: $v.jfMaster.createby.$invalid }"
              v-model="$v.jfMaster.createby.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfMaster.createbyid')" for="jf-master-createbyid">Createbyid</label>
            <input
              type="number"
              class="form-control"
              name="createbyid"
              id="jf-master-createbyid"
              data-cy="createbyid"
              :class="{ valid: !$v.jfMaster.createbyid.$invalid, invalid: $v.jfMaster.createbyid.$invalid }"
              v-model.number="$v.jfMaster.createbyid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfMaster.createon')" for="jf-master-createon">Createon</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="jf-master-createon"
                  v-model="$v.jfMaster.createon.$model"
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
                id="jf-master-createon"
                data-cy="createon"
                type="text"
                class="form-control"
                name="createon"
                :class="{ valid: !$v.jfMaster.createon.$invalid, invalid: $v.jfMaster.createon.$invalid }"
                v-model="$v.jfMaster.createon.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfMaster.createGsid')" for="jf-master-createGsid">Create Gsid</label>
            <input
              type="text"
              class="form-control"
              name="createGsid"
              id="jf-master-createGsid"
              data-cy="createGsid"
              :class="{ valid: !$v.jfMaster.createGsid.$invalid, invalid: $v.jfMaster.createGsid.$invalid }"
              v-model="$v.jfMaster.createGsid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfMaster.createGsmch')" for="jf-master-createGsmch">Create Gsmch</label>
            <input
              type="text"
              class="form-control"
              name="createGsmch"
              id="jf-master-createGsmch"
              data-cy="createGsmch"
              :class="{ valid: !$v.jfMaster.createGsmch.$invalid, invalid: $v.jfMaster.createGsmch.$invalid }"
              v-model="$v.jfMaster.createGsmch.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfMaster.createBmid')" for="jf-master-createBmid">Create Bmid</label>
            <input
              type="number"
              class="form-control"
              name="createBmid"
              id="jf-master-createBmid"
              data-cy="createBmid"
              :class="{ valid: !$v.jfMaster.createBmid.$invalid, invalid: $v.jfMaster.createBmid.$invalid }"
              v-model.number="$v.jfMaster.createBmid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfMaster.createBmm')" for="jf-master-createBmm">Create Bmm</label>
            <input
              type="text"
              class="form-control"
              name="createBmm"
              id="jf-master-createBmm"
              data-cy="createBmm"
              :class="{ valid: !$v.jfMaster.createBmm.$invalid, invalid: $v.jfMaster.createBmm.$invalid }"
              v-model="$v.jfMaster.createBmm.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfMaster.createGwid')" for="jf-master-createGwid">Create Gwid</label>
            <input
              type="text"
              class="form-control"
              name="createGwid"
              id="jf-master-createGwid"
              data-cy="createGwid"
              :class="{ valid: !$v.jfMaster.createGwid.$invalid, invalid: $v.jfMaster.createGwid.$invalid }"
              v-model="$v.jfMaster.createGwid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfMaster.createGwm')" for="jf-master-createGwm">Create Gwm</label>
            <input
              type="text"
              class="form-control"
              name="createGwm"
              id="jf-master-createGwm"
              data-cy="createGwm"
              :class="{ valid: !$v.jfMaster.createGwm.$invalid, invalid: $v.jfMaster.createGwm.$invalid }"
              v-model="$v.jfMaster.createGwm.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfMaster.modifyby')" for="jf-master-modifyby">Modifyby</label>
            <input
              type="text"
              class="form-control"
              name="modifyby"
              id="jf-master-modifyby"
              data-cy="modifyby"
              :class="{ valid: !$v.jfMaster.modifyby.$invalid, invalid: $v.jfMaster.modifyby.$invalid }"
              v-model="$v.jfMaster.modifyby.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfMaster.modifybyid')" for="jf-master-modifybyid">Modifybyid</label>
            <input
              type="number"
              class="form-control"
              name="modifybyid"
              id="jf-master-modifybyid"
              data-cy="modifybyid"
              :class="{ valid: !$v.jfMaster.modifybyid.$invalid, invalid: $v.jfMaster.modifybyid.$invalid }"
              v-model.number="$v.jfMaster.modifybyid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('rzbidApp.jfMaster.modifyon')" for="jf-master-modifyon">Modifyon</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="jf-master-modifyon"
                  v-model="$v.jfMaster.modifyon.$model"
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
                id="jf-master-modifyon"
                data-cy="modifyon"
                type="text"
                class="form-control"
                name="modifyon"
                :class="{ valid: !$v.jfMaster.modifyon.$invalid, invalid: $v.jfMaster.modifyon.$invalid }"
                v-model="$v.jfMaster.modifyon.$model"
              />
            </b-input-group>
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
            :disabled="$v.jfMaster.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./jf-master-update.component.ts"></script>
