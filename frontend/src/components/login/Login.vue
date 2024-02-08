<script setup>
import { useAccountStore } from '@/stores/account.js'
import { ref } from 'vue'
import { useRouter } from 'vue-router'
const router = useRouter()
const store = useAccountStore()
const useremail = ref(null)
const password = ref(null)
const logIn = function () {
  // 로그인 데이터 준비
  const logindata = {
    useremail: useremail.value,
    password: password.value
  }
  store.logIn(logindata)
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
    <v-card class="mx-auto pa-12 pb-8" elevation="8" max-width="448" rounded="lg">
      <div class="text-h5 text-center font-weight-bold">로그인</div>
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
      <v-btn block class="mb-8 mt-10" color="blue" size="large" variant="tonal" @click="logIn">
        로그인
      </v-btn>
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
  </div>
</template>
<style scoped>
.d-flex {
  height: 0% !important;
}
</style>
