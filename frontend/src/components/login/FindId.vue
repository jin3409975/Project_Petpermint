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
  <div style="margin-top: 120px; margin-bottom: 80px">
    <v-card
      class="mx-auto pa-12 pb-8"
      variant="outlined"
      elevation="0"
      max-width="448"
      rounded="lg"
    >
      <div class="text-h5 text-center font-weight-bold">이메일 찾기</div>
      <div
        class="text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between mt-6"
        style="font-weight: bold"
      >
        이름
      </div>
      <v-text-field
        v-model="username"
        density="compact"
        placeholder="이름을 입력해주세요."
        prepend-inner-icon="mdi-account-outline"
        variant="outlined"
        class="mb-5"
      ></v-text-field>

      <div
        class="text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between"
        style="font-weight: bold"
      >
        핸드폰 번호
      </div>
      <v-text-field
        v-model="phone"
        density="compact"
        placeholder="핸드폰 번호를 입력해주세요."
        prepend-inner-icon="mdi-cellphone"
        variant="outlined"
        class="mb-5"
      ></v-text-field>
      <v-btn block class="mb-8 mt-10" color="indigo" size="large" variant="tonal" @click="findmyId">
        이메일 찾기
      </v-btn>
    </v-card>
  </div>
</template>

<style scoped></style>
