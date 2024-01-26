<script setup>
import ReserveDate from '@/components/reserve/ReserveDate.vue'
import ReserveHospital from '@/components/reserve/ReserveHospital.vue'
import ReserveTIme from '@/components/reserve/ReserveTime.vue'
import ReservePet from '@/components/reserve/ReservePet.vue'
import ReserveComplete from '@/components/reserve/ReserveComplete.vue'
import { useReserveStore } from '@/stores/reserve.js'
import { onMounted } from 'vue'
import { storeToRefs } from 'pinia'

const reserveStore = useReserveStore()
const { type } = storeToRefs(reserveStore)

onMounted(() => {
  // 페이지 마운티드 될때 예약 타입 1로 설정 , 초기상담
  type.value = 1
})
</script>

<template>
  <div>
    <p>초기상담페이지 입니다</p>
  </div>

  <div class="box">
    <ReserveDate />
  </div>
  <div class="box">
    <ReserveTIme />
  </div>
  <div class="box">
    <ReserveHospital />
  </div>
  <div class="box">
    <ReservePet />
  </div>
  <div>
    <ReserveComplete />
  </div>
</template>

<style>
.box {
  border: 1px black solid;
  width: 500px;
}
</style>
