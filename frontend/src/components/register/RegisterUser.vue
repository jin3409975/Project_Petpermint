<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
// import { useAccountStore } from '@/stores/account.js'
// import Complete from '@/components/register/Complete.vue'

const router = useRouter()
// const store = useAccountStore()

const email = ref(null)
const confirm = ref(null)
const password1 = ref(null)
const password2 = ref(null)
const name = ref(null)
const phone = ref(null)
const address1 = ref(null)
const address2 = ref(null)

// 회원가입 폼 / 회원가입이 완료 컴포넌트
// const showSignUpForm = ref(true)
// const showSignUpComplete = ref(false)

const signUp = function () {
  // const payload = {
  //     email: email.value,
  //     confirm: confirm.value,
  //     address: address.value,
  //     password1: password1.value,
  //     password2: password2.value,
  //     name: name.value,
  //     phone: phone.value,
  //     address1: address1.value,
  //     address2: address2.value,
  // }
  // store.signUp(payload) (추후 개발시 활성화)
  router.push({ name: 'register-pet' })

  // 데이터가 잘 전달 되면 회원가입 완료 페이지 띄우기(추후수정)
  //   try {
  //
  //     await store.signUp(payload)

  //   // 회원가입이 성공하면 상태를 업데이트, 회원가입 완료표시
  //   showSignUpForm.value = false
  //   showSignUpComplete.value = true
  // } catch (error) {
  //   // 회원가입 실패
  //   console.error('회원가입 실패:', error)
  // }
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
  (v) => (v && v.length >= 5) || '아이디는 최소 5자 이상이어야 합니다.'
])
const password2Rules = ref([
  (v) => !!v || '비밀번호 확인은 필수입니다.',
  (v) => v === password1.value || '비밀번호가 일치하지 않습니다.'
])
const nameRules = ref([(v) => !!v || '이름은 필수입니다.'])
const phoneRules = ref([
  (v) => !!v || '핸드폰번호는 필수입니다.',
  (v) => (v && v.length == 13) || '010-0000-0000 형식으로 입력해주세요'
])
const address1Rules = ref([(v) => !!v || '자택 주소는 필수입니다.'])
const address2Rules = ref([(v) => !!v || '나머지 주소도 입력해주세요.'])
</script>

<template>
  <div class="sign-up-container">
    <h1 class="title">일반 사용자 회원가입</h1>
    <h3>개인 및 계정 정보</h3>
    <!-- <form v-if="showSignUpForm" @submit.prevent="signUp"> -->
    <form @submit.prevent="signUp" class="sign-up-form">
      <div>
        <v-text-field
          label="이메일 *"
          v-model="email"
          :rules="emailRules"
          hide-details="auto"
        ></v-text-field>
        <v-text-field
          label="인증번호 숫자 6자리 입력  *"
          v-model="confirm"
          :rules="confirmRules"
          hide-details="auto"
        ></v-text-field>
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
        <v-text-field
          label="이름 *"
          v-model="name"
          :rules="nameRules"
          hide-details="auto"
        ></v-text-field>
        <v-text-field
          label="핸드폰번호 *"
          v-model="phone"
          :rules="phoneRules"
          hide-details="auto"
        ></v-text-field>
        <v-text-field
          label="직장주소 *"
          v-model="address1"
          :rules="address1Rules"
          hide-details="auto"
        ></v-text-field>
        <v-text-field
          label="나머지 주소 *"
          v-model="address2"
          :rules="address2Rules"
          hide-details="auto"
        ></v-text-field>
      </div>
      <div class="button-container">
        <v-btn type="submit" variant="outlined" class="submit-btn">다음</v-btn>
      </div>
    </form>
  </div>
  <!-- `showSignUpComplete`이 true이면 회원가입 완료 화면을 표시합니다. -->
  <!-- <Complete v-if="showSignUpComplete" /> -->
</template>

<style>
.sign-up-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 20px;
}

.title {
  margin-bottom: 30px; /* h1과 form 사이의 간격 */
}

.sign-up-form {
  width: 100%; /* 폼의 너비 */
  max-width: 500px; /* 최대 폼 너비 */
  margin-bottom: 30px; /* form과 버튼 사이의 간격 */
}

.submit-btn {
  margin-top: 20px; /* 버튼 상단 간격 */
}

.button-container {
  display: flex;
  justify-content: center;
  margin-top: 10px; /* 버튼 상단 간격 */
}
</style>
