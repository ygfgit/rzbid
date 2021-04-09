<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="jfTarget">
        <h2 class="jh-entity-heading" data-cy="jfTargetDetailsHeading">
          <span v-text="$t('rzbidApp.jfTarget.detail.title')">JfTarget</span> {{ jfTarget.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('rzbidApp.jfTarget.flzl')">Flzl</span>
          </dt>
          <dd>
            <span>{{ jfTarget.flzl }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfTarget.master')">Master</span>
          </dt>
          <dd>
            <div v-if="jfTarget.master">
              <router-link :to="{ name: 'JfWorkView', params: { jfWorkId: jfTarget.master.id } }">{{ jfTarget.master.id }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="jfTarget.id" :to="{ name: 'JfTargetEdit', params: { jfTargetId: jfTarget.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./jf-target-details.component.ts"></script>
