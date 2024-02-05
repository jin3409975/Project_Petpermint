<script setup>
import MainFirst from '@/components/main/MainFirst.vue'
import MainTopBanner from '@/components/main/MainTopBanner.vue'
import MainLifeBanner from '@/components/main/MainLifeBanner.vue'
import Typed from 'typed.js'
</script>

<template>
  <div>
    <MainFirst />
    <MainTopBanner />
  </div>

  <!-- <MainLifeBanner /> -->
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
