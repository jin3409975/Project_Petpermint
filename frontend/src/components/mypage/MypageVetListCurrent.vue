<script>
import { Qalendar } from 'qalendar'
import { myPageStore } from '@/stores/mypage.js'
import { onBeforeMount, ref } from 'vue'
import { storeToRefs } from 'pinia'
import { useRouter } from 'vue-router'
export default {
  components: {
    Qalendar
  },
  setup() {
    const mypagestore = myPageStore()
    const { myevents } = storeToRefs(mypagestore)
    const router = useRouter()
    // Initialize config as a reactive reference
    const config = ref({
      // Configuration details go here
      defaultMode: 'month',
      style: {
        colorSchemes: {
          meetings: {
            //일정 css
            color: '#fff',
            backgroundColor: '#131313'
          },
          sports: {
            color: '#fff',
            backgroundColor: '#ff4081'
          }
        }
      }
    })

    onBeforeMount(() => {
      console.log('Component is about to be mounted')
      mypagestore.getmyevents()
    })
    const navigationToinfo = function () {
      router.push({ name: 'mypage-vet-info' })
    }
    // Return all reactive references and methods you want to use in the template
    return {
      events: myevents,
      config: config,
      navigationToinfo
    }
  }
}
</script>

<template>
  <p>나의 예약 내역 보기</p>

  <div class="mycalendar">
    <button class="pinkbutton" @click="navigationToinfo">나의 정보 보기</button>
    <Qalendar :events="events" :config="config" />
  </div>
</template>

<style>
@import 'qalendar/dist/style.css';
.calendar-month__weekday[data-v-034f06d8] {
  min-height: 80px;
}
.mycalendar {
  height: auto;
  width: 80%;
  justify-content: left;
  flex-direction: column;
  display: flex;
  margin-bottom: 100px;
  margin: auto;
}
.pinkbutton {
  background-color: #ffd0d0;
  width: 200px;
  margin-bottom: 10px;
  height: 50px;
  float: left;
}
</style>
