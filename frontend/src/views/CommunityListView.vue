<script setup>
import CommunityList from '../components/lifecare/CommunityList.vue'
import CommunityWrite from '../components/lifecare/CommunityWrite.vue'
import { useCommunityStore } from '@/stores/community'
import { useAccountStore } from '@/stores/account'
const account_stores = useAccountStore()
const stores = useCommunityStore()
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import InfiniteLoading from 'v3-infinite-loading'
import 'v3-infinite-loading/lib/style.css'

const articles = ref([])
const postIds = ref([0])
let page = 0

const load = async ($state) => {
  console.log('loading...')
  console.log(postIds.value)
  try {
    const json = await stores.communitylist(page, postIds.value)
    articles.value.push(...json)
    console.log(articles.value)
    json.forEach((element) => {
      postIds.value.push(element.postId)
    })

    $state.loaded()

    page++
  } catch (error) {
    $state.error()
  }
}

const router = useRouter()

const navigateToMain = () => {
  router.push({ name: 'main-home' })
}

onMounted(async () => {})
</script>

<template>
  <v-container fluid class="w-50" style="margin-top: 80px">
    <v-btn @click="navigateToMain" style="margin-bottom: 15px" icon="mdi-home"></v-btn>
    <CommunityWrite></CommunityWrite>
    <CommunityList v-for="article in articles" :key="article.postId" :article="article">
    </CommunityList>
  </v-container>
  <InfiniteLoading @infinite="load" />
</template>

<style></style>
