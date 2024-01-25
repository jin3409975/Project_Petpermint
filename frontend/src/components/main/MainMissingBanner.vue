<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
const { VITE_MISSING_PET_API_URI, VITE_MISSING_PET_API_KEY } = import.meta.env
const missingPetData = ref({})

const fetchMissingPet = async () => {
  const params = {
    serviceKey: VITE_MISSING_PET_API_KEY,
    pageNo: 1,
    numOfRows: 1,
    _type: 'json',
    state: 'protect'
  }

  const response = await axios.get(VITE_MISSING_PET_API_URI, { params: params })
  // console.log(VITE_MISSING_PET_API_KEY)
  // console.log(response.data.response.body.items.item[0])
  return response.data.response.body.items.item
}

onMounted(() => {
  missingPetData.value = fetchMissingPet().pop()
})
</script>

<template>
  <div>{{ missingPetData.happenPlace }}</div>
</template>

<style scoped></style>
