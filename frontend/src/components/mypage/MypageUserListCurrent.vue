<script>
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import { myPageStore } from '@/stores/mypage.js'
import { ref } from 'vue'

export default {
  components: {
    FullCalendar
  },
  setup() {
    const mypagestore = myPageStore()

    // Use a ref for calendarOptions to ensure reactivity
    const calendarOptions = ref({
      plugins: [timeGridPlugin],
      initialView: 'timeGridWeek', // 월별 보기 initialView: 'dayGridMonth'
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
<style>
.fc-h-event .fc-event-title-container {
  flex-grow: 1;
  flex-shrink: 1;
  min-width: 0px;
  min-height: 50px;
}
</style>
