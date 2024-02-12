<script setup>
import { useRouter } from 'vue-router'
import { ref, onMounted, watch } from 'vue'
import { useCommunityStore } from '@/stores/community'

const props = defineProps(['article', 'user'])
const community_stores = useCommunityStore()

const userId = ref(localStorage.useremail)
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
  <v-row
    justify="center"
    style="
      margin-top: -63px;
      margin-bottom: 20px;
      display: block;
      margin-left: 0px;
      margin-right: 0px;
    "
  >
    <v-form v-model="dialog">
      <template v-slot:activator="{ props }">
        <v-btn @click="logincheck" v-bind="props" icon="mdi-plus"></v-btn>
      </template>
      <v-card style="border-radius: 30px">
        <v-card-title style="margin-top: 20px; margin-left: 20px">
          <span style="font-size: 25px"><strong>펫닥 게시글</strong></span>
        </v-card-title>
        <form id="form-register" method="POST" action="" style="margin-left: 20px">
          <v-container>
            <v-row>
              <v-col cols="12" sm="6" md="8" style="padding-bottom: 0px">
                <span>{{ userId }}</span>
              </v-col>
              <v-col cols="12" sm="12" style="padding-right: 35px; padding-bottom: 0px">
                <v-textarea
                  clearable
                  auto-grow
                  variant="solo-filled"
                  label="내용*"
                  required
                  rows="4"
                  v-model="content"
                  flat
                ></v-textarea>
              </v-col>
              <v-col cols="12" sm="6">
                <v-file-input
                  variant="underlined"
                  multiple
                  label="첨부 이미지"
                  v-model="files"
                ></v-file-input>
              </v-col>
              <v-btn
                prepend-icon="mdi-send-circle-outline"
                size="large"
                color="blue-darken-1"
                variant="tonal"
                @click="write"
                style="margin-top: 25px; margin-left: 200px; margin-bottom: 20px"
              >
                Save
              </v-btn>
            </v-row>
          </v-container>
        </form>
      </v-card>
    </v-form>
  </v-row>
</template>

<style scoped>
:deep(.v-field) {
  border-radius: 20px;
}
</style>
