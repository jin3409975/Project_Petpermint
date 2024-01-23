<template>
    <div>
        회원가입 뷰
        <RouterLink to="/register/user">일반회원</RouterLink>
        
        <RouterLink to="/register/vet">수의사</RouterLink>
    </div>
    <RouterView />
</template>

<script setup>
// import { RouterLink, RouterView } from 'vue-router'
// import RegisterVetView from '@/views/account/RegisterVetView.vue'
// import RegisterUserView from '@/views/account/RegisterUserView.vue'
</script>

<style>

</style>