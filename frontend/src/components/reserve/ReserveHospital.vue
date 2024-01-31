<script setup>
import { onMounted, ref } from 'vue'
import { useReserveStore } from '@/stores/reserve'
import { storeToRefs } from 'pinia'

const reserveStore = useReserveStore()
const { reservedate, doctorList, reservehospital, type, reservelicense, hospitalList } =
  storeToRefs(reserveStore)

const selectDoctor = (doctor) => {
  reservelicense.value = doctor.licenseNumber
  // doctor.licenseNumber를 store의 licensenumber에 저장
}
const selectHospital = (hospital) => {
  reservehospital.value = hospital.number
  // 예약에 필요한 병원 번호를 store변수에 저장
}
console.log('doctor', doctorList)
</script>

<template>
  <div>
    <p>병원선택입니다.</p>
    <p>선택한 시간 {{ reservedate }}에 상담 가능 수의사는 다음과 같습니다</p>
  </div>
  <div v-if="type == 1">
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
    <p>병원진료예약입니다.</p>
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
