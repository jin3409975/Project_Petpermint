<script setup>
import { ref, watchEffect } from 'vue' // Import watchEffect here
import { useReserveStore } from '@/stores/reserve'
import { storeToRefs } from 'pinia'
import VueDatePicker from '@vuepic/vue-datepicker'
import '@vuepic/vue-datepicker/dist/main.css'
import axios from 'axios'
import { useRouter } from 'vue-router'

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
        alert('해당 날짜에 예약이 없습니다.')
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
</script>

<template>
  <div style="">
    <div></div>
    <div style="margin-top: 100px">
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
      >
      </VueDatePicker>
    </div>
    <div>
      <v-card class="mx-auto hospital-list-card" max-width="450" style="border: 1px solid #36577e">
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
    <div>
      <v-dialog v-model="dialog" width="400px">
        <!-- 온라인 초기 상담 -->
        <v-carousel height="480" hide-delimiter-background show-arrows="hover" color="#668BA7">
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

/* Button base styles */
.button {
  padding: 10px 20px; /* Adjust padding as needed */
  background-color: #007bff; /* Use your primary color here */
  color: white; /* Text color */
  border: none; /* No border */
  outline: none; /* Remove outline */
  border-radius: 20px; /* Rounded corners */
  cursor: pointer; /* Mouse pointer on hover */
  font-size: 1rem; /* Adjust font size as needed */
  font-weight: bold; /* Bold font */
  text-transform: uppercase; /* Uppercase text */
  letter-spacing: 0.05em; /* Spacing between letters */
  transition:
    background-color 0.3s,
    box-shadow 0.3s; /* Transition for smooth effect on hover */
}

/* Button hover styles */
.button:hover {
  background-color: #0056b3; /* Darken the color on hover */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Add shadow on hover */
}

/* Button focus styles */
.button:focus {
  box-shadow: 0 0 0 2px rgba(0, 123, 255, 0.5); /* Add focus ring */
}

/* Button active styles */
.button:active {
  background-color: #004b9b; /* Even darker color on click */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2); /* Smaller shadow on click */
}
</style>
