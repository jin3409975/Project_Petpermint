<script setup>
import { useAccountStore } from '@/stores/account.js'
import { ref } from 'vue'
import { useRouter } from 'vue-router'
const router = useRouter()
const store = useAccountStore()
const useremail = ref(null)
const password = ref(null)
const logIn = async function () {
  // 로그인 데이터 준비
  const logindata = {
    useremail: useremail.value,
    password: password.value
  }
  await store.logIn(logindata)
  // // 데이터 검증 로직
  // if (result) {
  //   // 데이터 전송 로직
  //   router.push({ name: 'main-home' })
  // } else {
  //   alert('로그인 정보를 입력해주세요.')
  // }
}
const rules = {
  email: [(v) => !!v || '이메일을 입력해주세요.'],
  password: [(v) => !!v || '비밀번호를 입력해주세요.']
}

// nav 해당 페이지로 이동
const navigateTo = (path) => {
  router.push(path)
}

// const items = ref([
//   {
//     title: '아이디 찾기',
//     disabled: false,
//     name: ''
//   },
//   {
//     title: '비밀번호 찾기',
//     disabled: false,
//     name: ''
//   },
//   {
//     title: '회원가입',
//     disabled: false,
//     name: 'register-home'
//   }
// ])
// const navigate = (item) => {
//   if (item.name) {
//     router.push({ name: item.name })
//   }
// }
</script>
<template>
  <div style="margin-top: 120px; margin-bottom: 80px">
    <!-- <v-img
      class="mx-auto my-6"
      max-width="228"
      src="https://cdn.vuetifyjs.com/docs/images/logos/vuetify-logo-v3-slim-text-light.svg"
    ></v-img> -->
    <v-card
      class="mx-auto pa-12 pb-8"
      elevation="8"
      max-width="448"
      rounded="lg"
      style="margin-top: 40px; z-index: 10"
    >
      <div class="text-h5 text-center font-weight-bold">로그인</div>
      <form @submit.prevent="logIn">
        <div
          class="text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between mt-6"
        >
          Email
          <a
            class="text-caption text-decoration-none text-blue"
            href="/find/id"
            rel="noopener noreferrer"
            target="_self"
          >
            이메일 찾기</a
          >
        </div>
        <v-text-field
          v-model="useremail"
          density="compact"
          placeholder="이메일 입력"
          prepend-inner-icon="mdi-email-outline"
          variant="outlined"
          :rules="rules.email"
          class="mb-5"
        ></v-text-field>
        <div class="text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between">
          Password
          <a
            class="text-caption text-decoration-none text-blue"
            href="/find/password"
            rel="noopener noreferrer"
            target="_self"
          >
            비밀번호 찾기</a
          >
        </div>
        <v-text-field
          v-model="password"
          :append-inner-icon="visible ? 'mdi-eye-off' : 'mdi-eye'"
          :type="visible ? 'text' : 'password'"
          density="compact"
          placeholder="비밀번호 입력"
          prepend-inner-icon="mdi-lock-outline"
          variant="outlined"
          :rules="rules.password"
          @click:append-inner="visible = !visible"
        ></v-text-field>
        <v-btn type="submit" block class="mb-8 mt-10" color="blue" size="large" variant="tonal">
          로그인
        </v-btn>
      </form>

      <v-card-text class="text-center">
        <a
          class="text-blue text-decoration-none"
          @click="navigateTo('/register/user/info')"
          rel="noopener noreferrer"
          target="_blank"
          style="margin-right: 35px; cursor: pointer"
        >
          보호자 회원 가입 <v-icon icon="mdi-chevron-right"></v-icon>
        </a>
        <a
          class="text-blue text-decoration-none"
          @click="navigateTo('/register/vet')"
          rel="noopener noreferrer"
          target="_blank"
          style="margin-left: 35px; cursor: pointer"
        >
          수의사 회원 가입 <v-icon icon="mdi-chevron-right"></v-icon>
        </a>
      </v-card-text>
    </v-card>

    <div class="dog" style="position: absolute; bottom: 10px"></div>
    <div class="grass">
      <div class="sand"></div>
      <div class="cloudcontain1">
        <div class="cloud1"></div>
        <div class="cloud2"></div>
        <div class="cloud3"></div>
      </div>
      <div class="cloudcontain2">
        <div class="cloud1"></div>
        <div class="cloud2"></div>
        <div class="cloud3"></div>
      </div>
      <div class="cloudcontain3">
        <div class="cloud1"></div>
        <div class="cloud2"></div>
        <div class="cloud3"></div>
      </div>
      <div class="cloudcontain4">
        <div class="cloud1"></div>
        <div class="cloud2"></div>
        <div class="cloud3"></div>
      </div>
      <div class="blade1"></div>
      <div class="blade2"></div>
      <div class="blade3"></div>
      <div class="blade4"></div>
      <div class="blade5"></div>
      <div class="blade6"></div>
      <div class="blade7"></div>
      <div class="blade8"></div>
      <div class="blade9"></div>
      <div class="blade10"></div>
      <div class="blade11"></div>
      <div class="blade12"></div>
      <div class="blade13"></div>
      <div class="blade14"></div>
      <div class="blade15"></div>
      <div class="blade16"></div>
      <div class="blade17"></div>
      <div class="blade18"></div>
      <div class="blade19"></div>
      <div class="blade20"></div>
      <div class="blade21"></div>
      <div class="blade22"></div>
      <div class="blade23"></div>
      <div class="blade24"></div>
      <div class="blade25"></div>
      <div class="blade26"></div>
      <div class="blade27"></div>
      <div class="blade28"></div>
      <div class="blade29"></div>
      <div class="blade30"></div>
      <div class="blade31"></div>
      <div class="blade32"></div>
      <div class="blade33"></div>
      <div class="blade34"></div>
      <div class="blade35"></div>
      <div class="blade36"></div>
      <div class="blade37"></div>
      <div class="blade38"></div>
      <div class="blade39"></div>
      <div class="blade40"></div>
      <div class="blade41"></div>
      <div class="blade42"></div>
      <div class="blade43"></div>
      <div class="blade44"></div>
      <div class="blade45"></div>
      <div class="blade1b"></div>
      <div class="blade2b"></div>
      <div class="blade3b"></div>
      <div class="blade4b"></div>
      <div class="blade5b"></div>
      <div class="blade6b"></div>
      <div class="blade7b"></div>
      <div class="blade8b"></div>
      <div class="blade9b"></div>
      <div class="blade10b"></div>
      <div class="blade11b"></div>
      <div class="blade12b"></div>
      <div class="blade13b"></div>
      <div class="blade14b"></div>
      <div class="blade15b"></div>
      <div class="blade16b"></div>
      <div class="blade17b"></div>
      <div class="blade18b"></div>
      <div class="blade19b"></div>
      <div class="blade20b"></div>
      <div class="blade21b"></div>
      <div class="blade22b"></div>
      <div class="blade23b"></div>
      <div class="blade24b"></div>
      <div class="blade25b"></div>
      <div class="blade26b"></div>
      <div class="blade27b"></div>
      <div class="blade28b"></div>
      <div class="blade29b"></div>
      <div class="blade30b"></div>
      <div class="blade31b"></div>
      <div class="blade32b"></div>
      <div class="blade33b"></div>
      <div class="blade34b"></div>
      <div class="blade35b"></div>
      <div class="blade36b"></div>
      <div class="blade37b"></div>
      <div class="blade38b"></div>
      <div class="blade39b"></div>
      <div class="blade40b"></div>
      <div class="blade41b"></div>
      <div class="blade42b"></div>
      <div class="blade43b"></div>
      <div class="blade44b"></div>
      <div class="blade45b"></div>
    </div>
  </div>
</template>
<style scoped>
.d-flex {
  height: 0% !important;
}
</style>
