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
  console.log(comment.value)

  commentcount.value = comment.value.length
  console.log(commentcount.value)
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
  <v-container fluid class="w-100">
    <!-- 댓글 목록 -->
    <div id="comment-area" class="col-lg-8 col-md-10 col-sm-12">
      <h4>댓글 ({{ commentcount }})</h4>
      <ul class="list-group">
        <template v-if="commentcount > 0">
          <li class="list-group-item pt-0 pb-0" v-for="com in comment" :key="com.commentNo">
            <div class="media-body">
              <div class="row">
                <h5 class="mt-0 col">{{ com.userId }}</h5>
                <div class="d-flex justify-content-end col">
                  <template v-if="com.registTime != null">
                    <template v-if="userId == com.userId && com.delete == false">
                      <button
                        type="button"
                        class="btn btn-outline-danger ms-1"
                        id="btn-cm-delete"
                        @click="deleteComment(com.commentNo)"
                      >
                        댓글삭제
                      </button>
                    </template>
                  </template>
                </div>
              </div>
              <template v-if="com.delete == false">
                <p>{{ com.content }}</p>
              </template>
              <template v-if="com.delete == true">
                <p>삭제된 댓글입니다.</p>
              </template>
              <p>{{ com.registTime }}</p>
            </div>

            <div
              class="modal modal-wrap modal-comment"
              tabindex="-1"
              role="dialog"
              aria-labelledby="myLargeModalLabel"
              aria-hidden="true"
            >
              <div class="modal1 modal-dialogue">
                <header class="modal-header">
                  <h2>답글 작성</h2>
                  <button class="modal-close-btn" @click="modalOff('.modal-comment')">X</button>
                </header>
                {{ parentuserid }}: {{ parentcontent }} 답글
                <div class="modal-input-wrap">
                  <label class="modal-label" for="modal-pw">내용</label>
                  <textarea
                    class="modal-input"
                    id="content"
                    name="content"
                    v-model="childcontent"
                    style="width: 370px; height: 100px"
                  >
                                        내용</textarea
                  >
                </div>
                <div class="modal-input-wrap" style="justify-content: center">
                  <button @click="CommentChildWrite()" class="modal-submit">확인</button>
                  <button class="modal-cancel but" @click="modalOff('.modal-comment')">취소</button>
                </div>
              </div>
            </div>
          </li>
        </template>
      </ul>
    </div>
    <template v-if="userId != null">
      <form id="comment" class="col-lg-8 col-md-10 col-sm-12">
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
          <button type="button" class="btn btn-outline-dark" @click="writeComments">작성</button>
        </div>
      </form>
    </template>
  </v-container>
</template>

<style scoped></style>
