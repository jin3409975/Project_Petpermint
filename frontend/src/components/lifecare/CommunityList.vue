<script setup>
import CommunityUpdate from '../lifecare/CommunityUpdate.vue'
import CommunityComment from '../lifecare/CommunityComment.vue'
import { ref, onBeforeMount, onMounted, watch } from 'vue'
import { useRouter } from 'vue-router'
import { useCommunityStore } from '@/stores/community'
import { useAccountStore } from '@/stores/account'

const props = defineProps(['article', 'user'])
const community_stores = useCommunityStore()
const account_stores = useAccountStore()

onBeforeMount(async () => {
  await props.article.forEach((article) => {
    if (article.registTime) {
      const originalDate = new Date(article.registTime)

      // Subtract 9 hours
      const adjustedDate = new Date(originalDate.getTime() + 9 * 60 * 60 * 1000)

      // Format the adjusted date as a string
      article.adjustedDate = adjustedDate.toLocaleString() // Adjust the formatting as needed
    }
  })
})

const userId = ref()
const writer = ref()

const likes = ref(0)
const commentcount = ref(0)

const picture = ref('/assets/img/default_profile.png')

const router = useRouter()
const isNew = ref()

const checkLike = async () => {
  if (userId.value == null) {
    alert('로그인 후 이용해주세요')
  } else {
    const checker = await community_stores.communitylikecheck(props.article.postId, userId.value)
    if (checker === 'Fail') {
      await community_stores.communitylikecancel(props.article.postId, userId.value)
      likes.value--
    } else {
      await community_stores.communitylike(props.article.postId, userId.value)
      likes.value++
    }
  }
}

onMounted(async () => {
  userId.value = localStorage.useremail
  writer.value = community_stores.communitydetail(props.article.postId)
  isNew.value = props.article.new

  time.value = dateConvert(
    props.article.registTime.substring(0, props.article.registTime.length - 2)
  )
  likes.value = props.article.likes
  await community_stores.commentlist(props.article.postId)

  commentcount.value = community_stores.comments.length
})

function dateConvert(createdAt) {
  var date = new Date(createdAt)
  const adjustedCreatedAt = new Date(date.getTime() + 9 * 60 * 60 * 1000)

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
  await account_stores.getnormalprofile(newId)
  writer.value = await account_stores.userdata
  if (writer.value.picture != 0 && writer.value.picture != null)
    picture.value = writer.value.picture
}

const time = ref('0일 전')
watch(
  () => props.article.registTime,
  (newTime) => {
    let temp = new Date(newTime)
    time.value = dateConvert(temp)
  }
)

const showComments = ref(false)
const toggleComments = () => {
  showComments.value = !showComments.value
}

const del = () => {
  let deleted = community_stores.communitydelete(props.article.postId)
  alert('게시물을 삭제합니다')
  location.reload()
}
</script>

<template>
  <v-card
    class="card"
    :title="article.userName"
    :subtitle="time"
    :style="{
      borderRadius: '20px',
      height: isNew == null ? '' : '200px',
      background: isNew == null ? 'white' : '#c0d0c0'
    }"
  >
    <template v-if="isNew == null" v-slot:prepend>
      <v-avatar size="50" style="margin-left: 15px; margin-top: 10px; margin-right: 5px">
        <img v-if="article.picture !== '0'" :src="article.picture" style="max-width: 100%" />
        <v-else>
          <img src="/assets/img/default_profile.png" style="max-width: 100%" />
        </v-else>
      </v-avatar>
    </template>
    <template v-if="isNew != null" v-slot:prepend> &nbsp;&nbsp;&nbsp;</template>
    <template v-slot:append>
      <v-icon icon="mdi-new-box" color="white"></v-icon>
    </template>

    <v-card-actions style="padding-right: 10px">
      <v-col>
        <v-row>
          <div style="margin-left: 25px; margin-right: 25px">
            <span style="font-size: 17px">{{ article.content }}</span>
          </div>
        </v-row>
        <v-row class="d-flex justify-content-center" style="margin-top: 20px">
          <img class="mx-auto" :src="article.url" style="max-width: 60%; border-radius: 10px" />
        </v-row>
        <v-row>
          <v-col>
            <div
              class="button-group"
              style="
                display: flex;
                align-items: center;
                justify-content: space-between; /* Adjusted */
                margin-top: 20px;
              "
            >
              <!-- Left Aligned Buttons: Like Button and Comments Toggle -->

              <div v-if="isNew == null" style="display: flex; gap: 20px">
                <div>
                  <v-btn
                    variant="outlined"
                    @click="checkLike"
                    density="comfortable"
                    height="43px"
                    color="grey"
                    prepend-icon="mdi-heart"
                    class="heart5"
                    ><span style="color: black">{{ likes }}</span></v-btn
                  >
                </div>
                <div>
                  <v-btn
                    variant="outlined"
                    class="comment"
                    color="grey"
                    @click="toggleComments"
                    density="comfortable"
                    height="43px"
                    prepend-icon="mdi-message-reply-text"
                    size="large"
                    ><span style="color: black">{{ commentcount }}</span></v-btn
                  >
                </div>
              </div>

              <!-- Right Aligned Buttons: Update and Delete -->
              <div style="display: flex; gap: 10px">
                <CommunityUpdate
                  v-if="article.userId == userId"
                  :article="article"
                  :user="user"
                ></CommunityUpdate>
                <v-btn
                  v-if="article.userId == userId"
                  @click="del"
                  color="grey"
                  density="comfortable"
                  icon="mdi-delete"
                ></v-btn>
              </div>
            </div>
          </v-col>
        </v-row>
      </v-col>
    </v-card-actions>
    <CommunityComment v-if="showComments" :article="article" :user="user"></CommunityComment>
  </v-card>
</template>

<style scoped>
:deep(.comment .mdi:before) {
  color: rgb(137, 186, 247);
}
:deep(.heart5 .mdi:before) {
  color: red;
}
:deep(.v-btn .v-icon) {
  --v-icon-size-multiplier: 1.15;
}
.card {
  margin-bottom: 30px;
  border: 1px solid #e0e0e0;
}
a {
  text-decoration: none;
}
p {
  margin: 0px;
}
:deep(.v-card-item .v-card-title) {
  padding-top: 13px;
}
:deep(.v-card-title) {
  font-size: 18px;
  font-weight: bold;
}
</style>
