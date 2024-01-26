<script setup>
import { onMounted, ref } from 'vue'
import { useReserveStore } from '@/stores/reserve'
import { storeToRefs } from 'pinia'

const reserveStore = useReserveStore()
const { reservedate, starttime, endtime, doctorList, reservehospital } = storeToRefs(reserveStore)

const selectHospital = (doctor) => {
  reservehospital.value = doctor
}
</script>

<template>
  <div>
    <p>병원선택입니다.</p>
    <p>선택한 시간 {{ starttime }}-{{ endtime }}에 상담 가능 수의사는 다음과 같습니다</p>
  </div>
  <v-card class="mx-auto" max-width="300">
    <v-list-item
      v-for="(doctor, i) in doctorList"
      :key="i"
      :value="doctor"
      @click="selectHospital(doctor)"
    >
      <v-list-item-title v-text="doctor"></v-list-item-title>
    </v-list-item>
  </v-card>
</template>

<style scoped></style>
