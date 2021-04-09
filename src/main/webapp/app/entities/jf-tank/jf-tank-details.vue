<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="jfTank">
        <h2 class="jh-entity-heading" data-cy="jfTankDetailsHeading">
          <span v-text="$t('rzbidApp.jfTank.detail.title')">JfTank</span> {{ jfTank.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('rzbidApp.jfTank.zone')">Zone</span>
          </dt>
          <dd>
            <span>{{ jfTank.zone }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfTank.code')">Code</span>
          </dt>
          <dd>
            <span>{{ jfTank.code }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfTank.master')">Master</span>
          </dt>
          <dd>
            <div v-if="jfTank.master">
              <router-link :to="{ name: 'JfWorkView', params: { jfWorkId: jfTank.master.id } }">{{ jfTank.master.id }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="jfTank.id" :to="{ name: 'JfTankEdit', params: { jfTankId: jfTank.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./jf-tank-details.component.ts"></script>
