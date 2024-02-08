<script setup>
import { useRoute, useRouter } from 'vue-router'
import { ref, onMounted, watch } from 'vue'
import { useCommunityStore } from '@/stores/community'
import { useAccountStore } from '@/stores/account'

const props = defineProps(['article', 'user'])
const route = useRoute()
const community_stores = useCommunityStore()
const account_stores = useAccountStore()

const user = ref()
const userId = ref(localStorage.useremail)
const writer = ref()
const comment = ref([])
const com_temp = ref([])
const commentcount = ref()

const likes = ref(0)

const getComments = async () => {
  console.log(props.article.postId)
  await community_stores.commentlist(props.article.postId)
  comment.value = community_stores.comments

  comment.value = community_stores.comments.map((commentItem) => {
    const temp = new Date(commentItem.registTime)
    commentItem.registTime = dateConvert(temp)
    return commentItem
  })

  commentcount.value = comment.value.length
}

function dateConvert(createdAt) {
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

const writeComments = () => {
  let postId = props.article.postId

  let result = community_stores.commentwrite(postId, userId.value, com_temp.value)

  location.reload()
}

const deleteComment = (commentNo) => {
  let postId = props.article.postId
  let result = community_stores.commentdelete(commentNo, postId)

  location.reload()
}

onMounted(async (article) => {
  console.log(article)
  await getComments()
  user.value = props.user
})

watch(
  () => props.article,
  (newArticle) => {
    console.log('Article changed:', newArticle)
    getComments()
  }
)
</script>

<template>
  <v-container fluid class="d-flex justify-center align-center" style="max-width: 50%">
    <!-- 댓글 목록 -->
    <v-col>
      <v-row>
        <div id="comment-area" class="col-lg-12 col-md-6 col-sm-12">
          <h5>댓글 {{ commentcount }}개</h5>
          <v-card v-for="com in comment" :key="com.commentNo" class="mb-2">
            <v-card-title>
              <div class="d-flex justify-space-between align-center">
                <h5 class="mr-2">{{ com.userId }}</h5>
                <v-btn
                  v-if="userId == com.userId && !com.delete"
                  @click="deleteComment(com.commentNo)"
                  icon
                  color="red"
                >
                  <v-icon>mdi-delete-forever</v-icon>
                </v-btn>
              </div>
            </v-card-title>

            <template v-if="com.delete">
              <p style="margin-left: 20px">삭제된 댓글입니다.</p>
            </template>
            <template v-else>
              <p style="margin-left: 20px">{{ com.content }}</p>
            </template>

            <v-card-actions>
              <p class="mb-0" style="margin-left: 10px">{{ com.registTime }}</p>
            </v-card-actions>
          </v-card>
        </div>
      </v-row>
      <v-row>
        <template v-if="userId != null">
          <form id="comment" class="col-lg-12 col-md-10 col-sm-12">
            <div class="input-group mb-3">
              <input type="hidden" id="articleno" :value="article.postId" />
              <input type="hidden" id="nowid" :value="userId" />
              <span class="input-group-text">{{ userId }}</span>
              <div class="form-floating">
                <textarea
                  class="form-control"
                  placeholder="Leave a comment here"
                  id="floatingTextarea"
                  style="height: 150px"
                  v-model="com_temp"
                ></textarea>
                <label for="floatingInputGroup1">댓글</label>
              </div>
              <button type="button" class="btn btn-outline-dark" @click="writeComments">
                작성
              </button>
            </div>
          </form>
        </template>
      </v-row>
    </v-col>
  </v-container>
</template>

<style scoped></style>
