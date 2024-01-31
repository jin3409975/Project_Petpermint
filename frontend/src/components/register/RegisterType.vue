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
  <div>
    <h1 class="header">회원가입</h1>
    <div class="card-container">
      <v-container>
        <v-card @click="navigateToRegisterUser" class="register-card">보호자</v-card>
      </v-container>

      <v-container>
        <v-card @click="navigateToRegisterVet" class="register-card">수의사</v-card>
      </v-container>
    </div>
  </div>
</template>

<style scoped>
.header {
  text-align: center;
  font-weight: bold;
  font-size: 30px; /* 원하는 크기로 조절 */
  margin-top: 50px;
  margin-bottom: 50px;
}

.card-container {
  display: flex;
  justify-content: space-around;
  margin-top: 20px;
}

.register-card {
  height: 300px;
  width: 400px;
}
</style>
