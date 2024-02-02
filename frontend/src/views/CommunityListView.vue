<script setup>
import CommunityList from '../components/lifecare/CommunityList.vue'
import { useCommunityStore } from '@/stores/community'
const store = useCommunityStore()
import { ref, onMounted } from 'vue'
import axios from 'axios'

const articles = ref()

const getCommunityList = function () {
  store.communitylist()
}

onMounted(() => {
  getCommunityList()
})
</script>

<template>
  <v-container fluid class="w-50">
    <v-btn variant="tonal"> Button </v-btn>
  </v-container>
</template>

<style></style>
