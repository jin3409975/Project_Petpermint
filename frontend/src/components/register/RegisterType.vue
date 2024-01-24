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
    <div class="d-flex justify-center">
        <div class="half-width d-flex justify-center align-center">
            <v-btn text @click="navigateToRegisterUser">일반회원</v-btn>
        </div>
        <div class="half-width d-flex justify-center align-center">
            <v-btn text @click="navigateToRegisterVet">수의사</v-btn>
        </div>
    </div>
</template>

<style>
.d-flex {
    display: flex;
    height: 100vh; /* 전체 뷰포트 높이 */
}

.justify-center {
    justify-content: center; /* 가로 중앙 정렬 */
}

.align-center {
    align-items: center; /* 세로 중앙 정렬 */
}

.half-width {
    width: 50%; /* 화면의 절반 크기 */
}
</style>