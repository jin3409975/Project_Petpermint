<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
    <div>
        <!-- 회원가입 뷰
        <RouterLink to="/register/user">일반회원</RouterLink>
        
        <RouterLink to="/register/vet">수의사</RouterLink> -->

        <RouterLink to="/register/user">일반회원</RouterLink>
        <p>수의사 회원가입</p>
    </div>
    <RouterView />
</template>

<style></style>
