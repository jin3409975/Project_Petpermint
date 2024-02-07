<script setup>
import { onMounted, ref } from 'vue'
import { useReserveStore } from '@/stores/reserve'
import { storeToRefs } from 'pinia'

const reserveStore = useReserveStore()
const {
  reservedate,
  doctorList,
  reservehospitalno,
  type,
  reservelicense,
  hospitalList,
  petList,
  hospitalname
} = storeToRefs(reserveStore)

const selectDoctor = (doctor) => {
  reservelicense.value = doctor.licenseNumber
}
const selectHospital = (hospital) => {
  console.log('hos', hospital)
  reservehospitalno.value = hospital.hospitalNo
  hospitalname.value = hospital.name
  // 예약에 필요한 병원 번호를 store변수에 저장
}
onMounted(() => {
  doctorList.value = null
  hospitalList.value = null
  //petList.value = null
})
</script>

<template>
  <div v-if="type == 1">
    <p>{{ reservedate }}에 상담 가능 수의사는 다음과 같습니다</p>
    <v-card class="mx-auto hospital-list-card" max-width="300">
      <v-list three-line class="scrollable-list">
        <v-list-item
          v-for="(doctor, i) in doctorList"
          :key="i"
          :value="doctor"
          @click="selectDoctor(doctor)"
        >
          <v-list-item-content>
            <v-list-item-title v-text="doctor.userName"></v-list-item-title>
            <v-list-item-subtitle v-text="doctor.hospitalName"></v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-card>
  </div>
  <div v-else-if="type == 2">
    <p>{{ reservedate }}에 진료 가능 병원은 다음과 같습니다</p>
    <v-card class="mx-auto hospital-list-card" max-width="300">
      <v-list three-line class="scrollable-list">
        <v-list-item
          v-for="(hospital, i) in hospitalList"
          :key="i"
          :value="hospital"
          @click="selectHospital(hospital)"
        >
          <v-list-item-content>
            <v-list-item-title v-text="hospital.name"></v-list-item-title>
            <v-list-item-subtitle v-text="hospital.roadNumberAddress"></v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-card>
  </div>
</template>

<style scoped>
.hospital-list-card .scrollable-list {
  height: 250px; /* 5개 항목의 높이에 맞춰 조정하세요 */
  overflow-y: auto;
}

/* 리스트 항목의 높이를 조정하세요. 예시로 80px를 사용했습니다. */
.v-list-item {
  height: 50px;
}

/* 반응형 디자인을 위해 뷰포트에 따라 높이 조정 */
@media (max-width: 600px) {
  .hospital-list-card .scrollable-list {
    height: 100vh; /* 모바일 뷰포트에 맞게 높이를 전체 화면으로 조정할 수 있습니다 */
  }
}
</style>
