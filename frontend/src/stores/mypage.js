import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
export const myPageStore = defineStore('mypage', () => {
  const { VITE_APP_SERVER_URI } = import.meta.env
  // const API_URL = `${VITE_SERVER_URI}/reserve/`
  // axios 요청 예정
  const myevents = ref([
    // 사용자 예약 리스트
  ])
  const vetevents = ref([]) // 수의사의 예약 내역
  const useremail = localStorage.getItem('useremail')

  function getmyevents() {
    //일반 회원의 예약 내역을 불러오기
    axios({
      method: 'get',
      url: VITE_APP_SERVER_URI + '/reserve/all',
      params: {
        userId: useremail
      }
    }).then((r) => {
      if (r.data.statusCode == 200) {
        console.log(r.data.result)
        extractEvents(r.data.result)
        myevents.value = extractEvents(r.data.result)
        console.log('myevents', myevents.value)
      }
    })
  }
  function extractEvents(data) {
    return data.map((item) => {
      // 시작 시간을 Date 객체로 파싱
      const startTime = new Date(item.time)

      // 끝나는 시간을 계산하기 위해 20분을 추가
      const endTime = new Date(startTime.getTime())
      endTime.setMinutes(startTime.getMinutes() + 20)

      // Date 객체를 원하는 형태의 문자열로 변환
      const formatDateTime = (date) => date.toISOString().replace('T', ' ').substring(0, 16)

      return {
        id: item.appointId,
        title: item.type === 1 ? '온라인 상담' : item.type === 2 ? '병원 진료' : '알 수 없음',
        time: {
          start: formatDateTime(startTime),
          end: formatDateTime(endTime)
        },
        color: item.type === 1 ? 'blue' : item.type === 2 ? 'green' : 'yellow'
      }
    })
  }
  function getvetevents() {
    //수의사 회원의 예약 내역을 불러오기
    axios({
      method: 'get',
      url: VITE_APP_SERVER_URI + '',
      params: {}
    }).then((r) => {
      if (r.data.statusCode == 200) {
        extractEvents(r.data.result)
        vetevents.value = extractEvents(r.data.result)
        console.log('vetevents', vetevents.value)
      }
    })
  }
  return { myevents, vetevents, getmyevents, getvetevents, extractEvents }
})
