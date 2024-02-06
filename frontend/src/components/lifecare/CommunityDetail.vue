<script setup>
import { useRouter } from 'vue-router'
import { ref, onMounted, watch } from 'vue'
import { useCommunityStore } from '@/stores/community'

const props = defineProps(['article', 'user'])
const community_stores = useCommunityStore()

const userId = ref()

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

const navigateToCommunity = () => {
  router.push({ name: 'lifecare-community-list' })
}

onMounted(() => {
  userId.value = localStorage.useremail
  console.log(props.article.likes)
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
    <div class="mx-auto">{{ article.content }}</div>
    <img :src="article.url" />
    Likes: {{ likes }} Views: {{ article.hits }}
    <v-btn @click="checkLike">Like</v-btn>
    <v-btn @click="navigateToCommunity">Back</v-btn>
  </v-container>
</template>

<style scoped></style>
