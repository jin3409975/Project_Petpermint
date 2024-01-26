import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useReserveStore = defineStore('reserve', () => {
  // type : 1초기상담, 2 병원진료, 3 응급예약
  const type = ref(0)
  // 상담 날짜 date
  const reservedate = ref('')
  function pickedDate(newDate) {
    reservedate.value = newDate
  }
  // 예약 병원 Hospital.id
  const reservehospital = ref(null)
  //예약병원 or 수의사 리스트 임시데이터 생성 -> 추후 axios요청예정
  const doctorList = ref(['이민지', '이채은', '김재민'])
  // 예약 시간 time
  const starttime = ref('')
  const endtime = ref('')
  // 예약가능 시간 리스트 임시 데이터 생성-> 추후 axios GET요청
  const timeList = ref([
    { start: '17:00', end: '17:20' },
    { start: '18:00', end: '18:20' },
    {
      start: '19:00',
      end: '19:20'
    }
  ])
  const reservepet = ref('')
  const petList = ref(['초롱', '금동', '루이', '솔이', '코코'])

  return {
    reservedate,
    type,
    reservehospital,
    starttime,
    endtime,
    pickedDate,
    timeList,
    petList,
    reservepet,
    doctorList
  }
})
