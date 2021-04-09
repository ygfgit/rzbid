<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="jfWorkDetails">
        <h2 class="jh-entity-heading" data-cy="jfWorkDetailsDetailsHeading">
          <span v-text="$t('rzbidApp.jfWorkDetails.detail.title')">JfWorkDetails</span> {{ jfWorkDetails.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('rzbidApp.jfWorkDetails.rq')">Rq</span>
          </dt>
          <dd>
            <span>{{ jfWorkDetails.rq }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfWorkDetails.lb')">Lb</span>
          </dt>
          <dd>
            <span>{{ jfWorkDetails.lb }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfWorkDetails.shl')">Shl</span>
          </dt>
          <dd>
            <span>{{ jfWorkDetails.shl }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfWorkDetails.gq')">Gq</span>
          </dt>
          <dd>
            <span>{{ jfWorkDetails.gq }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfWorkDetails.syl')">Syl</span>
          </dt>
          <dd>
            <span>{{ jfWorkDetails.syl }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfWorkDetails.master')">Master</span>
          </dt>
          <dd>
            <div v-if="jfWorkDetails.master">
              <router-link :to="{ name: 'JfWorkView', params: { jfWorkId: jfWorkDetails.master.id } }">{{
                jfWorkDetails.master.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="jfWorkDetails.id"
          :to="{ name: 'JfWorkDetailsEdit', params: { jfWorkDetailsId: jfWorkDetails.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./jf-work-details-details.component.ts"></script>
