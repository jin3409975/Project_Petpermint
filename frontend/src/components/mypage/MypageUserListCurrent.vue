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

    onBeforeMount(async () => {
      //console.log('Component is about to be mounted')
      await mypagestore.getmyevents()
    })
    const navigationToinfo = function () {
      router.push({ name: 'mypage-user-info' })
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
  <div
    style="
      margin-bottom: 50px;
      height: 100vh;
      display: flex;
      justify-content: center;
      align-items: center;
      margin-top: 40px;
    "
  >
    <div class="mycalendar">
      <div class="flex-container">
        <v-btn color="#E27878" @click="navigationToinfo" style="margin-bottom: 20px"
          >마이페이지로 돌아가기</v-btn
        >
        <p style="font-size: 35px; font-weight: bold; margin-left: 300px">나의 예약 내역 보기</p>
      </div>
      <Qalendar :events="events" :config="config" />
    </div>
  </div>
</template>

<style>
@import 'qalendar/dist/style.css';
.calendar-month__weekday[data-v-034f06d8] {
  min-height: 80px;
}
.flex-container {
  display: flex; /* Flexbox 레이아웃 활성화 */
  align-items: center; /* 수직 방향 중앙 정렬 */
  gap: 10px; /* 요소 사이의 간격 */
}

.mycalendar {
  height: auto;
  width: 80%;
  justify-content: left;
  flex-direction: column;
  display: flex;
  margin-bottom: 130px;
  margin-top: 200px;
}
.pinkbutton {
  background-color: #ffd0d0;
  width: 200px;
  margin-bottom: 10px;
  height: 50px;
  float: left;
  margin-top: 50px;
}
</style>
