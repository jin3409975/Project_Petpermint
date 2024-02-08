<script setup>
import { useRouter } from 'vue-router'
import { ref, onMounted, watch } from 'vue'
import { useCommunityStore } from '@/stores/community'

const props = defineProps(['article', 'user'])
const community_stores = useCommunityStore()

const userId = ref()
const likes = ref(0)
const router = useRouter()

const content = ref('')
const files = ref()

const write = async () => {
  if (localStorage.useremail == null) {
    alert('로그인 후 진행해 주십시오')
  } else {
    const formData = new FormData()
    formData.append('userId', localStorage.useremail)
    formData.append('content', content.value)

    // Assuming 'images' is an array of File objects
    if (files.value != undefined) {
      files.value.forEach((image) => {
        formData.append('images', image)
      })
    }

    let writer = community_stores.communitywrite(formData)
    await new Promise((resolve) => setTimeout(resolve, 500))
    location.reload()
  }
}

const logincheck = () => {
  if (localStorage.useremail == null) alert('이 기능을 사용하려면 로그인 하십시오.')
}

onMounted(() => {
  userId.value = localStorage.useremail
})
</script>

<script>
export default {
  data: () => ({
    dialog: false
  })
}
</script>

<template>
  <v-row justify="end" style="margin-top: -63px; margin-bottom: 20px; margin-right: 1px">
    <v-dialog v-model="dialog" persistent width="1024">
      <template v-slot:activator="{ props }">
        <v-btn @click="logincheck" v-bind="props" icon="mdi-plus"></v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="text-h5"><b>작성</b></span>
        </v-card-title>
        <form id="form-register" method="POST" action="">
          <v-container>
            <v-row>
              <v-col cols="12" sm="6" md="4">
                <v-text-field
                  readonly
                  label="사용자 Email"
                  v-model="userId"
                  required
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-textarea
                  clearable
                  variant="outlined"
                  label="내용*"
                  required
                  v-model="content"
                ></v-textarea>
              </v-col>
              <v-col cols="12" sm="6">
                <v-file-input
                  variant="outlined"
                  multiple
                  label="첨부 이미지"
                  v-model="files"
                ></v-file-input>
              </v-col>
            </v-row>
            <small>*은 필수적으로 기입해야 합니다.</small>
          </v-container>
        </form>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue-darken-1" variant="text" @click="dialog = false"> Close </v-btn>
          <v-btn color="blue-darken-1" variant="text" @click="write"> Save </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<style scoped></style>
