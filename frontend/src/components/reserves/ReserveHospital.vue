<script setup>
import { onMounted, ref } from 'vue'
import { useReserveStore } from '@/stores/reserve'
import { storeToRefs } from 'pinia'

const reserveStore = useReserveStore()
const { date } = storeToRefs(reserveStore)
</script>

<template>
  <div>
    <p>병원선택입니다.{{ date }}</p>
  </div>
</template>

<style scoped></style>
