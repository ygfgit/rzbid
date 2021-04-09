<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="jfCargo">
        <h2 class="jh-entity-heading" data-cy="jfCargoDetailsHeading">
          <span v-text="$t('rzbidApp.jfCargo.detail.title')">JfCargo</span> {{ jfCargo.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('rzbidApp.jfCargo.rq')">Rq</span>
          </dt>
          <dd>
            <span>{{ jfCargo.rq }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfCargo.zygsdm')">Zygsdm</span>
          </dt>
          <dd>
            <span>{{ jfCargo.zygsdm }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfCargo.hth')">Hth</span>
          </dt>
          <dd>
            <span>{{ jfCargo.hth }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfCargo.zywtrid')">Zywtrid</span>
          </dt>
          <dd>
            <span>{{ jfCargo.zywtrid }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfCargo.zywtr')">Zywtr</span>
          </dt>
          <dd>
            <span>{{ jfCargo.zywtr }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfCargo.zhwchm')">Zhwchm</span>
          </dt>
          <dd>
            <span>{{ jfCargo.zhwchm }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfCargo.hwmch')">Hwmch</span>
          </dt>
          <dd>
            <span>{{ jfCargo.hwmch }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfCargo.gq')">Gq</span>
          </dt>
          <dd>
            <span>{{ jfCargo.gq }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfCargo.shl')">Shl</span>
          </dt>
          <dd>
            <span>{{ jfCargo.shl }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfCargo.syl')">Syl</span>
          </dt>
          <dd>
            <span>{{ jfCargo.syl }}</span>
          </dd>
          <dt>
            <span v-text="$t('rzbidApp.jfCargo.master')">Master</span>
          </dt>
          <dd>
            <div v-if="jfCargo.master">
              <router-link :to="{ name: 'JfWorkView', params: { jfWorkId: jfCargo.master.id } }">{{ jfCargo.master.id }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="jfCargo.id" :to="{ name: 'JfCargoEdit', params: { jfCargoId: jfCargo.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./jf-cargo-details.component.ts"></script>
