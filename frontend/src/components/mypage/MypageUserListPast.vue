<script setup>
import { createVuetify } from 'vuetify'
import { myPageStore } from '@/stores/mypage.js'
import { storeToRefs } from 'pinia'
const mypagestore = myPageStore()
const { userpastList } = storeToRefs(mypagestore)
const vuetify = createVuetify({
  theme: {
    defaultTheme: 'light'
  }
})
</script>

<template>
  <div><p>일반 유저의 과거예약내역입니다</p></div>
  <v-card class="mx-auto" max-width="500">
    <v-list>
      <v-list-item v-for="(past, i) in userpastList" :key="i">
        <v-list-item-content>
          <v-list-item-title v-text="past.title"></v-list-item-title>
          <v-list-item-subtitle v-text="past.text"></v-list-item-subtitle>
          <br />
          <hr />
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </v-card>
</template>

<style scoped></style>
