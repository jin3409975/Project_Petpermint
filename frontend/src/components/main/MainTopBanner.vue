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
  if (scrollY > 500) animateRow1.value = true
  if (scrollY > 900) animateRow2.value = true
  if (scrollY > 1400) animateRow3.value = true
  if (scrollY > 1900) animateRow4.value = true
}

onMounted(() => {
  window.addEventListener('scroll', checkScroll)
  checkScroll()
})

onUnmounted(() => {
  window.removeEventListener('scroll', checkScroll)
})
const card1 = ref({
  front: 'The "First Computer Programmer"',
  back: 'Ada Lovelace',
  flipped: false
})
const card2 = ref({
  front: 'The "First Computer Programmer"',
  back: 'Ada Lovelace',
  flipped: false
})
const card3 = ref({
  front: 'The "First Computer Programmer"',
  back: 'Ada Lovelace',
  flipped: false
})
const card4 = ref({
  front: 'The "First Computer Programmer"',
  back: 'Ada Lovelace',
  flipped: false
})

function toggleCard1() {
  card1.value.flipped = !card1.value.flipped
}
function toggleCard2() {
  card2.value.flipped = !card2.value.flipped
}
function toggleCard3() {
  card3.value.flipped = !card3.value.flipped
}
function toggleCard4() {
  card4.value.flipped = !card4.value.flipped
}
</script>

<template>
  <v-container>
    <v-row align="center" justify="start" no-gutters style="margin-top: 70px; margin-left: 50px">
      <v-col cols="12" class="d-flex justify-center">
        <p class="mb-0 main-text" style="padding-left: 35px">what's on our PetPermint?</p>
      </v-col>

      <v-col class="d-flex justify-end" style="padding-left: 30px">
        <div class="d-flex align-center">
          <p class="mb-0 switch-text" style="margin-right: 5px; margin-left: 20px">
            반려동물 보호자
          </p>
          <v-switch v-model="switchState"></v-switch>
          <p class="mb-0 switch-text" style="margin-left: 5px">수의사</p>
        </div>
      </v-col>
    </v-row>

    <div v-if="!switchState" style="margin-top: 50px">
      <v-row justify="space-around" ref="row1">
        <v-col
          cols="12"
          md="6"
          :class="{ 'animate-slide-in-left': animateRow1 }"
          style="padding-left: 80px; padding-right: 600px"
        >
          <div class="flashcard-container" @click="toggleCard1">
            <div class="flashcard" :class="{ flipped: card1.flipped }">
              <div class="front">
                {{ card1.front }}
              </div>
              <div class="back">
                {{ card1.back }}
              </div>
            </div>
          </div>
        </v-col>
        <v-col cols="12" md="5" :class="{ 'animate-slide-in-right': animateRow1 }">
          <img src="../../assets/img/fun1.png" class="image-contain" />
        </v-col>

        <v-col cols="12" md="6" :class="{ 'animate-slide-in-left': animateRow2 }">
          <img src="../../assets/img/fun2.png" class="image-contain" />
        </v-col>
        <v-col cols="12" md="5" offset="1" :class="{ 'animate-slide-in-right': animateRow2 }">
          <div class="flashcard-container" @click="toggleCard2">
            <div class="flashcard" :class="{ flipped: card2.flipped }">
              <div class="front1">
                {{ card2.front }}
              </div>
              <div class="back1">
                {{ card2.back }}
              </div>
            </div>
          </div>
        </v-col>

        <v-col
          cols="12"
          md="6"
          :class="{ 'animate-slide-in-left': animateRow3 }"
          style="padding-left: 80px; padding-right: 200px"
        >
          <div class="flashcard-container" @click="toggleCard3">
            <div class="flashcard" :class="{ flipped: card3.flipped }">
              <div class="front">
                {{ card3.front }}
              </div>
              <div class="back">
                {{ card3.back }}
              </div>
            </div>
          </div>
        </v-col>
        <v-col cols="12" md="5" offset="1" :class="{ 'animate-slide-in-right': animateRow3 }">
          <img src="../../assets/img/fun3.png" class="image-contain" />
        </v-col>

        <v-col cols="12" md="6" :class="{ 'animate-slide-in-left': animateRow4 }">
          <img src="../../assets/img/fun4.png" class="image-contain" />
        </v-col>
        <v-col
          cols="12"
          md="5"
          offset="1"
          :class="{ 'animate-slide-in-right': animateRow4 }"
          style="margin-bottom: 80px"
        >
          <div class="flashcard-container" @click="toggleCard4">
            <div class="flashcard" :class="{ flipped: card4.flipped }">
              <div class="front1">
                {{ card2.front }}
              </div>
              <div class="back1">
                {{ card2.back }}
              </div>
            </div>
          </div>
        </v-col>
      </v-row>
    </div>

    <div v-if="switchState" style="margin-top: 50px">
      <v-row justify="space-around" ref="row1">
        <v-col
          cols="12"
          md="6"
          :class="{ 'animate-slide-in-left': animateRow1 }"
          style="padding-left: 80px; padding-right: 600px"
        >
          <div class="flashcard-container" @click="toggleCard1">
            <div class="flashcard" :class="{ flipped: card1.flipped }">
              <div class="front">
                {{ card1.front }}
              </div>
              <div class="back">
                {{ card1.back }}
              </div>
            </div>
          </div>
        </v-col>
        <v-col cols="12" md="5" :class="{ 'animate-slide-in-right': animateRow1 }">
          <img src="../../assets/img/fun11.png" class="image-contain" />
        </v-col>

        <v-col cols="12" md="6" :class="{ 'animate-slide-in-left': animateRow2 }">
          <img src="../../assets/img/fun1.png" class="image-contain" />
        </v-col>
        <v-col cols="12" md="5" offset="1" :class="{ 'animate-slide-in-right': animateRow2 }">
          <div class="flashcard-container" @click="toggleCard2">
            <div class="flashcard" :class="{ flipped: card2.flipped }">
              <div class="front1">
                {{ card2.front }}
              </div>
              <div class="back1">
                {{ card2.back }}
              </div>
            </div>
          </div>
        </v-col>

        <v-col
          cols="12"
          md="6"
          :class="{ 'animate-slide-in-left': animateRow3 }"
          style="padding-left: 80px; padding-right: 200px; margin-bottom: 80px"
        >
          <div class="flashcard-container" @click="toggleCard3">
            <div class="flashcard" :class="{ flipped: card3.flipped }">
              <div class="front">
                {{ card3.front }}
              </div>
              <div class="back">
                {{ card3.back }}
              </div>
            </div>
          </div>
        </v-col>
        <v-col cols="12" md="5" offset="1" :class="{ 'animate-slide-in-right': animateRow3 }">
          <img src="../../assets/img/fun4.png" class="image-contain" />
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

.flashcard-container {
  perspective: 650px;
}

.flashcard {
  width: 400px;
  height: 400px;
  transform-style: preserve-3d;
  transition: transform 0.6s;
  cursor: pointer;
}

.flashcard.flipped {
  transform: rotateY(180deg);
}
.front,
.back {
  position: absolute;
  width: 100%;
  height: 100%;
  backface-visibility: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 10px;
  color: white;
  font-size: 20px;
  font-weight: bold;
}

.front {
  background: #c0a0a8;
}

.back {
  background: #d9c56a;
  transform: rotateY(180deg);
}
.front1,
.back1 {
  position: absolute;
  width: 100%;
  height: 100%;
  backface-visibility: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 10px;
  color: white;
  font-size: 20px;
  font-weight: bold;
}

.front1 {
  background: #d9c56a;
}

.back1 {
  background: #c0a0a8;
  transform: rotateY(180deg);
}

.switch-text {
  font-weight: bold;
  font-size: 16px;
}

.main-text {
  font-weight: bold;
  font-size: 60px;
}

.image-contain {
  width: 100%;
  height: 300px;
  object-fit: contain;
  margin-top: 50px;
  padding-right: 60px;
}
</style>
