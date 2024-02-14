<script setup></script>

<template>
  <v-container>
    <v-row align="center" justify="start" no-gutters style="margin-top: 80px">
      <v-col cols="12" class="d-flex justify-center">
        <p class="main-text" style="color: #668ba7">What's on our PetPermint?</p>
      </v-col>
    </v-row>
    <!-- Cards row -->
    <v-row justify="center" style="margin-top: 35px">
      <!-- Card 1 -->

      <v-col cols="12" md="4" class="d-flex justify-center">
        <v-card class="pa-3 custom-card" variant="text" style="width: 75%">
          <img
            src="/assets/img/online1.png"
            width="100%"
            style="border-radius: 10px; margin-bottom: 24px; height: 45%; margin-top: 20px"
          />
          <v-card-title
            class="justify-center"
            style="width: 100%; padding-left: 0px; padding-right: 0px"
          >
            <p style="margin-bottom: 0px; font-size: 22px; font-weight: bold">
              초기 화상 상담
            </p></v-card-title
          >
          <v-card-text style="padding-left: 0px; padding-right: 0px"
            ><p style="word-break: keep-all; font-size: 17px; line-height: 1.5; margin-bottom: 0px">
              언제 어디서든 원하는 수의사와 간편하게 온라인 상담을 경험해 보세요.
            </p></v-card-text
          >
        </v-card>
      </v-col>

      <!-- Card 2 -->
      <v-col cols="12" md="4" class="d-flex justify-center">
        <v-card class="pa-3 custom-card" variant="text" style="width: 85%">
          <img
            src="/assets/img/emergency_book.png"
            width="100%"
            style="border-radius: 10px; margin-bottom: 20px; margin-top: 20px"
          />
          <v-card-title
            class="justify-center"
            style="width: 100%; padding-left: 0px; padding-right: 0px"
          >
            <p style="margin-bottom: 0px; font-size: 22px; font-weight: bold">
              응급 예약
            </p></v-card-title
          >
          <v-card-text style="padding-left: 0px; padding-right: 0px"
            ><p style="word-break: keep-all; font-size: 17px; line-height: 1.5; margin-bottom: 0px">
              우리 집에서 가장 가까운 병원으로 원클릭 응급 예약을 할 수 있습니다.
            </p>
          </v-card-text>
        </v-card>
      </v-col>

      <!-- Card 3 -->
      <v-col cols="12" md="4" class="d-flex justify-center">
        <v-card class="pa-3 custom-card" variant="text" style="width: 85%">
          <img
            src="/assets/img/live.png"
            width="100%"
            style="border-radius: 10px; margin-bottom: 20px; margin-top: 20px"
          />
          <v-card-title
            class="justify-center"
            style="width: 100%; padding-left: 0px; padding-right: 0px"
          >
            <p style="margin-bottom: 0px; font-size: 22px; font-weight: bold">다양한 라이프케어</p>
          </v-card-title>
          <v-card-text style="padding-left: 0px; padding-right: 0px"
            ><p style="word-break: keep-all; font-size: 17px; line-height: 1.5; margin-bottom: 0px">
              라이브방송, 커뮤니티, 반려동물 동반 시설 찾기 서비스로 반려동물의 위한 유익한 정보를
              얻어 보세요.
            </p>
          </v-card-text>
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
