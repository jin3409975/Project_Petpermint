import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useReserveStore = defineStore('reserve', () => {
  const date = ref('')
  function pickedDate(newDate) {
    date.value = newDate
  }

  return { date, pickedDate }
})
