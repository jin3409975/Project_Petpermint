<script setup>
import { useReserveStore } from '@/stores/reserve'
import { storeToRefs } from 'pinia'
import { ref } from 'vue'
const reserveStore = useReserveStore()
const { notetext } = storeToRefs(reserveStore)
const note = ref('')
const onChange = () => {
  notetext = note.value
  console.log(notetext)
}
</script>

<template>
  <div>
    <v-container>
      <v-textarea v-model="note" variant="outlined" @change="onChange"> </v-textarea>
      <v-btn @click="console.log(note)">전송하기</v-btn>
    </v-container>
  </div>
</template>

<style scoped></style>
