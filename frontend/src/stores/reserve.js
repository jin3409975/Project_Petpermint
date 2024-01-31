import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import { useRouter } from 'vue-router'

export const useReserveStore = defineStore('reserve', () => {
  const router = useRouter()
  // type : 1초기상담, 2 병원진료, 3 응급예약
  const type = ref(0)
  // 상담 날짜 date + " " + time
  const reservedate = ref('')
  const starttime = ref('')
  const endtime = ref('')
  // 예약 병원 Hospital.id
  const reservehospital = ref(null)
  //예약병원 or 수의사 리스트 임시데이터 생성 -> 추후 axios요청예정
  const doctorList = ref([])
  const hospitalList = ref([])
  const reservelicense = ref()
  const reservepet = ref() // 예약할 펫 인덱스 저장
  const reservepetindex = ref()

  const petList = ref(['초롱', '금동', '루이', '솔이', '코코'])

  const API_URL = 'http://localhost:8080/reserve/'

  function consultCreate() {
    axios({
      method: 'post',
      url: API_URL + 'consult/create',
      data: {
        userId: '123',
        type: 1,
        time: reservedate.value,
        licenseNumber: '12345', // reservelicense
        animalId: reservepet.value
      }
    }).then((r) => {
      console.log(r)
      console.log(r.data.statusCode)
      if (r.data.statusCode == 200) {
        console.log('qwewqeqweeqw')
        router.push({ name: 'reserve-initial-complete' })
      }
    })
  }
  function hospitalCreate() {
    axios({
      method: 'post',
      url: API_URL + 'hospital/create',
      data: {
        userId: '123',
        type: 2,
        time: reservedate.value,
        hospitalNo: 30, //reservehospital
        animalId: reservepet.value
      }
    }).then((r) => {
      console.log(r)
      console.log(r.data.statusCode)
      if (r.data.statusCode == 200) {
        console.log('qwewqeqweeqw')
        router.push({ name: 'reserve-initial-complete' })
      }
    })
  }
  // 초기상담 시간 가능한 수의사, 병원 리스트 + 수의사 면허번호도 추가로 넘어왔으면
  function getdoctorList() {
    axios({
      method: 'get',
      url: API_URL + 'consult/list/',
      params: {
        startTime: starttime.value,
        endTime: endtime.value,
        time: reservedate.value
      }
    })
      .then((res) => {
        doctorList.value = res.data.result
        // console.log(reservedate.value)
        console.log(res.data)
      })
      .catch((err) => console.log(err))
  }
  // 병원 진료예약 가능 병원 병원주소 리스트 + 병원 번호? 도 함께 넘어왔으면 좋겠음
  function gethospitalList() {
    axios({
      method: 'get',
      url: API_URL + 'hospital/list/',
      params: {
        time: reservedate.value
      }
    })
      .then((res) => {
        hospitalList.value = res.data.result
        // console.log(reservedate.value)
        console.log(res.data)
      })
      .catch((err) => console.log(err))
  }
  return {
    reservedate, //yyyy-mm-dd
    type,
    reservehospital, //병원 이름, 수의사 이름
    petList,
    reservepet, //고른 펫
    doctorList,
    consultCreate,
    reservepetindex,
    hospitalCreate,
    getdoctorList,
    gethospitalList,
    reservelicense,
    hospitalList,
    starttime,
    endtime
  }
})
