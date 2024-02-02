<script setup>
import { ref } from 'vue'
// import { useRouter } from 'vue-router'

// const router = useRouter()

const userName = ref(null)
const email = ref('')
const changPassword = ref(null)
const confirmPassword = ref(null)
const phoneNumber = ref(null)
const address = ref('')
const changeAddress = ref(null)

const color = ref('indigo')
</script>

<template>
  <v-container class="profile-container">
    <v-row justify="end" class="mr-10">
      <v-col cols="12" md="9">
        <v-card>
          <v-card-title>{{ userName }}님의 정보</v-card-title>
          <v-card-text>
            고객님께서 입력하신 회원정보입니다. <br />
            원활한 예약을 위해 핸드폰 번호와 자택 주소를 반드시 확인해주세요.
          </v-card-text>

          <v-card-text>
            <!-- <v-form @submit.prevent="updateUserInfo"> -->
            <v-text-field v-model="email" label="이메일" readonly></v-text-field>
            <v-text-field
              v-model="changPassword"
              label="비밀번호 수정"
              type="changePassword"
            ></v-text-field>
            <v-text-field
              v-model="confirmPassword"
              label="비밀번호 확인"
              type="password"
            ></v-text-field>

            <v-text-field v-model="phoneNumber" label="핸드폰 번호"></v-text-field>
            <v-text-field v-model="changeAddress" label="자택 주소"></v-text-field>
            <v-text-field v-model="address" label="자택 주소" readonly></v-text-field>
            <v-btn type="submit" color="primary">저장</v-btn>
            <!-- </v-form> -->
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>
.profile-container {
  max-width: 30000px;
}
</style>
