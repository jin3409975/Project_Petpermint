<script setup>
import { ref } from 'vue'

const currentYear = ref(new Date().getFullYear())
</script>

<template>
  <v-card height="160px">
    <v-footer style="background-color: #668ba7 !important">
      <v-card flat tile width="100%" class="red lighten-1 text-center">
        <v-card-text
          class="white--text strong-text pt-2"
          style="background-color: #668ba7 !important"
        >
          <span class="white--text">{{ currentYear }} — </span><strong>PetPermint</strong>
          <br />
          <strong class="white--text strong-text">We love pets, you love pets.</strong><br />
          <strong class="white--text strong-text"
            >우리 아이들에게 여러분이 필요하고 여러분은 펫퍼민트가 필요합니다.</strong
          ><br />
          <div class="white--text small-text">사업자등록번호: 2024-02-19 | 대표: 팀명은3조</div>
          <div class="white--text small-text">
            대전 유성구 동서대로 98-39(삼성화재 유성캠퍼스) | 이메일: ssafy@gmail.com
          </div>
          <div class="white--text small-text">
            Copyright (c) Docfriends Corp. All rights reserved.
          </div>
        </v-card-text>
      </v-card>
    </v-footer>
  </v-card>
</template>

<style>
.v-card-text > * {
  color: white !important;
}

.strong-text {
  font-size: 15px;
}

.small-text {
  font-size: 12px;
}
</style>
