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
  // 페이지 마운티드 될때 예약 타입 2로 설정 , 병원진료예약
  type.value = 2
})
</script>

<template>
  <div class="reserve-container">
    <div class="reserve-item">
      <ReserveTime />
    </div>
    <div class="reserve-item">
      <ReserveHospital />
    </div>
    <div class="reserve-item">
      <ReservePet />
    </div>
  </div>
</template>

<style>
.reserve-container {
  display: flex;
  justify-content: center; /* Center the items horizontally */
  align-items: flex-start; /* Align items to the top */
  gap: 20px; /* Spacing between the items */
  padding: 20px; /* Padding around the container */
}

.reserve-item {
  flex: 1; /* Each item will take equal width */
  display: flex;
  flex-direction: column; /* Stack the child elements vertically */
  align-items: center; /* Center the child elements horizontally */
  background-color: #ffffff; /* Card background color */
  padding: 20px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); /* Subtle shadow for depth */
  border-radius: 12px; /* Rounded corners for the card */
  margin: 10px; /* Space between cards */
  max-width: calc(33.333% - 20px); /* Max width for each item with margin */
  box-sizing: border-box; /* Include padding and border in the element's width and height */
}

/* For the text and other elements inside your components, you'll need to style them accordingly.
For instance, you can add classes and style them within each component's <style> block. */

/* Responsive design for smaller screens */
@media (max-width: 1024px) {
  .reserve-container {
    flex-direction: row; /* Keep items in a row but with full width on smaller screens */
  }
  .reserve-item {
    max-width: 100%;
  }
}

@media (max-width: 768px) {
  .reserve-container {
    flex-direction: column; /* Stack items vertically on very small screens */
  }
  .reserve-item {
    width: 100%; /* Full width cards on small screens */
    max-width: none;
    margin: 10px 0; /* Adjust margin for vertical stacking */
  }
}
</style>
