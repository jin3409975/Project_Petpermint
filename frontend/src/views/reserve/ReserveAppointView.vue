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
  type.value = 2
})
</script>

<template>
  <v-app class="my-custom-reserve">
    <v-container style="width: 800px">
      <v-stepper
        :items="['시간 선택', '병원 선택', '반려동물 선택']"
        :hide-actions="true"
        v-model="step"
      >
        <template v-slot:item.1>
          <v-card
            v-if="step == 1"
            class="card"
            flat
            title="예약 시간 선택"
            subtitle="예약을 원하시는 날짜와 시간을 선택하고 다음 버튼을 눌러주세요."
          >
            <div class="d-flex justify-center">
              <ReserveTime />
            </div>
            <v-card-actions
              class="d-flex justify-center"
              style="margin-top: 10px; width: 415px; margin-left: 170px"
            >
              <!-- 예약을 원하시는 날짜와 시간을 선택하고 다음 버튼을 눌러주세요. -->
              <v-btn
                style="border: 2px solid"
                variant="outlined"
                block
                color="#6096b4"
                size="large"
                @click="selectDate"
                >선택 완료</v-btn
              >
            </v-card-actions>
          </v-card>
        </template>

        <template v-slot:item.2>
          <v-card v-if="step == 2" class="card" title="예약 병원 선택" flat>
            <ReserveHospital />
          </v-card>
          <v-card-actions class="d-flex justify-center" style="margin-top: 7px">
            <v-btn
              variant="outlined"
              color="#6096b4"
              @click="step = 1"
              style="margin-right: 290px; border: 1.7px solid"
              >이전 단계</v-btn
            >
            <v-btn style="border: 1.7px solid" variant="outlined" color="#6096b4" @click="step = 3"
              >다음 단계</v-btn
            >
          </v-card-actions>
        </template>

        <template v-slot:item.3>
          <v-card
            v-if="step == 3"
            class="card"
            title="반려동물 선택 및 특이사항"
            subtitle="진료 받을 반려동물을 선택하고 수의사님이 사전에 알면 좋을 특이 사항을 입력해 주세요."
            flat
            style="margin-top: 20px"
          >
            <ReservePet />
          </v-card>
          <v-container style="width: 600px; margin-top: 60px">
            <v-textarea
              label="특이 사항 및 진료 목적"
              v-model="note"
              auto-grow
              variant="outlined"
              rows="6"
              shaped
              @change="onChange"
            >
            </v-textarea>
          </v-container>
          <v-card-actions class="d-flex justify-center" style="margin-top: 20px">
            <v-btn
              variant="outlined"
              color="#6096b4"
              @click="step = 2"
              style="margin-right: 375px; border: 1.7px solid"
              >이전 단계</v-btn
            >
            <v-btn
              min-width="140px"
              style="border: 1.7px solid"
              variant="flat"
              size="large"
              prepend-icon="mdi-check-bold"
              color="green-darken-1"
              @click="navigateToReserveComplete"
              >예약 완료</v-btn
            >
          </v-card-actions>
          <v-dialog v-model="dialog" width="auto" persistent>
            <v-card>
              <v-card-text style="height: 110px">
                병원 예약이 완료되었습니다. <br />
                마이 페이지에서 예약 내역을 확인할 수 있습니다.
              </v-card-text>
              <v-card-actions
                style="position: absolute; top: 58px; right: 150px; padding-bottom: 0px"
              >
                <v-btn color="primary" block @click="navigateToMain">Close</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </template>
      </v-stepper>
    </v-container>
  </v-app>
</template>

<style scoped>
:deep(.v-card-title) {
  margin-bottom: 0px;
}
:deep(.v-card-subtitle) {
  padding-bottom: 30px;
}
:deep(.v-card-item) {
  padding-top: 0px;
}
:deep(.v-application__wrap) {
  min-height: 60vh !important;
}

:deep(.v-stepper .v-stepper-item--selected .v-stepper-item__avatar.v-avatar),
:deep(.v-stepper .v-stepper-item--complete .v-stepper-item__avatar.v-avatar) {
  background: #6588b2 !important;
}
:deep(.v-stepper-item__avatar.v-avatar) {
  background: #abaaaa;
  font-size: 0.9rem;
}
:deep(.v-stepper-item) {
  padding: 0.3rem;
}
:deep(.v-avatar) {
  width: 28px !important;
  height: 28px !important;
}
:deep(.v-stepper-header .v-divider) {
  margin: -2px 0px 0;
}
:deep(.v-container) {
  padding: 0;
}
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
.custom-btn {
  width: 100px;
  height: 40px;
  color: #fff;
  border-radius: 5px;
  padding: 10px 25px;
  font-family: 'Lato', sans-serif;
  font-weight: 500;
  background: transparent;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  display: inline-block;
  box-shadow:
    inset 2px 2px 2px 0px rgba(255, 255, 255, 0.5),
    7px 7px 20px 0px rgba(0, 0, 0, 0.1),
    4px 4px 5px 0px rgba(0, 0, 0, 0.1);
  outline: none;
}
.custom-btn.btn-2 {
  color: #fff;
  background: rgb(102, 139, 167) !important;
}
.btn-2 {
  background: rgb(96, 9, 240);
  background: linear-gradient(0deg, rgba(96, 9, 240, 1) 0%, rgba(129, 5, 240, 1) 100%);
  border: none;
}
.btn-2:before {
  height: 0%;
  width: 2px;
}
.btn-2:hover {
  box-shadow:
    4px 4px 6px 0 rgba(255, 255, 255, 0.5),
    -4px -4px 6px 0 rgba(116, 125, 136, 0.5),
    inset -4px -4px 6px 0 rgba(255, 255, 255, 0.2),
    inset 4px 4px 6px 0 rgba(0, 0, 0, 0.4);
}
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
