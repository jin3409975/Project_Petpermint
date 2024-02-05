<script setup>
import { onMounted, onUnmounted, ref } from 'vue'
import Navbar from '@/components/main/Navbar.vue'
import Typed from 'typed.js'

const typedElement = ref(null)

onMounted(() => {
  const options = {
    strings: [
      '사랑스러운 반려동물의 건강은',
      '저희 펫퍼민트가',
      '초기 화상 상담으로',
      '지켜드리겠습니다.'
    ],
    typeSpeed: 60,
    backSpeed: 40,
    fadeOut: true,
    loop: true
  }

  new Typed(typedElement.value, options)
})

onUnmounted(() => {
  if (typedElement.value) {
    typedElement.value.typed.destroy()
  }
})
</script>

<template>
  <div class="main-container">
    <Navbar />
    <video class="background-video" width="100%" autoplay muted loop>
      <source src="../../assets/img/MainVideo.mp4" type="video/mp4" />
    </video>
    <div ref="typedElement" class="video-overlay"></div>
  </div>
</template>

<style>
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
</style>
