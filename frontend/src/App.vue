<script setup>
import { ref } from 'vue'
import NavbarMain from '@/components/main/NavbarMain.vue'
import Footer from '@/components/main/Footer.vue'
import MyBook from '@/components/main/MyBook.vue'
import { computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useAccountStore } from '@/stores/account.js'

const router = useRouter()
const route = useRoute()
const store = useAccountStore()

const hideNavbarMain = computed(() => route.meta.hideNavbarMain)
const hideFooter = computed(() => route.meta.hideFooter)
const hideicon = computed(() => route.meta.hideicon)

const defaultImage = '/assets/img/emergency.png'
const hoverImage = '/assets/img/emergency_hover.png'
const currentImage = ref(defaultImage)
const handleMouseOver = () => {
  currentImage.value = hoverImage
}
const handleMouseOut = () => {
  currentImage.value = defaultImage
}

function goEmergency() {
  console.log('asdasdasdqefn qejfjoqekbwrh bjw')
  router.push({ name: 'reserve-emergency' })
}
const backgroundColor = computed(() => {
  console.log(route.meta.backgroundColor) // Check the value
  return route.meta.backgroundColor
})
</script>

<template>
  <body>
    <v-app :style="{ backgroundColor: backgroundColor }">
      <NavbarMain v-if="!hideNavbarMain" />
      <RouterView />
      <Footer v-if="!hideFooter" />
      <div
        v-if="!hideicon"
        class="emerg-container"
        @mouseover="handleMouseOver"
        @mouseout="handleMouseOut"
        @click="goEmergency"
      >
        <img :src="currentImage" class="img-style" />
        <span>응급예약</span>
      </div>
      <MyBook v-if="store.loginStatus" />
    </v-app>
  </body>
</template>

<style scoped>
.emerg-container {
  position: fixed;
  bottom: 15px;
  right: 20px;
  z-index: 1000;
  cursor: pointer;
  width: 150px;
  height: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
  border: rgb(244, 46, 46) 2px solid;
  border-radius: 15px;
}

.img-style {
  width: auto;
  height: 70%;
  margin-right: 4px;
}

.emerg-container:hover {
  background-color: #ff2f2f;
  border-radius: 15px;
  border: #ff2f2f 2px solid;
}
span {
  white-space: nowrap;
  font-weight: bold;
  font-size: 25px;
  color: rgb(244, 46, 46);
}
.emerg-container:hover span {
  color: rgb(255, 255, 255);
}
</style>
