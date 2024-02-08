<script>
import { Qalendar } from 'qalendar'
import { myPageStore } from '@/stores/mypage.js'
import { onBeforeMount, ref } from 'vue'
import { storeToRefs } from 'pinia'

export default {
  components: {
    Qalendar
  },
  setup() {
    const mypagestore = myPageStore()
    const { myevents } = storeToRefs(mypagestore)

    // Initialize config as a reactive reference
    const config = ref({
      // Configuration details go here
    })

    onBeforeMount(() => {
      console.log('Component is about to be mounted')
      mypagestore.getmyevents()
    })

    // Return all reactive references and methods you want to use in the template
    return {
      events: myevents,
      config
    }
  }
}
</script>

<template>
  <div>
    <p>나의 예약 내역 보기</p>
    <Qalendar :events="events" :config="config" />
  </div>
</template>

<style>
@import 'qalendar/dist/style.css';
.calendar-month__weekday[data-v-034f06d8] {
  min-height: 80px;
}
</style>
