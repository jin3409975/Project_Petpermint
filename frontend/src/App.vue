<script setup>
import Navbar from '@/components/main/Navbar.vue'
import Footer from '@/components/main/Footer.vue'
import { computed } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const hideNavbar = computed(() => route.meta.hideNavbar)
const hideFooter = computed(() => route.meta.hideFooter)
</script>

<template>
  <v-app>
    <Navbar v-if="!hideNavbar" />
    <RouterView />
    <Footer v-if="!hideFooter" />
  </v-app>
</template>

<style></style>
