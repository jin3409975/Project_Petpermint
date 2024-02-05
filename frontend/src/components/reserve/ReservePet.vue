<script setup>
import { createVuetify } from 'vuetify'
import { storeToRefs } from 'pinia'
import { useReserveStore } from '@/stores/reserve'
import { useRouter } from 'vue-router'
import { onMounted } from 'vue'

const reserveStore = useReserveStore()
const { petList, reservepet, reservehospital, reservepetindex, type, petname } =
  storeToRefs(reserveStore)
const vuetify = createVuetify({
  theme: {
    defaultTheme: 'light'
    //
  }
})

const selectPet = () => {
  console.log(reservepetindex.value)
  reservepet.value = petList.value[reservepetindex.value].animalId
  petname.value = petList.value[reservepetindex.value].name
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
    <v-container fluid class="d-flex justify-center">
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
  <!-- <button class="button" @click="navigateToReserveComplete">완료</button> -->
</template>

<style>
.v-container--fluid {
  max-width: 30%;
}
.v-selection-control {
  align-items: center;
  contain: layout;
  display: flex;
  flex: 1 0;
  grid-area: control;
  position: relative;
  user-select: none;
  justify-content: center;
}
.card {
  width: 80%;
  text-align: center;
  justify-content: center;
}
.d-flex {
  display: flex;
}
.justify-center {
  justify-content: center;
}
/* Button base styles */
.button {
  padding: 10px 20px; /* Adjust padding as needed */
  background-color: #007bff; /* Use your primary color here */
  color: white; /* Text color */
  border: none; /* No border */
  outline: none; /* Remove outline */
  border-radius: 20px; /* Rounded corners */
  cursor: pointer; /* Mouse pointer on hover */
  font-size: 1rem; /* Adjust font size as needed */
  font-weight: bold; /* Bold font */
  text-transform: uppercase; /* Uppercase text */
  letter-spacing: 0.05em; /* Spacing between letters */
  transition:
    background-color 0.3s,
    box-shadow 0.3s; /* Transition for smooth effect on hover */
}

/* Button hover styles */
.button:hover {
  background-color: #0056b3; /* Darken the color on hover */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Add shadow on hover */
}

/* Button focus styles */
.button:focus {
  box-shadow: 0 0 0 2px rgba(0, 123, 255, 0.5); /* Add focus ring */
}

/* Button active styles */
.button:active {
  background-color: #004b9b; /* Even darker color on click */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2); /* Smaller shadow on click */
}
</style>
