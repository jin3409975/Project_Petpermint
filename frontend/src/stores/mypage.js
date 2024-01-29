import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const myPageStore = defineStore('mypage', () => {
  // axios 요청 예정
  const myevents = [
    // title : 일정이름 start(2024-01-31T05:00-06:00) 일정 시작일 end:일정 완료일, url:일정클릭시 이동
    { title: 'Meeting1', start: '2024-01-31' },
    { title: 'Meeting2', start: '2024-01-30' }
  ]
  return { myevents }
})
