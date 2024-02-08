<script setup>
import { ref, onBeforeMount, onMounted } from 'vue'
import axios from 'axios'

onMounted(() => {})
</script>

<template>
  <v-container
    fluid
    class="d-flex justify-center align-center"
    style="max-width: 50%; margin-top: 100px"
  >
    <v-row no-gutters>
      <v-col cols="8">
        <v-sheet class="pa-2 ma-2">
          <h2>전문가 라이브 방송</h2>
        </v-sheet>
      </v-col>
      <v-col>
        <v-sheet class="pa-2 ma-2">
          <v-card class="black-square" color="black">
            <div>생방송 중</div>
          </v-card>
        </v-sheet>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped></style>
