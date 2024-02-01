<script setup>
import { onMounted, ref } from 'vue'
import { useReserveStore } from '@/stores/reserve'
import { storeToRefs } from 'pinia'

const reserveStore = useReserveStore()
const { reservedate, doctorList, reservehospitalno, type, reservelicense, hospitalList, petList } =
  storeToRefs(reserveStore)

const selectDoctor = (doctor) => {
  reservelicense.value = doctor.licenseNumber
}
const selectHospital = (hospital) => {
  console.log('hos', hospital)
  reservehospitalno.value = hospital.hospitalNo
  // 예약에 필요한 병원 번호를 store변수에 저장
}
onMounted(() => {
  doctorList.value = null
  hospitalList.value = null
  //petList.value = null
})
</script>

<template>
  <div>
    <p>병원선택입니다.</p>
    <p>선택한 시간 {{ reservedate }}</p>
  </div>
  <div v-if="type == 1">
    <p>선택한 시간 {{ reservedate }}에 상담 가능 수의사는 다음과 같습니다</p>
    <v-card class="mx-auto" max-width="300">
      <v-list-item
        v-for="(doctor, i) in doctorList"
        :key="i"
        :value="doctor"
        @click="selectDoctor(doctor)"
      >
        <v-list-item-title v-text="doctor.userName"></v-list-item-title>
        <v-list-item-subtitle v-text="doctor.hospitalName"></v-list-item-subtitle>
      </v-list-item>
    </v-card>
  </div>
  <div v-else="type == 2">
    <p>선택한 시간 {{ reservedate }}에 진료 가능 병원은 다음과 같습니다</p>
    <v-card class="mx-auto" max-width="300">
      <v-list-item
        v-for="(hospital, i) in hospitalList"
        :key="i"
        :value="hospital"
        @click="selectHospital(hospital)"
      >
        <v-list-item-title v-text="hospital.name"></v-list-item-title>
        <v-list-item-subtitle v-text="hospital.roadNumberAddress"></v-list-item-subtitle>
      </v-list-item>
    </v-card>
  </div>
</template>

<style scoped></style>
