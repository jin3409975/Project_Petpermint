<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

const navigateToMain = () => {
  router.push({ name: 'main-home' })
}
</script>

<template>
  <div class="centered-container">
    <div class="container-margin">
      <h1>회원 가입 완료 되었습니다.</h1>
    </div>
    <v-btn text @click="navigateToMain">홈으로 가기</v-btn>
  </div>
</template>

<style>
.centered-container {
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: center;
  height: 100vh;
}
.container-margin {
  margin-bottom: 50px;
}
</style>
