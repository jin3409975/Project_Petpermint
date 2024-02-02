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
const selectDate = () => {
  if (reserveStore.type == 1) {
    reserveStore.getdoctorList()
  } else {
    reserveStore.gethospitalList()
  }
  reserveStore.getpetList()
  // starttime , endtime 에 저장 .... 선택한 시간에서 20분 더한 시간을 추출하는 법을 모르겟습니다
  console.log(doctorList)
}
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
    <p>예약 시간 선택 페이지 입니다</p>
    <!-- <p>해당 날짜 {{ reservedate }}의 상담 가능 시간은</p> -->
  </div>
  <div>
    <VueDatePicker
      v-model="date"
      :start-time="startTime"
      minutes-increment="20"
      inline
      auto-apply
      :disabled-times="disabledTimes"
    >
    </VueDatePicker>

    <button @click="selectDate">select</button>
    {{ starttime }} {{ endtime }}
  </div>
</template>

<style scoped>
.dp__theme_light {
  --dp-background-color: #fff;
  --dp-text-color: #212121;
  --dp-hover-color: #f3f3f3;
  --dp-hover-text-color: #212121;
  --dp-hover-icon-color: #7c98cc;
  --dp-primary-color: #1976d2;
  --dp-primary-disabled-color: #6bacea;
  --dp-primary-text-color: #f8f5f5;
  --dp-secondary-color: #c0c4cc;
  --dp-border-color: #ddd;
  --dp-menu-border-color: #ddd;
  --dp-border-color-hover: #aaaeb7;
  --dp-disabled-color: #f6f6f6;
  --dp-scroll-bar-background: #f3f3f3;
  --dp-scroll-bar-color: #959595;
  --dp-success-color: #76d275;
  --dp-success-color-disabled: #a3d9b1;
  --dp-icon-color: #959595;
  --dp-danger-color: #ff6f60;
  --dp-marker-color: #ff6f60;
  --dp-tooltip-color: #fafafa;
  --dp-disabled-color-text: #8e8e8e;
  --dp-highlight-color: rgb(25 118 210 / 10%);
  --dp-range-between-dates-background-color: var(--dp-hover-color, #f3f3f3);
  --dp-range-between-dates-text-color: var(--dp-hover-text-color, #212121);
  --dp-range-between-border-color: var(--dp-hover-color, #f3f3f3);
}
</style>
