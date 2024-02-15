<script setup>
import { ref } from 'vue'
import { useAccountStore } from '@/stores/account.js'
import { storeToRefs } from 'pinia'

const store = useAccountStore()
const { result } = storeToRefs(store)
const email = ref(null)
const confirm = ref(null)
const password1 = ref(null)
const password2 = ref(null)
const name = ref(null)
const emailcheck = ref(false)

const emailRequest = function () {
  console.log('이메일 인증 요청', emailcheck.value)
  store.emailRequest(email.value, 'find')
  console.log('결과', store.result)
}

const validateResult = ref(0)
const emailValidate = function () {
  console.log('이메일 확인 요청', emailcheck.value)
  var data = false
  data = store.emailValidate(email.value, confirm.value)
  console.log('asddasads', data)
  if (data == true) {
    validateResult.value = 1
  } else {
    alert('인증 정보가 올바르지 않습니다.')
    return false
  }

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
  <div style="margin-top: 120px; margin-bottom: 40px">
    <v-card
      class="mx-auto pa-12 pb-8"
      variant="outlined"
      elevation="0"
      max-width="448"
      rounded="lg"
    >
      <div class="text-h5 text-center font-weight-bold">비밀번호 찾기</div>

      <div
        class="text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between mt-6"
        style="font-weight: bold"
      >
        이름
      </div>
      <v-text-field
        placeholder="이름을 입력해주세요."
        v-model="name"
        density="compact"
        :rules="nameRules"
        prepend-inner-icon="mdi-account-outline"
        variant="outlined"
        class="mb-5"
      ></v-text-field>

      <div
        class="text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between"
        style="font-weight: bold"
      >
        이메일
      </div>
      <v-text-field
        placeholder="이메일을 입력해주세요."
        v-model="email"
        :rules="emailRules"
        density="compact"
        prepend-inner-icon="mdi-email-outline"
        variant="outlined"
        class="mb-5"
      ></v-text-field>

      <v-btn block class="mb-8" color="indigo" size="large" variant="tonal" @click="emailRequest()"
        >인증 요청</v-btn
      >

      <div
        class="text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between"
        style="font-weight: bold"
      >
        인증 번호
      </div>
      <v-text-field
        placeholder="인증번호를 입력해주세요."
        v-model="confirm"
        :rules="confirmRules"
        density="compact"
        prepend-inner-icon="mdi-numeric"
        variant="outlined"
        class="mb-5"
      ></v-text-field>

      <v-btn block class="mb-8" color="indigo" size="large" variant="tonal" @click="emailValidate()"
        >인증 확인</v-btn
      >

      <div v-show="validateResult == 1">
        <div
          class="text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between"
          style="font-weight: bold"
        >
          비밀번호 변경
        </div>
        <v-text-field
          placeholder="변경할 비밀번호를 입력해주세요."
          v-model="password1"
          :rules="password1Rules"
          density="compact"
          prepend-inner-icon="mdi-lock-outline"
          variant="outlined"
          class="mb-5"
        ></v-text-field>

        <div
          v-show="validateResult == 1"
          class="text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between"
          style="font-weight: bold"
        >
          비밀번호 확인
        </div>
        <v-text-field
          placeholder="비밀번호를 한 번 더 입력해주세요."
          v-model="password2"
          :rules="password2Rules"
          density="compact"
          prepend-inner-icon="mdi-lock-outline"
          variant="outlined"
          class="mb-5"
        ></v-text-field>

        <v-btn
          block
          class="mb-8"
          color="indigo"
          size="large"
          variant="tonal"
          @click="updatePassword()"
          >비밀번호 변경</v-btn
        >
      </div>
    </v-card>
  </div>
</template>

<style scoped>
.v-btn {
  margin-bottom: 25px !important;
}
</style>
