<script setup>
import CommunityList from '../components/lifecare/CommunityList.vue'
import { useCommunityStore } from '@/stores/community'
const stores = useCommunityStore()
import { ref, onMounted } from 'vue'

const articles = ref([])

const getCommunityList = async function () {
  try {
    articles.value = await stores.communitylist()
  } catch (error) {
    console.error('Error fetching community list:', error)
  }
}

onMounted(() => {
  getCommunityList()
})
</script>

<template>
  <v-container fluid class="w-50">
    <CommunityList v-for="article in articles" :key="article.postId" :article="article">
    </CommunityList>
  </v-container>
</template>

<style></style>
