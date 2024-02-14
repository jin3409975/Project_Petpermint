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

const picture = ref('/assets/img/default_profile.png')

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

const del = async () => {
  let deleted = await community_stores.communitydelete(props.article.postId)
  alert('게시물을 삭제합니다')
  router.push({ name: 'lifecare-community-list' })
}

const navigateToCommunity = () => {
  router.push({ name: 'lifecare-community-list' })
}

const dateConvert = (createdAt) => {
  const adjustedCreatedAt = new Date(createdAt.getTime() + 9 * 60 * 60 * 1000)

  const milliSeconds = new Date() - adjustedCreatedAt
  const seconds = milliSeconds / 1000
  if (seconds < 60) return `방금 전`
  const minutes = seconds / 60
  if (minutes < 60) return `${Math.floor(minutes)}분 전`
  const hours = minutes / 60
  if (hours < 24) return `${Math.floor(hours)}시간 전`
  const days = hours / 24
  if (days < 7) return `${Math.floor(days)}일 전`
  const weeks = days / 7
  if (weeks < 5) return `${Math.floor(weeks)}주 전`
  const months = days / 30
  if (months < 12) return `${Math.floor(months)}개월 전`
  const years = days / 365
  return `${Math.floor(years)}년 전`
}

onMounted(async () => {
  userId.value = localStorage.useremail
  writer.value = await community_stores.communitydetail(props.article.postId)
})

watch(
  () => props.article.likes,
  (newLikes) => {
    likes.value = newLikes || 0
  }
)

watch(
  () => props.article.userId,
  (newId) => {
    fetchData(newId)
  }
)

const fetchData = async (newId) => {
  console.log(newId)
  await account_stores.getnormalprofile(newId)
  writer.value = await account_stores.userdata
  console.log(writer.value)
  console.log(writer.value.picture)
  if (writer.value.picture != 0 && writer.value.picture != null)
    picture.value = writer.value.picture
}

const time = ref('0일 전')
watch(
  () => props.article.registTime,
  (newTime) => {
    console.log(newTime)
    let temp = new Date(newTime)
    console.log(temp)
    time.value = dateConvert(temp)
    console.log(time)
  }
)
</script>

<template>
  <v-container fluid class="w-60">
    <v-col class="text-center">
      <v-row>
        <v-col>
          <img :src="picture" style="width: 50px; height: 50px; border-radius: 50%" />
          <span
            ><b>&nbsp; {{ time }}</b></span
          >
        </v-col>
        <v-col style="margin-top: 15px">
          <row>
            <v-btn
              @click="checkLike"
              style="margin-bottom: 5px"
              density="compact"
              icon="mdi-heart"
            ></v-btn>
            <span>
              <b> &nbsp; 좋아요 {{ likes }}개</b>
            </span>
          </row>
          <row>
            &nbsp;
            <v-btn
              @click="navigateToCommunity"
              style="margin-bottom: 5px"
              icon="mdi-arrow-left-bold"
            ></v-btn>
          </row>
        </v-col>
      </v-row>
      <v-row>
        <div class="mx-auto">{{ article.content }}</div>
      </v-row>

      <v-row>
        <v-col class="text-center" style="max-width: 60%; width: 100%; margin: 0 auto">
          <img
            v-for="(imageUrl, index) in article.urls"
            :src="imageUrl"
            :alt="'Image ' + (index + 1)"
            :key="index"
            style="max-width: 50%"
          />
        </v-col>
      </v-row>

      <v-row justify="space-between">
        <v-col>
          <b>조회수 {{ article.hits }}개</b>
        </v-col>
        <v-col>
          <CommunityUpdate
            v-if="article.userId == userId"
            :article="article"
            :user="user"
          ></CommunityUpdate>
          <v-btn
            v-if="article.userId == userId"
            @click="del"
            style="margin-top: -45px; margin-right: -50px"
            density="compact"
            icon="mdi-delete"
          ></v-btn>
        </v-col>
      </v-row>
    </v-col>
  </v-container>
</template>

<style scoped></style>
