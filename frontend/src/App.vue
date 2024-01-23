<script setup>
import { RouterLink, RouterView } from 'vue-router'
import HomeView from './views/HomeView.vue';
import RegisterView from './views/account/RegisterView.vue';
import LoginView from './views/account/LoginView.vue';

</script>

<template>
    <v-app>
        <Navbar/>
        <v-main>
            <Home/>
        </v-main>
        <Footer/>
    </v-app>
</template>

<script>
import Navbar from '@/components/Navbar.vue'
import Home from '@/components/Home.vue'
import Footer from '@/components/Footer.vue'

export default {
    components: {
        Navbar,
        Home,
        Footer
    }
}
</script>

<style>
</style>
