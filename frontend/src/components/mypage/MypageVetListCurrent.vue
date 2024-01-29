<script>
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import { myPageStore } from '@/stores/mypage.js'
import { watchEffect, ref } from 'vue'

export default {
  components: {
    FullCalendar
  },
  setup() {
    const mypagestore = myPageStore()

    // Use a ref for calendarOptions to ensure reactivity
    const calendarOptions = ref({
      plugins: [dayGridPlugin],
      initialView: 'dayGridWeek',
      weekends: true,
      selectable: true,
      events: mypagestore.myevents //mypage.store에서 일정 axios 요청
    })

    return { calendarOptions }
  }
}
</script>

<template>
  <h1>수의사 예약 현황</h1>
  <FullCalendar :options="calendarOptions"> </FullCalendar>
</template>
