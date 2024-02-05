<script setup>
import ReserveHospital from '@/components/reserve/ReserveHospital.vue'
import ReserveTime from '@/components/reserve/ReserveTime.vue'
import ReservePet from '@/components/reserve/ReservePet.vue'
import { useReserveStore } from '@/stores/reserve.js'
import { onMounted } from 'vue'
import { storeToRefs } from 'pinia'

const reserveStore = useReserveStore()
const { type } = storeToRefs(reserveStore)

onMounted(() => {
  // 페이지 마운티드 될때 예약 타입 1로 설정 , 초기상담
  type.value = 1
})
</script>

<template>
  <v-app>
    <v-container>
      <v-stepper :items="['상담 시간 선택', '상담 수의사 선택', '상담 자녀 선택']">
        <template v-slot:item.1>
          <v-card class="card" title="상담 시간 선택" flat>
            <div class="d-flex justify-center">
              <ReserveTime />
            </div>
            <v-card-actions>
              <v-btn class="custom-btn btn-2" @click="selectDate">시간 선택 완료</v-btn>
            </v-card-actions>
          </v-card>
        </template>

        <template v-slot:item.2>
          <v-card class="card" title="상담 수의사 선택" flat>
            <ReserveHospital />
          </v-card>
        </template>

        <template v-slot:item.3>
          <v-card class="card" title="상담 자녀 선택" flat>
            <ReservePet />
          </v-card>
        </template>
      </v-stepper>
    </v-container>
  </v-app>
</template>

<style>
.d-flex {
  display: flex;
}
.justify-center {
  justify-content: center;
}
.card {
  width: 100%;
  text-align: center;
}
</style>
