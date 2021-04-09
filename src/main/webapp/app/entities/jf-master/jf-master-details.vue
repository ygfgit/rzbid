<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="jfMaster">
        <h2 class="jh-entity-heading" data-cy="jfMasterDetailsHeading">
          <span v-text="$t('rzbidApp.jfMaster.detail.title')">JfMaster</span> {{ jfMaster.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('rzbidApp.jfMaster.zygs')">Zygs</span>
          </dt>
          <dd>
            <span>{{ jfMaster.zygs }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfMaster.je')">Je</span>
          </dt>
          <dd>
            <span>{{ jfMaster.je }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfMaster.she')">She</span>
          </dt>
          <dd>
            <span>{{ jfMaster.she }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfMaster.shhje')">Shhje</span>
          </dt>
          <dd>
            <span>{{ jfMaster.shhje }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfMaster.jmje')">Jmje</span>
          </dt>
          <dd>
            <span>{{ jfMaster.jmje }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfMaster.jmhje')">Jmhje</span>
          </dt>
          <dd>
            <span>{{ jfMaster.jmhje }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfMaster.idHw')">Id Hw</span>
          </dt>
          <dd>
            <span>{{ jfMaster.idHw }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfMaster.htid')">Htid</span>
          </dt>
          <dd>
            <span>{{ jfMaster.htid }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfMaster.mb')">Mb</span>
          </dt>
          <dd>
            <span>{{ jfMaster.mb }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfMaster.zywtrid')">Zywtrid</span>
          </dt>
          <dd>
            <span>{{ jfMaster.zywtrid }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfMaster.mark1')">Mark 1</span>
          </dt>
          <dd>
            <span>{{ jfMaster.mark1 }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfMaster.mark2')">Mark 2</span>
          </dt>
          <dd>
            <span>{{ jfMaster.mark2 }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfMaster.createby')">Createby</span>
          </dt>
          <dd>
            <span>{{ jfMaster.createby }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfMaster.createbyid')">Createbyid</span>
          </dt>
          <dd>
            <span>{{ jfMaster.createbyid }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfMaster.createon')">Createon</span>
          </dt>
          <dd>
            <span>{{ jfMaster.createon }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfMaster.createGsid')">Create Gsid</span>
          </dt>
          <dd>
            <span>{{ jfMaster.createGsid }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfMaster.createGsmch')">Create Gsmch</span>
          </dt>
          <dd>
            <span>{{ jfMaster.createGsmch }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfMaster.createBmid')">Create Bmid</span>
          </dt>
          <dd>
            <span>{{ jfMaster.createBmid }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfMaster.createBmm')">Create Bmm</span>
          </dt>
          <dd>
            <span>{{ jfMaster.createBmm }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfMaster.createGwid')">Create Gwid</span>
          </dt>
          <dd>
            <span>{{ jfMaster.createGwid }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfMaster.createGwm')">Create Gwm</span>
          </dt>
          <dd>
            <span>{{ jfMaster.createGwm }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfMaster.modifyby')">Modifyby</span>
          </dt>
          <dd>
            <span>{{ jfMaster.modifyby }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfMaster.modifybyid')">Modifybyid</span>
          </dt>
          <dd>
            <span>{{ jfMaster.modifybyid }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfMaster.modifyon')">Modifyon</span>
          </dt>
          <dd>
            <span>{{ jfMaster.modifyon }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="jfMaster.id" :to="{ name: 'JfMasterEdit', params: { jfMasterId: jfMaster.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./jf-master-details.component.ts"></script>
