<script setup>
import { ref, onMounted, onUnmounted } from 'vue'

const switchState = ref(false)

const row1 = ref(null)
const row2 = ref(null)
const row3 = ref(null)
const row4 = ref(null)

const animateRow1 = ref(false)
const animateRow2 = ref(false)
const animateRow3 = ref(false)
const animateRow4 = ref(false)

const checkScroll = () => {
  console.log(window.scrollY)
  if (scrollY > 300) animateRow1.value = true
  if (scrollY > 800) animateRow2.value = true
  if (scrollY > 1300) animateRow3.value = true
  if (scrollY > 1800) animateRow4.value = true
}

onMounted(() => {
  window.addEventListener('scroll', checkScroll)
  checkScroll()
})

onUnmounted(() => {
  window.removeEventListener('scroll', checkScroll)
})
</script>

<template>
  <v-container>
    <v-row align="center" justify="start" no-gutters>
      <v-col cols="auto" class="d-flex align-center justify-center">
        <p class="mb-0" style="margin-right: 5px">반려동물 보호자</p>
      </v-col>
      <v-col cols="auto" class="d-flex align-center justify-center">
        <v-switch v-model="switchState"></v-switch>
      </v-col>
      <v-col cols="auto" class="d-flex align-center justify-center">
        <p class="mb-0" style="margin-left: 5px">수의사</p>
      </v-col>
    </v-row>

    <div v-if="!switchState">
      <v-row justify="space-around" ref="row1">
        <v-col cols="12" md="6" :class="{ 'animate-slide-in-left': animateRow1 }">
          <p>온라인 초기 상담과 <br />에프터케어 서비스</p>
        </v-col>
        <v-col cols="12" md="6" :class="{ 'animate-slide-in-right': animateRow1 }">
          <img src="/assets/img/fun1.png" />
        </v-col>
      </v-row>
      <v-row justify="space-around" ref="row2">
        <v-col :class="{ 'animate-slide-in-left': animateRow2 }">
          <p>간편 진료 예약 서비스</p>
        </v-col>
        <v-col :class="{ 'animate-slide-in-right': animateRow2 }">
          <img src="/assets/img/fun2.png" />
        </v-col>
      </v-row>
      <v-row justify="space-around" ref="row3">
        <v-col :class="{ 'animate-slide-in-left': animateRow3 }">
          <p>응급 예약 서비스</p>
        </v-col>
        <v-col :class="{ 'animate-slide-in-right': animateRow3 }">
          <img src="/assets/img/fun3.png" />
        </v-col>
      </v-row>
      <v-row justify="space-around" ref="row4">
        <v-col :class="{ 'animate-slide-in-left': animateRow4 }">
          <p>라이프케어</p>
        </v-col>
        <v-col :class="{ 'animate-slide-in-right': animateRow4 }">
          <img src="/assets/img/fun4.png" />
        </v-col>
      </v-row>
    </div>

    <div v-if="switchState">
      <v-row justify="space-around" ref="row1">
        <v-col :class="{ 'animate-slide-in-left': animateRow1 }">
          <p>예약캘린더</p>
        </v-col>
        <v-col :class="{ 'animate-slide-in-right': animateRow1 }">
          <img src="/assets/img/fun11.png" />
        </v-col>
      </v-row>
      <v-row justify="space-around" ref="row2">
        <v-col cols="12" md="6" :class="{ 'animate-slide-in-left': animateRow2 }">
          <p>온라인 초기 상담과 <br />에프터케어 서비스</p>
        </v-col>
        <v-col cols="12" md="6" :class="{ 'animate-slide-in-right': animateRow2 }">
          <img src="/assets/img/fun1.png" />
        </v-col>
      </v-row>

      <v-row justify="space-around" ref="row3">
        <v-col :class="{ 'animate-slide-in-left': animateRow3 }">
          <p>라이프케어</p>
        </v-col>
        <v-col :class="{ 'animate-slide-in-right': animateRow3 }">
          <img src="/assets/img/fun4.png" />
        </v-col>
      </v-row>
    </div>
  </v-container>
</template>

<style>
.slide-in-left,
.slide-in-right {
  opacity: 0;
  transform: translateX(-100%);
}

@keyframes slideInFromLeft {
  from {
    transform: translateX(-100%);
    opacity: 0;
  }
  to {
    transform: translateX(0);
    opacity: 1;
  }
}

@keyframes slideInFromRight {
  from {
    transform: translateX(100%);
    opacity: 0;
  }
  to {
    transform: translateX(0);
    opacity: 1;
  }
}

.animate-slide-in-left {
  animation: 1s ease-out 0s 1 slideInFromLeft;
  opacity: 1;
}

.animate-slide-in-right {
  animation: 1s ease-out 0s 1 slideInFromRight;
  opacity: 1;
}
</style>
