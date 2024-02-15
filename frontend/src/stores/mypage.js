import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

export const myPageStore = defineStore('mypage', () => {
  const { VITE_APP_SERVER_URI, VITE_APP_CLIENT_URI } = import.meta.env
  // const API_URL = `${VITE_SERVER_URI}/reserve/`
  // axios 요청 예정
  const myevents = ref([
    // 사용자 예약 리스트
  ])
  const vetevents = ref([]) // 수의사의 예약 내역
  const useremail = localStorage.getItem('useremail')
  const licenseNumber = localStorage.getItem('licenseNumber')
  function getmyevents() {
    //일반 회원의 예약 내역을 불러오기
    axios({
      method: 'get',
      url: VITE_APP_SERVER_URI + '/reserve/all/normal',
      params: {
        userId: useremail
      }
    }).then((r) => {
      // console.log(r)
      if (r.data.statusCode == 200) {
        console.log('이벤트 불러와', r.data.result)
        extractEvents(r.data.result)
        myevents.value = extractEvents(r.data.result)
        // console.log('myevents', myevents.value)
      }
    })
  }
  function extractEvents(data) {
    return data
      .map((item) => {
        if (item.time.trim() === '') {
          // time이 빈 값인지 확인
          return null
        }
        //예약 날짜
        const date = item.time.split(' ')[0]
        // 시작 시간을 Date 객체로 파싱
        const startTime = item.time
        const endTime = date + ' ' + addMinutes(item.time.split(' ')[1], 20) // 끝나는 시간을 계산하기 위해 20분을 추가
        // const endTime = setMinutes(startTime.getMinutes() + 20)
        return {
          id: item.appointId,
          title: item.type === 1 ? '온라인 상담' : item.type === 2 ? '병원 진료' : '알 수 없음',
          time: {
            start: startTime,
            end: endTime
          },
          color: item.type === 1 ? 'blue' : item.type === 2 ? 'green' : 'yellow',
          description: item.note,
          isEditable: true
          // isCustom: true
        }
      })
      .filter((event) => event !== null)
  }
  function addMinutes(timeStr, minsToAdd) {
    // 문자열 시간을 Date 객체로 변환
    const timeParts = timeStr.split(':')
    const date = new Date()
    date.setHours(parseInt(timeParts[0], 10), parseInt(timeParts[1], 10) + minsToAdd, 0)

    // 시간과 분을 가져와서 필요시 0을 추가하여 두 자리 수로 만듦
    let hours = date.getHours()
    let minutes = date.getMinutes()
    hours = hours < 10 ? '0' + hours : hours
    minutes = minutes < 10 ? '0' + minutes : minutes

    // 수정된 시간을 문자열 형태로 반환
    return hours + ':' + minutes
  }

  //수의사 메인페이지 예약 조회
  function getvetevents() {
    //수의사 회원의 예약 내역을 불러오기
    axios({
      method: 'get',
      url: VITE_APP_SERVER_URI + '/reserve/all/expert',
      params: { licenseNumber: licenseNumber }
    }).then((r) => {
      // console.log(r)
      if (r.data.statusCode == 200) {
        extractEvents(r.data.result)
        console.log('vetdataa', r.data.result)
        vetevents.value = extractEvents(r.data.result)
        // console.log('vetevents', vetevents.value)
      }
    })
  }
  return { myevents, vetevents, getmyevents, getvetevents, extractEvents, addMinutes }
})
