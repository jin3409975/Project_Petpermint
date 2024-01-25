<script setup>
import { ref } from 'vue'
// 반응형 데이터 icons 정의
const icons = ref(['mdi-facebook', 'mdi-twitter', 'mdi-linkedin', 'mdi-instagram'])

// 현재 연도를 계산하는 반응형 데이터
const currentYear = ref(new Date().getFullYear())
</script>

<template>
  <v-card height="250px">
    <v-footer>
      <v-card flat tile width="100%" class="red lighten-1 text-center">
        <v-card-text>
          <v-btn v-for="icon in icons" :key="icon" class="mx-8" icon>
            <v-icon size="20px">
              {{ icon }}
            </v-icon>
          </v-btn>
        </v-card-text>
        <v-card-text class="white--text strong-text pt-0">
          {{ currentYear }} — <strong>PetPermint</strong>
          <br />
          <strong class="strong-text">We love pets, you love pets.</strong><br />
          <strong class="strong-text"
            >우리 아이들에게 여러분이 필요하고 여러분은 펫퍼민트가 필요합니다.</strong
          ><br />
          <div class="small-text">사업자등록번호: 2024-02-19 | 대표: 팀명은3조</div>
          <div class="small-text">
            대전 유성구 동서대로 98-39(삼성화재 유성캠퍼스) | 이메일: ssafy@gmail.com
          </div>
          <div class="small-text">Copyright (c) Docfriends Corp. All rights reserved.</div>
        </v-card-text>
      </v-card>
    </v-footer>
  </v-card>
</template>

<style>
.strong-text {
  font-size: 15px;
}

.small-text {
  font-size: 12px;
}
</style>
