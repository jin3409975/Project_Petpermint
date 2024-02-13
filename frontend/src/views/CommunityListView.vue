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

const isThoughtVisible = ref(true)

document.addEventListener('dataEmitted', (event) => {
  // Access the data from the event detail
  const { userId, content, files } = event.detail

  // Do something with the received data
  console.log('Received data:', userId, content, files)
})

const write = (content) => {
  var card = Object()
  card.userName = '추가 됨'
  console.log(getCurrentDateTimeString())
  card.registTime = getCurrentDateTimeString()
  card.content = content
  card.new = 1
  articles.value.unshift(card)
}

function getCurrentDateTimeString() {
  const currentDate = new Date()

  const year = currentDate.getFullYear()
  const month = String(currentDate.getMonth() + 1).padStart(2, '0')
  const day = String(currentDate.getDate()).padStart(2, '0')
  const hours = String(currentDate.getHours()).padStart(2, '0')
  const minutes = String(currentDate.getMinutes()).padStart(2, '0')
  const seconds = String(currentDate.getSeconds()).padStart(2, '0')

  const formattedDateTime = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`

  return formattedDateTime
}
</script>

<template>
  <div v-if="isThoughtVisible" class="thought">
    <div>
      <span><strong>펫닥 커뮤니티에 오신걸 환영합니다!</strong></span>
    </div>
    <br />
    <span>
      여러분의 반려동물 이야기나 유용한 정보를 여러 사람들과 공유해 보세요. 댓글로 의사소통도
      가능하답니다!</span
    >
    <br />
    <v-btn @click="isThoughtVisible = false" variant="text" style="margin-top: 10px"> close </v-btn>
  </div>
  <div class="dog3">
    <div class="heart heart--1"></div>
    <div class="heart heart--2"></div>
    <div class="heart heart--3"></div>
    <div class="heart heart--4"></div>
    <div class="head">
      <div class="year year--left"></div>
      <div class="year year--right"></div>
      <div class="nose"></div>
      <div class="face">
        <div class="eye eye--left"></div>
        <div class="eye eye--right"></div>
        <div class="mouth"></div>
      </div>
    </div>
    <div class="body3">
      <div class="cheast"></div>
      <div class="back"></div>
      <div class="legs">
        <div class="legs__front legs__front--left"></div>
        <div class="legs__front legs__front--right"></div>
        <div class="legs__back legs__back--left"></div>
        <div class="legs__back legs__back--right"></div>
      </div>
      <div class="tail"></div>
    </div>
  </div>
  <v-container fluid class="w-50" style="margin-top: 80px">
    <v-btn @click="navigateToMain" style="margin-bottom: 15px" icon="mdi-home"></v-btn>
    <CommunityWrite @data-send="write"></CommunityWrite>
    <CommunityList v-for="article in articles" :key="article.postId" :article="article">
    </CommunityList>
  </v-container>
  <InfiniteLoading @infinite="load" />
</template>

<style></style>
