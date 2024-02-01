import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
export const myPageStore = defineStore('mypage', () => {
  const { VITE_SERVER_URI } = import.meta.env
  // const API_URL = `${VITE_SERVER_URI}/reserve/`
  // axios 요청 예정
  const myevents = ref([
    // title : 일정이름 start(2024-01-31T05:00-06:00) 일정 시작일 end:일정 완료일, url:일정클릭시 이동
    // { title: 'Meeting1', start: '2024-01-31' },
    // { title: 'Meeting2', start: '2024-01-30' }
  ])

  function getmyevents() {
    console.log('test')
    axios({
      method: 'get',
      url: VITE_SERVER_URI + '/reserve/hospital/data',
      params: {
        userId: 'alswl9703@naver.com'
      }
    }).then((r) => {
      console.log(r)
      if (r.data.statusCode == 200) {
        console.log(r)
        myevents.value = r
      }
    })
  }
  // 일반 유저 user mypage 과거 상담 내역 리스트
  const userpastList = ref([
    { title: '2024-01-01', text: '초콜릿먹은 강아지' },
    { title: '2024-01-11', text: '초콜릿먹은 고양이' },
    { title: '2024-01-21', text: '초콜릿 못 먹은 강아지' },
    { title: '2024-01-3', text: '초콜릿 못 먹은 고양이' },
    { title: '2024-01-30', text: '초콜릿 먹고 싶은 강아지' },
    { title: '2024-01-31', text: '초콜릿 먹고 싶은 고양이' }
  ])
  // 수의사 과거 상담 리스트
  const vetpastList = ref([
    { title: '2024-01-01', text: '강아지1' },
    { title: '2024-01-11', text: '강아지2' },
    { title: '2024-01-21', text: '강아지3' },
    { title: '2024-01-3', text: '고양이1' },
    { title: '2024-01-30', text: '고양이2' },
    { title: '2024-01-31', text: '고양이3' }
  ])
  return { myevents, userpastList, vetpastList, getmyevents }
})
