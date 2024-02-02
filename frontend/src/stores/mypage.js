import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
export const myPageStore = defineStore('mypage', () => {
  const { VITE_APP_SERVER_URI } = import.meta.env
  // const API_URL = `${VITE_SERVER_URI}/reserve/`
  // axios 요청 예정
  const myevents = ref([
    // title : 일정이름 start(2024-01-31T05:00-06:00) 일정 시작일 end:일정 완료일, url:일정클릭시 이동
    // { title: 'Meeting1', start: '2024-01-31' },
    // { title: 'Meeting2', start: '2024-01-30' }
  ])

  function getmyevents() {
    axios({
      method: 'get',
      url: VITE_APP_SERVER_URI + '/reserve/all',
      params: {
        userId: 'alswl9703@naver.com'
      }
    }).then((r) => {
      if (r.data.statusCode == 200) {
        extractEvents(r.data.result)
        myevents.value = extractEvents(r.data.result)
        console.log('myevents', myevents.value)
      }
    })
  }
  function extractEvents(data) {
    // data 배열을 map 함수를 사용하여 새 배열 생성
    return data.map((item) => ({
      id: item.appointId,
      title: item.type === 1 ? '온라인 상담' : item.type === 2 ? '병원 진료' : '알 수 없음', // type 값에 따라 title 결정, // type 값을 title로 매핑
      start: item.time, // time 값을 start로 매핑
      color: item.type === 1 ? '#d2e0fb' : item.type === 2 ? '#53b257' : '#53b257'
    }))
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
  return { myevents, userpastList, vetpastList, getmyevents, extractEvents }
})
