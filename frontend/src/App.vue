<script setup>
import NavbarMain from '@/components/main/NavbarMain.vue'
import Footer from '@/components/main/Footer.vue'
import { computed } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const hideNavbarMain = computed(() => route.meta.hideNavbarMain)
const hideFooter = computed(() => route.meta.hideFooter)
const hideicon = computed(() => route.meta.hideicon)
</script>

<template>
  <body>
    <v-app>
      <NavbarMain v-if="!hideNavbarMain" />
      <RouterView />
      <Footer v-if="!hideFooter" />
      <v-icon v-if="!hideicon" class="fixed-icon">mdi-car-emergency</v-icon>
    </v-app>
  </body>
</template>

<style>
.fixed-icon {
  position: fixed;
  bottom: 20px;
  right: 20px;
  z-index: 1000;
  cursor: pointer;
  font-size: 48px;
}
</style>
