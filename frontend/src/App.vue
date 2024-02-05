<script setup>
import Footer from '@/components/main/Footer.vue'
import { computed } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const hideNavbar = computed(() => route.meta.hideNavbar)
const hideFooter = computed(() => route.meta.hideFooter)
</script>

<template>
  <body>
    <v-app>
      <RouterView />
      <Footer v-if="!hideFooter" />
    </v-app>
  </body>
</template>

<style></style>
