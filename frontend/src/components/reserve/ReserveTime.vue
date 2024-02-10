<script setup>
import { ref, watchEffect } from 'vue' // Import watchEffect here
import { useReserveStore } from '@/stores/reserve'
import { storeToRefs } from 'pinia'
import VueDatePicker from '@vuepic/vue-datepicker'
import '@vuepic/vue-datepicker/dist/main.css'

const reserveStore = useReserveStore()
const { reservedate, doctorList, starttime, endtime } = storeToRefs(reserveStore)

const date = ref()
const startTime = ref({ hours: 0, minutes: 0 })

watchEffect(() => {
  reservedate.value = formatDate(date.value)
  starttime.value = formatstart(date.value)
  endtime.value = formatend(date.value)
})
// const selectDate = () => {
//   if (reserveStore.type == 1) {
//     reserveStore.getdoctorList()
//   } else {
//     reserveStore.gethospitalList()
//   }
//   reserveStore.getpetList()
//   // starttime , endtime 에 저장 .... 선택한 시간에서 20분 더한 시간을 추출하는 법을 모르겟습니다
//   console.log(doctorList)
// }
function formatDate(selected) {
  if (selected) {
    const date = new Date(selected)
    const year = date.getFullYear()
    const month = ('0' + (1 + date.getMonth())).slice(-2)
    const day = ('0' + date.getDate()).slice(-2)
    const hour = ('0' + date.getHours()).slice(-2)
    const min = ('0' + date.getMinutes()).slice(-2)

    console.log(date)
    return `${year}-${month}-${day} ${hour}:${min}`
  }
  return ''
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
</script>

<template>
  <div>
    <VueDatePicker
      v-model="date"
      :start-time="startTime"
      minutes-increment="20"
      inline
      time-picker-inline
      auto-apply
      :disabled-times="disabledTimes"
    >
    </VueDatePicker>

    <!-- <button class="button" @click="selectDate">시간 선택 완료</button> -->
    <!-- {{ starttime }} {{ endtime }} -->
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
  --dp-button-height: 45px; /*Size for buttons in overlays*/
  --dp-month-year-row-height: 45px; /*Height of the month-year select row*/
  --dp-month-year-row-button-size: 30px; /*Specific height for the next/previous buttons*/
  --dp-cell-size: 55px; /*Width and height of calendar cell*/
  --dp-cell-padding: 5px; /*Padding in the cell*/
  --dp-common-padding: 10px; /*Common padding used*/
  --dp-menu-min-width: 400px; /*Adjust the min width of the menu*/
  --dp-overlay-col-padding: 3px; /*Padding in the overlay column*/
  --dp-time-inc-dec-button-size: 202px; /*Sizing for arrow buttons in the time picker*/
  --dp-menu-padding: 6px 30px; /*Menu padding*/

  /*Font sizes*/
  --dp-font-size: 1.2rem; /*Default font-size*/
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
:deep(.dp__time_input) {
  margin-bottom: 20px;
}
:deep(.dp__theme_light) {
  --dp-background-color: #36577e;
  --dp-text-color: #ffffff;
  --dp-hover-color: #f9f7f7;
  --dp-hover-text-color: #212121;
  --dp-primary-color: #f9f7f7;
  --dp-primary-disabled-color: #6bacea;
  --dp-primary-text-color: #000000;
  --dp-secondary-color: #b7becb;
  --dp-border-color: #6096b4;
  --dp-disabled-color: rgb(187, 187, 187);
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
