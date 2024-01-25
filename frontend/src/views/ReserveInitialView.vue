<script setup>
import ReserveDate from '@/components/reserve/ReserveDate.vue'
import ReserveHospital from '@/components/reserve/ReserveHospital.vue'
import ReserveTIme from '@/components/reserve/ReserveTime.vue'
import ReservePet from '@/components/reserve/ReservePet.vue'
import { useReserveStore } from '@/stores/reserve.js'
import { computed } from 'vue'
const reserveStore = useReserveStore()
const selectedDate = computed(() => !!reserveStore.date)
</script>

<template>
  <div>
    <p>초기상담페이지 입니다</p>
  </div>
  <ReserveDate />
  <ReserveHospital></ReserveHospital>
  <ReserveTIme></ReserveTIme>
  <ReservePet></ReservePet>
</template>

<style scoped></style>
