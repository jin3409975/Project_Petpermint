<script setup>
import MainFirst from '@/components/main/MainFirst.vue'
import MainTopBanner from '@/components/main/MainTopBanner.vue'
import MainBottomBanner from '@/components/main/MainBottomBanner.vue'
</script>

<template>
  <div>
    <!-- 비디오 메인 -->
    <MainFirst />
    <!-- 장점 -->
    <MainTopBanner style="height: 100vh" />
    <!-- 문구 -->
    <v-row class="blue-bg text-center">
      <v-col class="text-botx" cols="12">
        <p class="banner-text">"반려동물과 함께하는 행복하고 건강한 동반,</p>
        <p class="banner-text">펫퍼민트가 함께 하겠습니다."</p>
      </v-col>
    </v-row>
    <MainBottomBanner style="height: 100vh" />
  </div>
</template>

<style scoped>
.blue-bg {
  background-color: #668ba7;
  color: rgb(255, 255, 255);
  padding: 20px 0;
  height: 350px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 60px;
}

.banner-text {
  margin: 0;
  padding: 0 15px;
  text-align: center;
  font-size: 43px;
  font-weight: bold;
}

.text-botx {
  padding-left: 200px;
  padding-right: 200px;
}
</style>
