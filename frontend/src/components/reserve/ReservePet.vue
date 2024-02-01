<script setup>
import { createVuetify } from 'vuetify'
import { storeToRefs } from 'pinia'
import { useReserveStore } from '@/stores/reserve'
import { useRouter } from 'vue-router'
import { onMounted } from 'vue'

const reserveStore = useReserveStore()
const { petList, reservepet, reservehospital, reservepetindex, type } = storeToRefs(reserveStore)
const vuetify = createVuetify({
  theme: {
    defaultTheme: 'light'
    //
  }
})

const selectPet = () => {
  console.log(reservepetindex.value)
  reservepet.value = petList.value[reservepetindex.value].animalId
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
      <v-radio-group v-model="reservepetindex" @change="selectPet()">
        <v-radio
          v-for="(pet, index) in petList"
          :key="pet.id"
          :label="pet.name"
          :value="index"
        ></v-radio>
      </v-radio-group>
    </v-container>
  </div>
  <p>
    {{ reservehospital }} 수의사님과 진료를 볼 선택한 자녀는 {{ petList[reservepetindex] }} 입니다 !
  </p>
  <button @click="navigateToReserveComplete">완료</button>
</template>

<style scoped></style>
