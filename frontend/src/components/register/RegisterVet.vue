<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
// import { useAccountStore } from '@/stores/account.js'
// import Complete from '@/components/register/Complete.vue'

const router = useRouter()
// const store = useAccountStore()

const username = ref(null)
const email = ref(null)
const address = ref(null)
const phone = ref(null)
const password1 = ref(null)
const password2 = ref(null)

// 회원가입 폼 / 회원가입이 완료 컴포넌트
// const showSignUpForm = ref(true)
// const showSignUpComplete = ref(false)

const signUp = function () {
  // const payload = {
  //     username: username.value,
  //     email: email.value,
  //     address: address.value,
  //     phone: phone.value,
  //     password1: password1.value,
  //     password2: password2.value
  // }
  // store.signUp(payload) (추후 개발시 활성화)
  router.push({ name: 'register-complete' })

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
</script>

<template>
  <div>
    <!-- <form v-if="showSignUpForm" @submit.prevent="signUp"> -->
    <form @submit.prevent="signUp">
      <div>
        <input type="text" placeholder="User email" v-model.trim="email" />
        <input type="text" placeholder="Password" v-model.trim="password1" />
        <input type="text" placeholder="Password Check" v-model.trim="password2" />
        <input type="text" placeholder="Username" v-model.trim="username" />
        <input type="text" placeholder="Address" v-model.trim="address" />
        <input type="text" placeholder="Phone number" v-model.trim="phone" />
      </div>
      <input type="submit" />
    </form>
  </div>
  <!-- `showSignUpComplete`이 true이면 회원가입 완료 화면을 표시합니다. -->
  <!-- <Complete v-if="showSignUpComplete" /> -->
</template>

<style></style>
