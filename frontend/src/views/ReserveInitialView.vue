<script setup>
import ReserveHospital from '@/components/reserve/ReserveHospital.vue'
import ReserveTime from '@/components/reserve/ReserveTime.vue'
import ReservePet from '@/components/reserve/ReservePet.vue'
import { useReserveStore } from '@/stores/reserve.js'
import { ref, onMounted } from 'vue'
import { storeToRefs } from 'pinia'
import { useRouter } from 'vue-router'
const router = useRouter()
const dialog = ref(false)
const reserveStore = useReserveStore()
const { type, notetext } = storeToRefs(reserveStore)
const step = ref(1)
const selectDate = () => {
  step.value = 2
  if (reserveStore.type == 1) {
    reserveStore.getdoctorList()
  } else {
    reserveStore.gethospitalList()
  }
  reserveStore.getpetList()
}
const navigateToReserveComplete = () => {
  notetext.value = note.value
  dialog.value = true
  console.log(notetext.value, note.value)
  if (type == 1) {
    reserveStore.consultCreate()
  } else {
    reserveStore.hospitalCreate()
  }
}
const navigateToMain = () => {
  dialog.value = false
  router.push({ name: 'main-home' })
}
const note = ref('')
onMounted(() => {
  // 페이지 마운티드 될때 예약 타입 2로 설정 , 병원예약
  type.value = 1
})
</script>

<template>
  <v-app>
    <v-container>
      <v-stepper
        :items="['예약 시간 선택', '예약 병원 선택', '예약 자녀 선택', '특이 사항 입력']"
        :hide-actions="true"
        alt-labels
        v-model="step"
      >
        <template v-slot:item.1>
          <v-card v-if="step == 1" class="card" title="예약 시간 선택" flat>
            <div class="d-flex justify-center">
              <ReserveTime />
            </div>
            <v-card-actions class="d-flex justify-center">
              <v-btn class="custom-btn btn-2" @click="selectDate">시간 선택 완료</v-btn>
            </v-card-actions>
          </v-card>
        </template>

        <template v-slot:item.2>
          <v-card v-if="step == 2" class="card" title="예약 병원 선택" flat>
            <ReserveHospital />
          </v-card>
          <v-card-actions class="d-flex justify-center">
            <v-btn class="custom-btn btn-2" @click="step = 1">이전 단계</v-btn>
            <v-spacer></v-spacer>
            <v-btn class="custom-btn btn-2" @click="step = 3">다음 단계</v-btn>
          </v-card-actions>
        </template>

        <template v-slot:item.3>
          <v-card v-if="step == 3" class="card" title="예약 자녀 선택" flat>
            <ReservePet />
          </v-card>
          <v-card-actions class="d-flex justify-center">
            <v-btn class="custom-btn btn-2" @click="step = 2">이전 단계</v-btn>
            <v-spacer></v-spacer>
            <v-btn class="custom-btn btn-2" @click="step = 4">다음 단계</v-btn>
          </v-card-actions>
        </template>

        <template v-slot:item.4>
          <v-card v-if="step == 4" class="card" title="특이 사항 입력" flat>
            <v-container>
              <v-textarea v-model="note" variant="outlined" @change="onChange"> </v-textarea>
            </v-container>
          </v-card>
          <v-card-actions class="d-flex justify-center">
            <v-btn class="custom-btn btn-2" @click="step = 3">이전 단계</v-btn>
            <v-spacer></v-spacer>
            <v-btn class="custom-btn btn-2" @click="navigateToReserveComplete">예약 완료</v-btn>
          </v-card-actions>
          <v-dialog v-model="dialog" width="auto" persistent>
            <v-card>
              <v-card-text>
                병원 예약이 완료되었습니다. <br />
                마이 페이지에서 예약 내역을 확인할 수 있습니다.
              </v-card-text>
              <v-card-actions>
                <v-btn color="primary" block @click="navigateToMain">Close</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
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
