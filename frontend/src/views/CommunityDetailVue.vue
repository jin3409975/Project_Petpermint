<script setup>
import CommunityDetail from '../components/lifecare/CommunityDetail.vue'
import { useCommunityStore } from '@/stores/community'
import { useRoute, useRouter } from 'vue-router'
import { ref, onMounted } from 'vue'

const stores = useCommunityStore()
const article = ref([])
const route = useRoute()
const router = useRouter()

const { postId } = route.params

const getCommunityDetail = async function (postId) {
  try {
    article.value = await stores.communitydetail(postId)
  } catch (error) {
    console.error('Error fetching community detail:', error)
  }
}

onMounted(() => {
  getCommunityDetail(postId)
})
</script>

<template>
  <v-container fluid class="w-80">
    <CommunityDetail :article="article"></CommunityDetail>
  </v-container>
</template>

<style></style>
