<script>
import { createVuetify, useDate } from 'vuetify'
import { useReserveStore } from '@/stores/reserve.js'
import { ref, watchEffect } from 'vue'

export const vuetify = createVuetify({
  theme: {
    defaultTheme: 'light'
  }
})

export default {
  setup() {
    // reserveStore에 선택 날짜 저장
    const reserveStore = useReserveStore()
    const selectedDate = ref(null)
    const formattedDate = ref('')

    watchEffect(() => {
      formattedDate.value = formatDate(selectedDate.value)
      reserveStore.pickedDate(formattedDate.value)
    })

    return {
      selectedDate,
      formattedDate
    }
  }
}
// ex 2024-01-24형태로 날짜 포맷
function formatDate(selectedDate) {
  if (selectedDate) {
    const date = new Date(selectedDate)
    const year = date.getFullYear()
    const month = ('0' + (1 + date.getMonth())).slice(-2)
    const day = ('0' + date.getDate()).slice(-2)
    return `${year}-${month}-${day}`
  }
  return ''
}
</script>

<template>
  <div>
    <v-app>
      <v-container>
        <v-date-picker v-model="selectedDate"></v-date-picker>
      </v-container>
    </v-app>
  </div>
  Selected Date: {{ selectedDate }}
</template>

<style scoped></style>
