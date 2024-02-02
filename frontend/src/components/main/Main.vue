<script setup>
import Navbar from '@/components/main/Navbar.vue'
import MainTopBanner from '@/components/main/MainTopBanner.vue'
import MainLifeBanner from '@/components/main/MainLifeBanner.vue'
</script>

<template>
  <div class="main-container">
    <video class="background-video" width="100%" autoplay muted loop>
      <source src="../../assets/img/MainVideo.mp4" type="video/mp4" />
    </video>
    <Navbar />
    <!-- <MainTopBanner />
    <MainLifeBanner /> -->
  </div>
</template>

<style>
.main-container {
  position: relative;
  overflow: hidden;
  height: 100vh; /* 필요에 따라 조정 */
}

.background-video {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover; /* 비디오가 컨테이너를 꽉 채우도록 */
}
</style>
