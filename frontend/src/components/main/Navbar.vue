<script setup>
import { onMounted, onBeforeUnmount, ref, computed } from 'vue'
import { useRouter } from 'vue-router'

const defaultLogoUrl = new URL('@/assets/img/logo.png', import.meta.url).href
const scrolledLogoUrl = new URL('@/assets/img/logomain.png', import.meta.url).href

const logoUrl = ref(defaultLogoUrl)

const router = useRouter()

const isScrolled = ref(false)

const logoStyle = ref({
  cursor: 'pointer',
  marginBottom: '8px',
  marginLeft: '5px',
  height: '70px'
})

const updateNavBarOnScroll = () => {
  const scrolled = window.scrollY > 50
  isScrolled.value = scrolled
  logoUrl.value = scrolled ? scrolledLogoUrl : defaultLogoUrl
  logoStyle.value.height = scrolled ? '60px' : '70px'
}

onMounted(() => {
  window.addEventListener('scroll', updateNavBarOnScroll)
})

onBeforeUnmount(() => {
  window.removeEventListener('scroll', updateNavBarOnScroll)
})

const navigateToMain = () => {
  router.push({ name: 'main-home' })
}
const navigateToLogin = () => {
  router.push({ name: 'login-home' })
}
const navigateToRegister = () => {
  router.push({ name: 'register-home' })
}
const navigateToInitial = () => {
  router.push({ name: 'reserve-initial' })
}
const navigateToAppoint = () => {
  router.push({ name: 'reserve-appoint' })
}
const navigateToCommunity = () => {
  router.push({ name: 'lifecare-community-list' })
}
</script>

<template>
  <v-app-bar :class="{ 'is-scrolled': isScrolled }" app fixed id="custom-nav-bar">
    <div
      style="
        display: flex;
        justify-content: space-between;
        align-items: center;
        width: 100%;
        margin-top: 10px;
      "
    >
      <div style="display: flex; align-items: center; column-gap: 45px">
        <img @click="navigateToMain" :src="logoUrl" :style="logoStyle" />
        <a @click.prevent="navigateToMain" class="home-link">Home</a>
        <a @click.prevent="navigateToInitial" class="home-link">초기 상담예약</a>
        <a @click.prevent="navigateToAppoint" class="home-link">병원 진료예약</a>
        <v-menu open-on-hover>
          <template v-slot:activator="{ props }">
            <a v-bind="props" class="home-link">
              라이프케어
              <v-icon>mdi-triangle-small-down</v-icon>
            </a>
          </template>
          <v-list bg-color="black">
            <v-list-item :to="{ name: 'lifecare-community-list' }" link>
              <v-list-item-title>펫닥 커뮤니티</v-list-item-title>
            </v-list-item>
            <v-list-item link>
              <v-list-item-title>전문가 라이브 방송</v-list-item-title>
            </v-list-item>
            <v-list-item link>
              <v-list-item-title>반려동물 시설/센터</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
      </div>
      <div style="display: flex; align-items: center; column-gap: 25px; margin-right: 15px">
        <a @click="navigateToLogin" class="home-link">로그인</a>
        <a @click="navigateToRegister" class="home-link">회원가입</a>
      </div>
    </div>
  </v-app-bar>
</template>

<style>
.home-link {
  font-size: 18px;
  font-weight: bold;
  color: #ffffff;
  text-decoration: none;
  background-image: linear-gradient(to right, transparent, transparent);
  background-repeat: no-repeat;
  background-size: 0% 3px;
  background-position: 0 100%;
  transition: background-size 0.3s ease;
  cursor: pointer;
}

.home-link:hover {
  background-image: linear-gradient(to right, #d2e0fb, #d2e0fb);
  background-size: 100% 3px;
}

#custom-nav-bar {
  z-index: 1000 !important;
  background-color: rgba(0, 0, 0, 0.115);
  backdrop-filter: blur(10px);
}

.is-scrolled {
  background-color: white !important;
  color: rgb(59, 59, 59);
  transition: all 0.3s ease;
}

.is-scrolled .home-link {
  color: rgb(59, 59, 59);
}
</style>
