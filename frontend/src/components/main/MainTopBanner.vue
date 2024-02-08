<script setup>
import { ref, onMounted, onUnmounted } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const switchState = ref(false)

const row1 = ref(null)
const row2 = ref(null)
const row3 = ref(null)
const row4 = ref(null)

const animateRow1 = ref(false)
const animateRow2 = ref(false)
const animateRow3 = ref(false)
const animateRow4 = ref(false)
const animateRow5 = ref(false)
const animateRow6 = ref(false)
const animateRow7 = ref(false)

const checkScroll = () => {
  console.log(window.scrollY)
  if (scrollY > 500) animateRow1.value = true
  if (scrollY > 900) animateRow2.value = true
  if (scrollY > 1400) animateRow3.value = true
  if (scrollY > 1900) animateRow4.value = true
  if (scrollY > 1900) animateRow5.value = true
  if (scrollY > 1900) animateRow6.value = true
  if (scrollY > 1900) animateRow7.value = true
}

onMounted(() => {
  window.addEventListener('scroll', checkScroll)
  checkScroll()
})

onUnmounted(() => {
  window.removeEventListener('scroll', checkScroll)
})
const card1 = ref({
  front: '초기 상담 예약',
  back: '언제 어디서든 \n 수의사와 함께\n 처방 없는 상담',
  flipped: false
})
const card2 = ref({
  front: '병원 진료 예약',
  back: '빠르고 편리한 예약\n 원하는 시간\n 원하는 병원',
  flipped: false
})
const card3 = ref({
  front: '응급 예약',
  back: '응급 상황 시, 오른쪽 아래 빨간 버튼을 누르세요!\n 가장 가까운 동물병원을 알려드려요!\n 예약 버튼으로 한 번에 예약까지!\n 이 버튼은 모든 페이지에서 찾아볼 수 있어요.',
  flipped: false
})
const card4 = ref({
  front: '라이프 케어',
  back: '전문가 라이브 방송\n 다양한 주제\n 커뮤니티',
  flipped: false
})
const card5 = ref({
  front: '상담 및 병원 예약 관리',
  back: '한 눈에 들어오는 일정',
  flipped: false
})
const card6 = ref({
  front: '화상 상담',
  back: '언제 어디서든 처방 없는 상담',
  flipped: false
})
const card7 = ref({
  front: '라이프 케어',
  back: '보호자들에게 꼭 필요한 정보를 제공해보세요.',
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
function toggleCard5() {
  card5.value.flipped = !card5.value.flipped
}
function toggleCard6() {
  card6.value.flipped = !card6.value.flipped
}
function toggleCard7() {
  card7.value.flipped = !card7.value.flipped
}

// nav 해당 페이지로 이동
const navigateTo = (path) => {
  router.push(path)
}
</script>

<template>
  <v-container>
    <v-row align="center" justify="start" no-gutters style="margin-top: 70px; margin-left: 50px">
      <v-col cols="12" class="d-flex justify-center">
        <p class="mb-0 main-text" style="padding-left: 35px">What's on our PetPermint?</p>
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

    <!-- 보호자 카드 -->
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
                <p>언제 어디서든<br /><br />수의사와 함께<br /><br />처방 없는 상담</p>
                <div class="btn-div">
                  <v-btn
                    class="btn"
                    @click="navigateTo('/reserve/initial/time')"
                    elevation="0"
                    color="indigo-darken-3"
                  >
                    초기 상담 예약 바로가기
                  </v-btn>
                </div>
              </div>
            </div>
          </div>
        </v-col>
        <v-col cols="12" md="5" :class="{ 'animate-slide-in-right': animateRow1 }">
          <img src="/assets/img/fun1.png" class="image-contain" />
        </v-col>

        <v-col cols="12" md="6" :class="{ 'animate-slide-in-left': animateRow2 }">
          <img src="/assets/img/fun2.png" class="image-contain" />
        </v-col>
        <v-col cols="12" md="5" offset="1" :class="{ 'animate-slide-in-right': animateRow2 }">
          <div class="flashcard-container" @click="toggleCard2">
            <div class="flashcard" :class="{ flipped: card2.flipped }">
              <div class="front1">
                {{ card2.front }}
              </div>
              <div class="back1">
                <p>빠르고 편리한 예약<br /><br />원하는 시간<br /><br />원하는 병원</p>
                <div class="btn-div">
                  <v-btn
                    class="btn"
                    @click="navigateTo('/reserve/appoint/time')"
                    elevation="0"
                    color="indigo-darken-3"
                  >
                    병원 진료 예약 바로가기
                  </v-btn>
                </div>
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
                <p>
                  오른쪽 아래 빨간 버튼<br /><br />가장 가까운 병원<br /><br />클릭 한 번에 예약까지
                </p>
              </div>
            </div>
          </div>
        </v-col>
        <v-col cols="12" md="5" offset="1" :class="{ 'animate-slide-in-right': animateRow3 }">
          <img src="/assets/img/fun3.png" class="image-contain" />
        </v-col>

        <v-col cols="12" md="6" :class="{ 'animate-slide-in-left': animateRow4 }">
          <img src="/assets/img/fun4.png" class="image-contain" />
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
                {{ card4.front }}
              </div>
              <div class="back1">
                <p>전문가 라이브 방송<br /><br />다양한 주제<br /><br />다양한 정보</p>
                <div class="btn-div">
                  <v-btn
                    class="btn"
                    @click="navigateTo('/lifecare/community')"
                    elevation="0"
                    color="indigo-darken-3"
                  >
                    라이프 케어 바로가기
                  </v-btn>
                </div>
              </div>
            </div>
          </div>
        </v-col>
      </v-row>
    </div>

    <!-- 수의사 카드 -->
    <div v-if="switchState" style="margin-top: 50px">
      <v-row justify="space-around" ref="row1">
        <v-col
          cols="12"
          md="6"
          :class="{ 'animate-slide-in-left': animateRow5 }"
          style="padding-left: 80px; padding-right: 600px"
        >
          <div class="flashcard-container" @click="toggleCard5">
            <div class="flashcard" :class="{ flipped: card5.flipped }">
              <div class="front">
                {{ card5.front }}
              </div>
              <div class="back">
                <p>한 눈에 들어오는 일정</p>
              </div>
            </div>
          </div>
        </v-col>
        <v-col cols="12" md="5" :class="{ 'animate-slide-in-right': animateRow6 }">
          <img src="/assets/img/fun11.png" class="image-contain" />
        </v-col>

        <v-col cols="12" md="6" :class="{ 'animate-slide-in-left': animateRow6 }">
          <img src="/assets/img/fun1.png" class="image-contain" />
        </v-col>
        <v-col cols="12" md="5" offset="1" :class="{ 'animate-slide-in-right': animateRow6 }">
          <div class="flashcard-container" @click="toggleCard6">
            <div class="flashcard" :class="{ flipped: card6.flipped }">
              <div class="front1">
                {{ card6.front }}
              </div>
              <div class="back1">
                <p>언제 어디서든<br /><br />처방 없는 상담</p>
              </div>
            </div>
          </div>
        </v-col>

        <v-col
          cols="12"
          md="6"
          :class="{ 'animate-slide-in-left': animateRow7 }"
          style="padding-left: 80px; padding-right: 200px; margin-bottom: 80px"
        >
          <div class="flashcard-container" @click="toggleCard7">
            <div class="flashcard" :class="{ flipped: card7.flipped }">
              <div class="front">
                {{ card7.front }}
              </div>
              <div class="back">
                <p>전문가 라이브 방송<br /><br />다양한 주제<br /><br />다양한 정보</p>
                <div class="btn-div">
                  <v-btn
                    class="btn"
                    @click="navigateTo('/lifecare/community')"
                    elevation="0"
                    color="indigo-darken-3"
                  >
                    라이프 케어 바로가기
                  </v-btn>
                </div>
              </div>
            </div>
          </div>
        </v-col>
        <v-col cols="12" md="5" offset="1" :class="{ 'animate-slide-in-right': animateRow7 }">
          <img src="/assets/img/fun4.png" class="image-contain" />
        </v-col>
      </v-row>
    </div>
  </v-container>
</template>

<style scoped>
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
  position: relative;
}

.flashcard {
  width: 400px;
  height: 400px;
  transform-style: preserve-3d;
  transition: transform 0.6s;
  cursor: pointer;
  text-align: center;
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
  font-size: 30px;
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
  font-size: 30px;
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

.btn-div {
  position: absolute;
  bottom: 10px;
  left: 50%;
  transform: translateX(-50%);
}

.btn {
  position: absolute;
  bottom: 10px;
  left: 50%;
  transform: translateX(-50%);
}
</style>
