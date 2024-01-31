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
  reserveStore.getdoctorList()
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
</script>

<template>
  <div>
    <p>예약 시간 선택 페이지 입니다</p>
    <!-- <p>해당 날짜 {{ reservedate }}의 상담 가능 시간은</p> -->
  </div>
  <div>
    <VueDatePicker v-model="date" :start-time="startTime" minutes-increment="20" inline auto-apply>
    </VueDatePicker>

    <button @click="selectDate">select</button>
    {{ starttime }} {{ endtime }}
  </div>
</template>

<style scoped></style>
