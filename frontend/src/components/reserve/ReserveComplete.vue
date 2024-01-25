<script setup>
import { useReserveStore } from '@/stores/reserve'
import { storeToRefs } from 'pinia'

const reserveStore = useReserveStore()
const { reservedate, starttime, endtime, reservehospital, reservepet } = storeToRefs(reserveStore)
</script>

<template>
  <div>
    <p>예약완료 페이지 입니다</p>
    <p>예약 날짜 {{ reservedate }}</p>
    <p>예약시간 {{ starttime }}-{{ endtime }}</p>
    <p>예약병원 {{ reservehospital }}</p>
    <p>예약 자녀 {{ reservepet }}</p>
  </div>
</template>

<style scoped></style>
