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
  //console.log(props.article.postId)
  await community_stores.commentlist(props.article.postId)
  comment.value = community_stores.comments

  comment.value = community_stores.comments.map((commentItem) => {
    const temp = new Date(commentItem.registTime)
    commentItem.registTime = dateConvert(temp)
    return commentItem
  })

  commentcount.value = comment.value.length
}

function dateConvert(date) {
  var createdAt = new Date(date)
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
  var writtenComment = new Object()
  writtenComment.postId = postId
  writtenComment.userId = userId.value
  writtenComment.registTime = '방금 전'
  writtenComment.content = com_temp.value
  comment.value.push(writtenComment)
  commentcount.value++
  com_temp.value = ''
}

const deleteComment = (comment) => {
  let commentNo = comment.commentNo
  let postId = props.article.postId
  comment.content = '삭제된 댓글입니다.'
  let result = community_stores.commentdelete(commentNo, postId)
}

onMounted(async (article) => {
  //console.log(article)
  await getComments()
  user.value = props.user
})

watch(
  () => props.article,
  (newArticle) => {
    //console.log('Article changed:', newArticle)
    getComments()
  }
)
</script>

<template>
  <v-container
    fluid
    class="d-flex justify-center align-center"
    style="max-width: 92%; margin-left: 27px"
  >
    <!-- 댓글 목록 -->
    <v-col>
      <v-row>
        <div id="comment-area" class="col-lg-12 col-md-6 col-sm-12">
          <div style="margin-bottom: 30px">
            <span style="font-size: 19px"
              ><strong>댓글 {{ commentcount }}개</strong></span
            >
          </div>

          <v-card
            variant="tonal"
            color="indigo-lighten-4"
            v-for="com in comment"
            :key="com.commentNo"
            class="mb-2"
          >
            <v-card-title style="padding-bottom: 0px; padding-right: 0px">
              <div class="d-flex align-center justify-content-between">
                <!-- User ID and Registration Time on the left -->
                <div>
                  <span style="font-size: 16px; color: black">
                    <strong>{{ com.userId }}</strong>
                  </span>
                  <span
                    class="mb-0"
                    style="margin-left: 10px; font-size: 14px; color: rgb(117, 117, 117)"
                  >
                    {{ com.registTime }}
                  </span>
                </div>

                <!-- Delete Comment Button on the right -->
                <v-btn
                  v-if="userId == com.userId && !com.delete"
                  @click="deleteComment(com)"
                  color="red"
                  variant="text"
                  class="ml-auto delete-btn"
                  style="padding-right: 0px; padding-left: 0px"
                >
                  삭제
                </v-btn>
              </div>
            </v-card-title>

            <template v-if="com.delete">
              <p style="margin-left: 20px; color: black">삭제된 댓글입니다.</p>
            </template>
            <template v-else>
              <p style="margin-left: 20px; color: black">{{ com.content }}</p>
            </template>
          </v-card>
        </div>
      </v-row>
      <v-row style="margin-top: 30px; margin-bottom: 1px">
        <template v-if="userId != null">
          <form id="comment" class="col-lg-12 col-md-10 col-sm-12">
            <div class="input-group mb-3">
              <input type="hidden" id="articleno" :value="article.postId" />
              <input type="hidden" id="nowid" :value="userId" />
              <div class="comment-input-container">
                <div class="form-floating">
                  <textarea
                    class="form-control"
                    auto-grow
                    id="floatingTextarea"
                    v-model="com_temp"
                    style="border-radius: 10px"
                    @keydown.enter.exact.prevent="writeComments"
                  ></textarea>
                  <label for="floatingTextarea">댓글</label>
                </div>
                <v-btn
                  @click="writeComments"
                  variant="text"
                  color="blue"
                  prepend-icon="mdi-reply"
                  class="comment-submit-btn"
                ></v-btn>
              </div>
            </div>
          </form>
        </template>
      </v-row>
    </v-col>
  </v-container>
</template>

<style scoped>
:deep(.delete-btn .v-btn--size-default) {
  padding-right: 0px !important;
}
:deep(.v-btn .v-icon) {
  --v-icon-size-multiplier: 1.3;
}
:deep(.input-group) {
  display: inline;
}
.comment-input-container {
  position: relative;
  display: flex;
  align-items: center;
}

.form-floating {
  flex-grow: 1; /* Ensure textarea takes up available space */
}

.comment-submit-btn {
  position: absolute;
  right: 0; /* Adjust as necessary */
  top: 40%; /* Center vertically */
  transform: translateY(-50%); /* Adjust button's center to match the form's center */
  box-shadow: none;
  padding-right: 0px;
  padding-top: 10px;
  padding-bottom: 10px;
  border-radius: 10px; /* Optional: if you want the button to have rounded corners */
}
</style>
