<script setup>
import { ref, onMounted, onUnmounted, computed } from 'vue'
import { useRouter } from 'vue-router'

const logoUrl = computed(() => {
  return new URL('@/assets/img/logo.png', import.meta.url).href
})

const router = useRouter()

const navigateToLogin = () => {
  router.push({ name: 'login-home' })
}
const navigateToRegister = () => {
  router.push({ name: 'register-home' })
}
const navigateToInitial = () => {
  router.push({ name: 'reserve-initial' })
}
const navigateToAppoint = () => {
  router.push({ name: 'reserve-appoint' })
}

const isScrolled = ref(false)

const updateAppBarBackground = () => {
  console.log(window.scrollY) // 디버깅을 위한 로그
  isScrolled.value = window.scrollY > 0
}

onMounted(() => {
  window.addEventListener('scroll', updateAppBarBackground)
})

onUnmounted(() => {
  window.removeEventListener('scroll', updateAppBarBackground)
})
</script>

<template>
  <v-app-bar app fixed id="custom-nav-bar" :class="{ 'app-bar-scrolled': isScrolled }">
    <div class="d-flex justify-between w-100">
      <!-- 왼쪽 항목: 페퍼민트란, 초기상담 예약, 병원진료 예약, 라이프케어 -->
      <div class="d-flex justify-start">
        <v-btn text>Home</v-btn>
        <v-btn text @click="navigateToInitial">초기 상담예약</v-btn>
        <v-btn text @click="navigateToAppoint">병원 진료예약</v-btn>
        <v-btn text>라이프케어</v-btn>
      </div>

      <!-- 로고 이미지 -->
      <div class="d-flex justify-center">
        <img :src="logoUrl" class="my-2" contain height="70" />
      </div>

      <!-- 오른쪽 항목: 로그인, 회원가입 버튼 -->
      <div class="d-flex justify-end">
        <v-btn text @click="navigateToLogin">로그인</v-btn>
        <v-btn text @click="navigateToRegister">회원가입</v-btn>
      </div>
    </div>
  </v-app-bar>
</template>

<style>
.d-flex {
  display: flex;
  align-items: center;
  flex: 1;
}

.justify-between {
  justify-content: space-between;
  width: 100%;
}

.justify-start {
  justify-content: flex-start;
  flex: 1;
}

.justify-center {
  justify-content: center;
}

.justify-end {
  justify-content: flex-end;
  flex: 1;
}

#custom-nav-bar {
  background-color: rgba(68, 52, 213, 0.592); /* 초기 투명 상태 */
  backdrop-filter: blur(5px);
  transition: background-color 2.5s ease; /* 부드러운 색상 전환 */
}

/* 스크롤 시: 흰색 배경 */
.app-bar-scrolled {
  background-color: white !important; /* 흰색 배경 */
  transition: background-color 0.3s; /* 부드러운 색상 전환 */
}
</style>
