<script setup>
import { ref } from 'vue'
import { useReserveStore } from '@/stores/reserve'
import { storeToRefs } from 'pinia'
import { createVuetify } from 'vuetify'

const reserveStore = useReserveStore()
const { reservedate, starttime, endtime, timeList } = storeToRefs(reserveStore)

const vuetify = createVuetify({
  theme: {
    defaultTheme: 'light'
    // ...
  }
})
// 예정: axios 가능 시간 리스트 불러오기로 수정

const selectTime = (item) => {
  console.log('확인', item)
  starttime.value = item.start
  endtime.value = item.end
}
</script>

<template>
  <div>
    <p>예약 시간 선택 페이지 입니다</p>
    <p>해당 날짜 {{ reservedate }}의 상담 가능 시간은</p>
  </div>

  <v-card class="mx-auto" max-width="300">
    <v-list-item v-for="(time, i) in timeList" :key="i" :value="time" @click="selectTime(time)">
      <v-list-item-title v-text="`${time.start}-${time.end}`"></v-list-item-title>
    </v-list-item>
  </v-card>
</template>

<style scoped></style>
