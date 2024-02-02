<script>
import FullCalendar from '@fullcalendar/vue3'
// import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import { myPageStore } from '@/stores/mypage.js'
import { ref, onBeforeMount } from 'vue'
import { storeToRefs } from 'pinia'
export default {
  components: {
    FullCalendar
  },
  setup() {
    const mypagestore = myPageStore()
    const { myevents } = storeToRefs(mypagestore)
    onBeforeMount(() => {
      console.log('onmounted')
      mypagestore.getmyevents()
    })
    // Use a ref for calendarOptions to ensure reactivity
    const calendarOptions = ref({
      plugins: [timeGridPlugin],
      initialView: 'timeGridWeek', // 월별 보기 initialView: 'dayGridMonth'
      weekends: true,
      selectable: true,
      events: myevents,
      allDay: false
      // eventColor: '#378006'
    })

    return { calendarOptions }
  }
}
</script>

<template>
  <h1>나의 예약 현황</h1>
  <button @click="tes">test</button>
  <FullCalendar :options="calendarOptions"> </FullCalendar>
</template>
<style></style>
