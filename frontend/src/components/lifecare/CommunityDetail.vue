<script setup>
import CommunityUpdate from '../lifecare/CommunityUpdate.vue'
import { useRouter } from 'vue-router'
import { ref, onMounted, watch } from 'vue'
import { useCommunityStore } from '@/stores/community'
import { useAccountStore } from '@/stores/account'

const props = defineProps(['article', 'user'])
const community_stores = useCommunityStore()
const account_stores = useAccountStore()

const userId = ref()
const writer = ref()

const likes = ref(0)

const router = useRouter()
const checkLike = async () => {
  if (userId.value == null) {
    alert('로그인 후 이용해주세요')
  } else {
    let checker = await community_stores.communitylikecheck(props.article.postId, userId.value)
    let ch = await community_stores.likecheck
    console.log(ch)
    if (ch == 'Fail') alert('이미 좋아요 하셨습니다')
    else {
      await community_stores.communitylike(props.article.postId, userId.value)
      likes.value++
    }
  }
}

const del = () => {
  let deleted = community_stores.communitydelete(props.article.postId)
  alert('게시물을 삭제합니다')
  router.push({ name: 'lifecare-community-list' })
}

const navigateToCommunity = () => {
  router.push({ name: 'lifecare-community-list' })
}

onMounted(() => {
  userId.value = localStorage.useremail
  console.log(props.article)
  writer.value = community_stores.communitydetail(props.article.postId)
  console.log(writer.value)
})

watch(
  () => props.article.likes,
  (newLikes) => {
    likes.value = newLikes || 0
  }
)
</script>

<template>
  <v-container fluid class="w-50">
    <img v-if="article.picture !== '0'" :src="article.picture" style="max-width: 10%" />
    <v-else>
      <img src="/assets/img/default_profile.png" style="max-width: 10%" />
    </v-else>
    <span>{{ article.registTime }}</span>
    <div class="mx-auto">{{ article.content }}</div>
    <img :src="article.url" />
    <img
      v-for="(imageUrl, index) in article.urls"
      :src="imageUrl"
      :alt="'Image ' + (index + 1)"
      :key="index"
      style="max-width: 50%"
    />
    좋아요: {{ likes }} 조회수: {{ article.hits }}
    <v-btn @click="checkLike">좋아요</v-btn>
    <v-btn @click="navigateToCommunity">돌아가기</v-btn>
    <CommunityUpdate
      v-if="article.userId == userId"
      :article="article"
      :user="user"
    ></CommunityUpdate>
    <v-btn v-if="article.userId == userId" @click="del">삭제</v-btn>
  </v-container>
</template>

<style scoped></style>
