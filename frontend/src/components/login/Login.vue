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
  // const logindata = {
  //   useremail: useremail.value,
  //   password: password.value
  // }

  // store.logIn(logindata)

  // 데이터 검증 로직
  if (useremail.value && password.value) {
    // 데이터 전송 로직
    router.push({ name: 'main-home' })
  } else {
    alert('로그인 정보를 입력해주세요.')
  }
}

const rules = {
  email: [(v) => !!v || '아이디를 입력해주세요.'],
  password: [(v) => !!v || '비밀번호를 입력해주세요.']
}

const items = ref([
  {
    title: '아이디 찾기',
    disabled: false,
    name: ''
  },
  {
    title: '비밀번호 찾기',
    disabled: false,
    name: ''
  },
  {
    title: '회원가입',
    disabled: false,
    name: 'register-home'
  }
])

const navigate = (item) => {
  if (item.name) {
    router.push({ name: item.name })
  }
}
</script>

<template>
  <div class="sign-up-container">
    <h1>계정 로그인</h1>
    <!-- 이메일 , 비밀번호 입력 폼  -->
    <form @submit.prevent="logIn" class="sign-up-form">
      <v-text-field
        label="사용자 이메일"
        v-model="useremail"
        :rules="rules.email"
        hide-details="auto"
      ></v-text-field>
      <v-text-field
        label="비밀번호"
        v-model="password"
        :rules="rules.password"
        hide-details="auto"
      ></v-text-field>
      <div class="button-container">
        <v-btn type="submit" variant="outlined" class="submit-btn">로그인</v-btn>
      </div>
    </form>
    <v-breadcrumbs :items="items">
      <template v-slot:item="{ item }">
        <span v-if="!item.disabled" class="breadcrumb-item" @click="navigate(item)">
          {{ item.title }}
        </span>
        <span v-else>{{ item.title }}</span>
      </template>
    </v-breadcrumbs>
  </div>
</template>

<style>
.sign-up-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 20px;
  margin-top: 50px;
}
.sign-up-form {
  width: 100%;
  max-width: 500px;
  margin-bottom: 30px;
  margin-top: 30px;
}
.button-container {
  display: flex;
  justify-content: center;
  margin-top: 10px;
}
.breadcrumb-item {
  cursor: pointer;
  color: blue; /* 기본 색상 */
}

.breadcrumb-item:hover {
  text-decoration: underline; /* 호버 시 밑줄 */
  color: darkblue; /* 호버 시 색상 변경 */
}
</style>
