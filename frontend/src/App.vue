<script setup>
import NavbarMain from '@/components/main/NavbarMain.vue'
import Footer from '@/components/main/Footer.vue'
import { computed } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const hideNavbarMain = computed(() => route.meta.hideNavbarMain)
const hideFooter = computed(() => route.meta.hideFooter)
</script>

<template>
  <body>
    <v-app>
      <NavbarMain v-if="!hideNavbarMain" />
      <RouterView />
      <Footer v-if="!hideFooter" />
    </v-app>
  </body>
</template>

<style></style>
