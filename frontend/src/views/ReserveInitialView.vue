<script setup>
import ReserveDate from '@/components/reserves/ReserveDate.vue'
import ReserveHospital from '@/components/reserves/ReserveHospital.vue'
import ReserveTIme from '@/components/reserves/ReserveTIme.vue'
import ReservePet from '@/components/reserves/ReservePet.vue'
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

  <ReserveHospital v-if="selectedDate.value"></ReserveHospital>
  <ReserveTIme></ReserveTIme>
  <ReservePet></ReservePet>
</template>

<style scoped></style>
