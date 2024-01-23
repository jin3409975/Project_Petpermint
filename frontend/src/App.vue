<script setup>
import { RouterLink, RouterView } from 'vue-router'
import HomeView from './views/HomeView.vue';
import RegisterView from './views/account/RegisterView.vue';
import LoginView from './views/account/LoginView.vue';

</script>

<template>
  <header>
      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/login">로그인</RouterLink>
        <RouterLink to="/register">회원가입</RouterLink>
      </nav>
  </header>
  <RouterView />
</template>

<style scoped>
</style>
