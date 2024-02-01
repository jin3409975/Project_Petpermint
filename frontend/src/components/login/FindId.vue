<script setup>
import { ref } from 'vue'
import { useAccountStore } from '@/stores/account'
const store = useAccountStore()
const username = ref(null)
const phone = ref(null)
const findmyId = function () {
  store.findId(username.value, phone.value)
}
</script>

<template>
  <div>
    <v-text-field v-model="username" placeholder="enter your email"></v-text-field>
    <v-text-field v-model="phone" placeholder="enter your phone number"></v-text-field>
    <v-btn block class="mb-8 mt-10" color="blue" size="large" variant="tonal" @click="findmyId">
      아이디 찾기
    </v-btn>
  </div>
</template>

<style scoped></style>
