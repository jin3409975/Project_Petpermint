<script setup></script>

<template>
  <v-container>
    <v-row align="center" justify="start" no-gutters style="margin-top: 80px">
      <v-col cols="12" class="d-flex justify-center">
        <p class="main-text" style="color: #668ba7">What's on our PetPermint?</p>
      </v-col>
    </v-row>
    <!-- Cards row -->
    <v-row justify="center" style="margin-top: 80px">
      <!-- Card 1 -->
      <v-col cols="12" md="4" class="d-flex justify-center">
        <v-card class="pa-3 custom-card" variant="text">
          <v-card-title class="justify-center text-center" style="font-weight: 600; width: 100%">
            초기 화상 상담</v-card-title
          >
          <v-card-text class="text-center"
            >언제 어디서든 수의사와 간편하게 온라인 상담을 경험해 보세요.</v-card-text
          >
          <v-card-actions class="justify-center">
            <v-btn text color="primary">Action 1</v-btn>
          </v-card-actions>
        </v-card>
      </v-col>

      <!-- Card 2 -->
      <v-col cols="12" md="4" class="d-flex justify-center">
        <v-card class="pa-3 custom-card" variant="text">
          <v-card-title class="justify-center text-center" style="font-weight: 600; width: 100%">
            응급 예약</v-card-title
          >
          <v-card-text class="text-center"
            >This is some text inside card 2. Explore more about our features.</v-card-text
          >
          <v-card-actions class="justify-center">
            <v-btn text color="primary">Action 2</v-btn>
          </v-card-actions>
        </v-card>
      </v-col>

      <!-- Card 3 -->
      <v-col cols="12" md="4" class="d-flex justify-center">
        <v-card class="pa-3 custom-card" variant="text" style="max-width: 100%">
          <v-card-title class="justify-center text-center" style="font-weight: 600; width: 100%">
            다양한 라이프케어
          </v-card-title>
          <v-card-text class="text-center"
            >Card 3 contains additional information for our users.</v-card-text
          >
          <v-card-actions class="justify-center">
            <v-btn text color="primary">Action 3</v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>
.main-text {
  font-weight: bold;
  font-size: 45px;
}

.custom-card {
  height: 400px;
}
/* @media (max-width: 599px) {
  .custom-card {
    height: 250px;
  }
}
@media (min-width: 600px) and (max-width: 959px) {
  .custom-card {
    height: 275px; 
  }
} */
</style>
