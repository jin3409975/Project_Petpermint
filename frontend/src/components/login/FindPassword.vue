<script setup>
import { ref } from 'vue'
import { useAccountStore } from '@/stores/account.js'
const store = useAccountStore()
const email = ref(null)
const confirm = ref(null)
const password1 = ref(null)
const password2 = ref(null)
const name = ref(null)
const emailcheck = ref(false)

const emailRequest = function () {
  console.log('이메일 인증 요청', emailcheck.value)
  store.emailRequest(email.value)
  console.log('결과', store.result)
}
const emailValidate = function () {
  console.log('이메일 확인 요청', emailcheck.value)
  store.emailValidate(email.value, confirm.value)
  console.log('결과', store.result)
}
const updatePassword = function () {
  console.log('비밀번호 변경 실행')
  store.updatePassword(email.value, name.value, password1.value)
}
const emailRules = ref([
  (v) => !!v || '이메일은 필수입니다.',
  (v) => (v && v.length >= 5) || '아이디는 최소 5자 이상이어야 합니다.'
])
const confirmRules = ref([
  (v) => !!v || '이메일 확인은 필수입니다.',
  (v) => (v && v.length == 5) || '인증번호를 다시 입력해 주세요.'
])
const password1Rules = ref([
  (v) => !!v || '비밀번호는 필수입니다.',
  (v) => (v && v.length >= 5) || '비밀번호는 최소 5자 이상이어야 합니다.'
])
const password2Rules = ref([
  (v) => !!v || '비밀번호 확인은 필수입니다.',
  (v) => v === password1.value || '비밀번호가 일치하지 않습니다.'
])
</script>

<template>
  <div>
    <v-text-field
      label="이름 *"
      v-model="name"
      :rules="nameRules"
      hide-details="auto"
    ></v-text-field>
    <v-text-field
      label="이메일 *"
      v-model="email"
      :rules="emailRules"
      hide-details="auto"
    ></v-text-field>
    <v-btn @click="emailRequest()">인증 요청</v-btn>
    <v-text-field
      label="인증번호 숫자 6자리 입력  *"
      v-model="confirm"
      :rules="confirmRules"
      hide-details="auto"
    ></v-text-field>
    <v-btn @click="emailValidate()">인증 확인</v-btn>
    <div v-show="store.result == 1">
      <v-text-field
        label="비밀번호 *"
        v-model="password1"
        :rules="password1Rules"
        hide-details="auto"
      ></v-text-field>
      <v-text-field
        label="비밀번호 확인 *"
        v-model="password2"
        :rules="password2Rules"
        hide-details="auto"
      ></v-text-field>
      <v-btn @click="updatePassword()">비밀번호 변경</v-btn>
    </div>
  </div>
</template>

<style scoped></style>
