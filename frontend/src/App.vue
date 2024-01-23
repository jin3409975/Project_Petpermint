<script setup>
import { RouterLink, RouterView } from 'vue-router'
import RegisterView from './views/account/RegisterView.vue'
import LoginView from './views/account/LoginView.vue'
import Navbar from '@/components/Navbar.vue'
import MainView from '@/views/MainView.vue'
import Footer from '@/components/Footer.vue'
</script>

<template>
    <v-app>
        <Navbar />
        <v-main>
            <MainView />
        </v-main>
        <Footer />
    </v-app>
</template>

<style></style>
