<script setup>
import { createVuetify } from 'vuetify'
import { storeToRefs } from 'pinia'
import { useReserveStore } from '@/stores/reserve'
import { useRouter } from 'vue-router'

const reserveStore = useReserveStore()
const { petList, reservepet, reservehospital, reservepetindex, type } = storeToRefs(reserveStore)
const vuetify = createVuetify({
  theme: {
    defaultTheme: 'light'
    //
  }
})

const selectPet = (pet, id) => {
  console.log(pet, id)
  reservepetindex.value = id
  reservepet.value = pet
}
const navigateToReserveComplete = () => {
  if (type == 1) {
    reserveStore.consultCreate()
  } else {
    reserveStore.hospitalCreate()
  }
}
</script>

<template>
  <div>
    <p>자녀 선택 페이지입니다</p>
  </div>
  <div>
    <v-container fluid>
      <div v-for="(pet, index) in petList" :key="pet.id">
        <v-checkbox :label="pet" @click="selectPet(pet, index)"></v-checkbox>
      </div>
    </v-container>
  </div>
  <p>{{ reservehospital }} 수의사님과 진료를 볼 선택한 자녀는 {{ reservepet }} 입니다 !</p>
  <button @click="navigateToReserveComplete">완료</button>
</template>

<style scoped></style>
