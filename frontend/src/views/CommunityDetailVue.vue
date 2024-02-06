<script setup>
import CommunityDetail from '../components/lifecare/CommunityDetail.vue'
import { useCommunityStore } from '@/stores/community'
import { useAccountStore } from '@/stores/account'
import { useRoute, useRouter } from 'vue-router'
import { ref, onMounted } from 'vue'

const community_stores = useCommunityStore()
const account_stores = useAccountStore()
const article = ref([])
const user = ref()
const route = useRoute()
const router = useRouter()

const { postId } = route.params

const getCommunityDetail = async function (postId) {
  try {
    article.value = await community_stores.communitydetail(postId)
    user.value = await account_stores.userdata

    console.log(account_stores)
  } catch (error) {
    console.error('Error fetching community detail:', error)
  }
}

const increaseHit = async function (postId) {
  try {
    user.value = await community_stores.communityhit(postId)
  } catch (error) {
    console.error('Error fetching community detail:', error)
  }
}

onMounted(async () => {
  await increaseHit(postId)
  getCommunityDetail(postId)
  console.log(user)
})
</script>

<template>
  <v-container fluid class="w-80">
    <CommunityDetail :article="article" :user="user"></CommunityDetail>
  </v-container>
</template>

<style></style>
