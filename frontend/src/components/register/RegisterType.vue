<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

const navigateToRegisterUser = () => {
  router.push({ name: 'register-user' })
}
const navigateToRegisterVet = () => {
  router.push({ name: 'register-vet' })
}
</script>

<template>
  <div class="page-container">
    <h1 class="header">회원가입</h1>
    <div class="card-container">
      <v-container>
        <v-card @click="navigateToRegisterUser" class="register-card user-card" elevation="5">
          <img src="@/assets/img/animal-icon-1.png" alt="user-icon" />
          <v-card-title>보호자</v-card-title>
          <v-card class="inner-card" color="#BBDEFB">
            <v-card-text class="register-card-text">
              펫퍼민트만의 특별한 혜택을 <br />경험해보세요!<br />
              <br />
              <br />
              1. 원하는 수의사와 함께하는 화상 상담
              <br />
              <br />
              2. 간편한 병원 진료 예약
              <br />
              <br />
              3. 펫퍼민트만의 라이프 케어
            </v-card-text>
          </v-card>
        </v-card>
      </v-container>

      <v-container>
        <v-card @click="navigateToRegisterVet" class="register-card vet-card" elevation="5">
          <img src="@/assets/img/hospital-icon-1.png" alt="hospital-icon" />
          <v-card-title>수의사</v-card-title>
          <v-card class="inner-card" color="#BBDEFB">
            <v-card-text class="register-card-text"> 언 </v-card-text>
          </v-card>
        </v-card>
      </v-container>
    </div>
  </div>
</template>

<style scoped>
.page-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.header {
  text-align: center;
  font-weight: bold;
  font-size: 30px; /* 원하는 크기로 조절 */
  margin-top: 50px;
  margin-bottom: 15px;
}

.card-container {
  display: flex;
  justify-content: space-around;
  margin-top: 20px;
}

.register-card {
  height: 400px;
  width: 300px;
  text-align: center;
}

.user-card {
  margin-right: 50%; /* 보호자 카드 오른쪽 부분과 수의사 카드 왼쪽 부분 간격 조절 */
}

.vet-card {
  margin-left: 50px; /* 보호자 카드 오른쪽 부분과 수의사 카드 왼쪽 부분 간격 조절 */
}

.inner-card {
  opacity: 0.8;
}

.register-card-text {
  margin-top: 0px;
}

img {
  width: 100px;
  height: 100px;
  margin-top: 20px;
}
</style>
