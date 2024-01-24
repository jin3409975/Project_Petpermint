import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useReserveStore = defineStore('reserve', () => {
  const date = ref('')

  const pickedDate = function (formatdate) {
    // 고른 예약날짜 저장 -> 로직 수정중
    date.value = formatdate
  }

  return { date, pickedDate }
})
