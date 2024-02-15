<script setup>
import CommunityDetail from '../../components/lifecare/CommunityDetail.vue'
import CommunityComment from '../../components/lifecare/CommunityComment.vue'
import { useCommunityStore } from '@/stores/community'
import { useAccountStore } from '@/stores/account'
import { useRoute, useRouter } from 'vue-router'
import { ref, onMounted, onBeforeMount } from 'vue'

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

onBeforeMount(async () => {
  await increaseHit(postId)
  await getCommunityDetail(postId)
  console.log(user)
})
</script>

<template>
  <v-container fluid class="w-80" style="margin-top: 90px">
    <CommunityDetail :article="article" :user="user"></CommunityDetail>
    <hr></hr>
    <CommunityComment :article="article" :user="user"></CommunityComment>
  </v-container>
</template>

<style></style>
