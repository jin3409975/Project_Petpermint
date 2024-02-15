<script setup>
import { onMounted, onUnmounted, ref, computed } from 'vue'
import Navbar from '@/components/main/Navbar.vue'
import Typed from 'typed.js'

const isVeterinarian = computed(() => {
  const userType = localStorage.getItem('usertype')
  return userType === '2'
})

const typedElement = ref(null)
let typed = null

onMounted(() => {
  const options = computed(() => ({
    strings: isVeterinarian.value
      ? ['동물 보호자들과 초기 화상 상담', '지금 바로 시작해보세요.']
      : ['사랑스러운 반려동물의 건강', '펫퍼민트가 함께 합니다.'],
    typeSpeed: 90,
    backSpeed: 40,
    fadeOut: true,
    loop: true
  })).value

  typed = new Typed(typedElement.value, options)
})

onUnmounted(() => {
  if (typed) {
    typed.destroy()
  }
})

const scrollDwn = () => {
  window.scrollTo({ top: 698, left: 0 })
}
</script>

<template>
  <div class="main-container">
    <Navbar />
    <video class="background-video" width="100%" autoplay muted loop>
      <source src="/assets/img/MainVideo.mp4" type="video/mp4" />
    </video>

    <div ref="typedElement" class="video-overlay"></div>
    <div v-if="isVeterinarian" class="btn-container">
      <v-btn variant="outlined" color="white" size="large" @click="scrollDwn">예약 일정 확인</v-btn>
    </div>
  </div>
</template>

<style scoped>
:deep(.v-btn--size-large) {
  --v-btn-height: 55px;
  min-width: 90px;
  padding: 0px 30px;
  --v-btn-size: 1.2rem;
}
:deep(.v-btn) {
  border: 3px solid white;
}
.main-container {
  position: relative;
  overflow: hidden;
  height: 100vh;
}

.background-video {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover; /* 비디오가 컨테이너를 꽉 채우도록 */
}

.video-overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: #fff;
  text-align: center;
  font-size: 50px;
  font-weight: bold;
}

.btn-container {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, 130%); /* 중앙 정렬 */
  z-index: 10; /* 비디오 위에 표시 */
}
</style>
