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
      initialView: 'dayGridWeek', // 월별 보기 initialView: 'dayGridMonth'
      weekends: true,
      selectable: true,
      events: mypagestore.myevents
    })

    return { calendarOptions }
  }
}
</script>

<template>
  <h1>나의 예약 현황</h1>
  <FullCalendar :options="calendarOptions"> </FullCalendar>
</template>
