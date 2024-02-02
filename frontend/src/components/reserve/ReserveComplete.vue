<script setup>
import { useReserveStore } from '@/stores/reserve'
import { storeToRefs } from 'pinia'
const reserveStore = useReserveStore()
const { reservedate, starttime, endtime, petname, hospitalname } = storeToRefs(reserveStore)
</script>

<template>
  <div>
    <p>예약완료 페이지 입니다</p>
    <p>예약 시간 {{ reservedate }}-{{ endtime }}</p>

    <p>{{ hospitalname }}</p>
    <p>예약 자녀 {{ petname }}</p>
    <p></p>
  </div>
</template>

<style scoped></style>
