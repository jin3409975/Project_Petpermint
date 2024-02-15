<script setup>
import { ref, watchEffect, onMounted } from 'vue' // Import watchEffect here
import { useReserveStore } from '@/stores/reserve'
import { storeToRefs } from 'pinia'
import VueDatePicker from '@vuepic/vue-datepicker'
import '@vuepic/vue-datepicker/dist/main.css'
import axios from 'axios'
import { useRouter } from 'vue-router'
import Navbar from '@/components/main/Navbar.vue'
import MainFirst from '@/components/main/MainFirst.vue'

const router = useRouter()
const { VITE_APP_SERVER_URI } = import.meta.env
const reserveStore = useReserveStore()
const { reservedate, starttime, endtime } = storeToRefs(reserveStore)

const date = ref()
const startTime = ref({ hours: 0, minutes: 0 })

watchEffect(() => {
  reservedate.value = formatDate(date.value)
  starttime.value = formatstart(date.value)
  endtime.value = formatend(date.value)
})

const reserveList = ref()
const userName = ref()
const animalName = ref()

function formatDate(selected) {
  if (selected) {
    const date = new Date(selected)
    const year = date.getFullYear()
    const month = ('0' + (1 + date.getMonth())).slice(-2)
    const day = ('0' + date.getDate()).slice(-2)
    const hour = ('0' + date.getHours()).slice(-2)
    const min = ('0' + date.getMinutes()).slice(-2)

    const startTime = `${year}-${month}-${day} ${hour}:${min}`
    const endTime = `${year}-${month}-${day} 23:59`

    console.log(startTime, endTime, localStorage.getItem('licenseNumber'))
    axios({
      method: 'get',
      url: VITE_APP_SERVER_URI + '/reserve/mybook/expert',
      params: {
        licenseNumber: localStorage.getItem('licenseNumber'),
        startTime: startTime,
        endTime: endTime
      }
    }).then((r) => {
      console.log(r)
      if (r.data.result.length == 0) {
        reserveList.value = null
        userName.value = null
        animalName.value = null
        // alert('해당 날짜에 예약이 없습니다.')
        return
      } else {
        reserveList.value = r.data.result
        userName.value = r.data.userName
        animalName.value = r.data.animalName
      }
    })
  }
}
function formatstart(selected) {
  if (selected) {
    const date = new Date(selected)
    const hour = ('0' + date.getHours()).slice(-2)
    const min = ('0' + date.getMinutes()).slice(-2)
    return `${hour}:${min}`
  }
  return ''
}
function formatend(selected) {
  if (selected) {
    const date = new Date(selected)
    date.setMinutes(date.getMinutes() + 20)
    const hour = ('0' + date.getHours()).slice(-2)
    const min = ('0' + date.getMinutes()).slice(-2)
    return `${hour}:${min}`
  }
  return ''
}

// 00분, 20분, 40분을 제외한 모든 분을 비활성화하는 배열을 생성하는 함수
function generateDisabledTimes() {
  const disabledTimes = []
  for (let hour = 0; hour < 24; hour++) {
    for (let minute = 0; minute < 60; minute++) {
      if (minute !== 0 && minute !== 20 && minute !== 40) {
        disabledTimes.push({ hours: hour, minutes: minute })
      }
    }
  }
  return disabledTimes
}

const disabledTimes = ref(generateDisabledTimes()) // 함수 호출로 ref 생성

const disabledDates = (date) => {
  // 오늘 날짜의 시작 부분을 구합니다 (시간을 00:00:00으로 설정)
  const today = new Date()
  today.setHours(0, 0, 0, 0)

  // 비교할 날짜도 같은 방식으로 시간을 00:00:00으로 설정
  const compareDate = new Date(date)
  compareDate.setHours(0, 0, 0, 0)

  // 비교할 날짜가 오늘 이전이면 true, 그렇지 않으면 false 반환
  return compareDate < today
}

const listIndex = ref(0)

function selectReservation(index) {
  console.log(reserveList.value[index].appointId)
  dialog.value = true
  listIndex.value = index
}

const dialog = ref(false)

const handleEnteringConsultButton = () => {
  console.log(reserveList.value[listIndex.value].appointId)
  dialog.value = false
  router.push({
    name: 'cam-consult',
    params: { appointId: reserveList.value[listIndex.value].appointId }
  })
}

const scrollDwn = () => {
  console.log('here')
  window.scrollTo({ top: 822, left: 0 })
}

onMounted(() => {})
</script>

<template>
  <div style="text-align: center">
    <MainFirst />
    <!-- <Navbar />
    <video class="background-video" width="100%" autoplay muted loop>
      <source src="/assets/img/MainVideo.mp4" type="video/mp4" />
    </video>
    <div class="overlay" style="position: absolute; top: 300px; z-index: 2">
      <span style="color: white; font-size: 50px; font-family: Malgun Gothic"
        ><b>사용자들과 예약을 진행해보세요</b></span
      >
      <v-btn @click="scrollDwn" style="margin-left: 320px">예약확인</v-btn>
    </div> -->

    <v-row>
      <v-col style="margin-top: 100px" md="12">
        <span style="font-size: 40px; font-weight: bold">상담 예약</span>
      </v-col>

      <v-col style="margin-top: 110px; margin-bottom: 15px" md="6">
        <div
          class="calendar-container"
          style="margin-left: 250px; margin-bottom: 100px; margin-top: -50px"
        >
          <VueDatePicker
            v-model="date"
            :start-time="startTime"
            minutes-increment="20"
            inline
            time-picker-inline
            auto-apply
            :disabled-times="disabledTimes"
            :disabled-dates="disabledDates"
            :enable-time-picker="false"
            locale="ko-KR"
          >
          </VueDatePicker>
        </div>
      </v-col>
      <v-col md="6">
        <div style="margin-right: 130px; margin-top: 60px">
          <v-card
            class="mx-auto hospital-list-card"
            max-width="420"
            min-height="350"
            style="border: 1px solid #36577e; margin-bottom: 30px"
          >
            <p v-if="reserveList == null" style="margin-top: 150px">
              해당 날짜에 예약 내역이 없습니다.
            </p>
            <v-list base-color="#36577e" three-line class="scrollable-list">
              <v-list-item
                v-for="(reserve, i) in reserveList"
                :key="i"
                :value="reserve"
                @click="selectReservation(i)"
              >
                <v-list-item-content>
                  <v-list-item-title v-text="reserve.time"></v-list-item-title>
                  <v-list-item-subtitle
                    v-text="'보호자 : ' + userName[i] + ' / ' + '반려동물 : ' + animalName[i]"
                  ></v-list-item-subtitle>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-card>
        </div>
      </v-col>
      <div>
        <v-dialog v-model="dialog" width="400px">
          <!-- 온라인 초기 상담 -->
          <v-carousel height="480" hide-delimiter-background show-arrows="false" color="#668BA7">
            <v-carousel-item>
              <div class="card">
                <div class="card__img">
                  <svg width="100%">
                    <rect x="0" y="0" fill="#668BA7" width="100%" height="50%"></rect>
                  </svg>
                </div>
                <v-row class="rows">
                  <v-col col="12" md="12" class="margins" style="height: 10px">
                    <div class="card__content">
                      <div class="card__title">보호자</div>
                      <div class="card__subtitle">{{ userName[listIndex] }}</div>
                    </div>
                  </v-col>
                  <v-col col="12" md="12" class="margins" style="height: 10px">
                    <div class="card__content">
                      <div class="card__title">반려동물 이름</div>
                      <div class="card__subtitle">{{ animalName[listIndex] }}</div>
                    </div></v-col
                  >
                  <v-col col="12" md="12" class="margins" style="height: 10px">
                    <div class="card__content">
                      <div class="card__title">예약 유형</div>
                      <div class="card__subtitle">온라인 초기 상담</div>
                    </div>
                  </v-col>
                  <v-col col="12" md="12" class="margins" style="height: 10px">
                    <div class="card__content">
                      <div class="card__title">예약 날짜</div>
                      <div class="card__subtitle">{{ reserveList[listIndex].time }}</div>
                    </div>
                  </v-col>
                  <v-col col="12" md="3" class="margins">
                    <div class="card__title" style="margin-right: 0">메모</div>
                  </v-col>
                  <v-col col="12" md="9" class="margins">
                    <div class="card__subtitle">
                      {{ reserveList[listIndex].note }}
                    </div>
                  </v-col>
                </v-row>
              </div>

              <!-- 만약 온라인 초기 상담 시간이 안되었다면 disabled를 넣어서 클릭 안되게하기
          <v-btn class="fixed-top-right" variant="flat" color="#C0A0A8" disabled
            >상담 입장하기</v-btn
          > -->
              <v-btn
                class="fixed-top-right"
                variant="flat"
                color="#C0A0A8"
                @click="handleEnteringConsultButton"
                >상담 입장하기</v-btn
              >

              <!-- 닫기 버튼 -->
              <button class="closed" @click="dialog = false">
                <v-icon icon="mdi-close-thick"></v-icon>
              </button>
            </v-carousel-item>
          </v-carousel>
        </v-dialog>
      </div>
    </v-row>
  </div>
</template>

<style>
:root {
  /*General*/
  --dp-font-family: -apple-system, blinkmacsystemfont, 'Segoe UI', roboto, oxygen, ubuntu, cantarell,
    'Open Sans', 'Helvetica Neue', sans-serif;
  --dp-border-radius: 8px; /*Configurable border-radius*/
  --dp-cell-border-radius: 8px; /*Specific border radius for the calendar cell*/
  --dp-common-transition: all 0.1s ease-in; /*Generic transition applied on buttons and calendar cells*/

  /*Sizing*/
  --dp-button-height: 35px; /*Size for buttons in overlays*/
  --dp-month-year-row-height: 35px; /*Height of the month-year select row*/
  --dp-month-year-row-button-size: 30px; /*Specific height for the next/previous buttons*/
  --dp-cell-size: 45px; /*Width and height of calendar cell*/
  --dp-cell-padding: 5px; /*Padding in the cell*/
  --dp-common-padding: 10px; /*Common padding used*/
  --dp-menu-min-width: 400px; /*Adjust the min width of the menu*/
  --dp-overlay-col-padding: 3px; /*Padding in the overlay column*/
  --dp-menu-padding: 6px 20px; /*Menu padding*/

  /*Font sizes*/
  --dp-font-size: 1.1rem; /*Default font-size*/
  --dp-preview-font-size: 0.8rem; /*Font size of the date preview in the action row*/
  --dp-time-font-size: 0.8rem; /*Font size in the time picker*/

  /*Transitions*/
  --dp-animation-duration: 0.1s; /*Transition duration*/
  --dp-menu-appear-transition-timing: cubic-bezier(
    0.4,
    0,
    1,
    1
  ); /*Timing on menu appear animation*/
  --dp-transition-timing: ease-out; /*Timing on slide animations*/
}
</style>

<style scoped>
:deep(.dp__calendar_header_separator) {
  height: 1.8px;
}
:deep(.dp__today) {
  border: 1.8px solid #6096b4;
}
:deep(.dp__time_input) {
  margin-bottom: 20px;
}
:deep(.dp__theme_light) {
  --dp-background-color: #668ba7;
  --dp-text-color: #ffffff;
  --dp-hover-color: #ffffff;
  --dp-hover-text-color: #000000;
  --dp-primary-color: #ffffff;
  --dp-primary-disabled-color: #6bacea;
  --dp-primary-text-color: #000000;
  --dp-secondary-color: rgb(192, 196, 202);
  --dp-border-color: #6096b4;
  --dp-disabled-color: #565656;
  --dp-scroll-bar-background: #f3f3f3;
  --dp-tooltip-color: #fafafa;
  --dp-disabled-color-text: #3f72af;
  --dp-highlight-color: rgb(25 118 210 / 10%);
  --dp-range-between-dates-background-color: var(--dp-hover-color, #f3f3f3);
  --dp-range-between-dates-text-color: var(--dp-hover-text-color, #212121);
  --dp-range-between-border-color: var(--dp-hover-color, #f3f3f3);
}

.closed {
  position: absolute;
  top: 0;
  height: 40px;
  left: 105%;
  transform: translateX(-50%);
  z-index: 15;
  border-top-right-radius: 20px;
}
.fixed-top-right {
  position: fixed;
  top: 100px;
  right: 5px;
  z-index: 1000;
}
.rows {
  margin-left: 20px;
  margin-right: 20px;
  padding-bottom: 10px;
  margin-top: -40px;
}
.margins {
  padding-top: 0px;
  padding-bottom: 0px;
}
.card__content {
  display: flex;
  align-items: center;
  justify-content: flex-start;
}
.card__title {
  margin-right: 20px;
  font-weight: bold;
  font-size: 17px;
  color: var(--main-color);
}

.card__subtitle {
  font-weight: bold;
  font-size: 17px;
  color: var(--submain-color);
}

.card {
  --main-color: #000;
  --submain-color: #78858f;
  --bg-color: #fff;
  font-family:
    system-ui,
    -apple-system,
    BlinkMacSystemFont,
    'Segoe UI',
    Roboto,
    Oxygen,
    Ubuntu,
    Cantarell,
    'Open Sans',
    'Helvetica Neue',
    sans-serif;
  position: relative;
  width: 400px;
  height: 480px;
  display: flex;
  flex-direction: column;
  align-items: center;
  border-radius: 20px;
  background: var(--bg-color);
}

.card__img {
  height: 192px;
  width: 100%;
}

.card__img svg {
  height: 100%;
  border-radius: 20px 20px 0 0;
}

@keyframes animate {
  0% {
    opacity: 0;
    transform: rotate(45deg) translate(-20px, -20px);
  }
  50% {
    opacity: 1;
  }
  100% {
    opacity: 0;
    transform: rotate(45deg) translate(20px, 20px);
  }
}

button {
  width: 110px;
  height: 50px;
  cursor: pointer;
  display: flex;
  align-items: center;
  border-radius: 5px;
}

.background-video {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 53%;
  object-fit: cover; /* 비디오가 컨테이너를 꽉 채우도록 */
}

.overlay {
  position: absolute;
  top: 800px;
  left: 400px;
  z-index: 10;
}
</style>
