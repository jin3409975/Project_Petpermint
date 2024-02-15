<script setup>
import { onMounted, onBeforeUnmount, ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import { useAccountStore } from '@/stores/account.js'
const defaultLogoUrl = new URL('/assets/img/logo.png', import.meta.url).href
const scrolledLogoUrl = new URL('/assets/img/logomain.png', import.meta.url).href
const store = useAccountStore()
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

const isLoggedIn = computed(() => {
  //console.log('computed test ', store.loginStatus)
  if (store.loginStatus == null) {
    return false
  }
  return store.loginStatus
})
const handleLoginLogout = () => {
  if (isLoggedIn.value) {
    store.logout()
    router.push({ name: 'main-home' })
  } else {
    router.push({ name: 'login-home' })
  }
}

const gotoMyPage = () => {
  if (localStorage.usertype == 1) router.push({ name: 'mypage-user-info' })
  else if (localStorage.usertype == 2) router.push({ name: 'mypage-vet-info' })
}

const navigateToMain = () => {
  if (localStorage.usertype == 2) router.push({ name: 'main-vet' })
  else router.push({ name: 'main-home' })
}
const navigateToInitial = () => {
  if (!isLoggedIn.value) {
    alert('로그인 후 이용 가능합니다.')
    return false
  }
  router.push({ name: 'reserve-initial' })
}
const navigateToAppoint = () => {
  if (!isLoggedIn.value) {
    alert('로그인 후 이용 가능합니다.')
    return false
  }
  router.push({ name: 'reserve-appoint' })
}
const navigateToCommunity = () => {
  router.push({ name: 'lifecare-community-list' })
}
const navigateToBroadcast = () => {
  router.push({ name: 'lifecare-broadcast' })
}
const navigateToVetRegister = () => {
  router.push({ name: 'register-vet' })
}
const navigateToUserRegister = () => {
  router.push({ name: 'register-user' })
}
const navigateToVenue = () => {
  router.push({ name: 'lifecare-venue' })
}
const isVeterinarian = computed(() => {
  const userType = localStorage.getItem('usertype')
  return userType === '2'
})
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

        <a v-if="!isVeterinarian" @click.prevent="navigateToInitial" class="home-link"
          >초기 상담예약</a
        >
        <a v-if="!isVeterinarian" @click.prevent="navigateToAppoint" class="home-link"
          >병원 진료예약</a
        >
        <v-menu open-on-hover>
          <template v-slot:activator="{ props }">
            <a v-bind="props" class="home-link">
              라이프케어
              <v-icon>mdi-triangle-small-down</v-icon>
            </a>
          </template>
          <v-list>
            <v-list-item link @click="navigateToCommunity">
              <v-list-item-title>펫닥 커뮤니티</v-list-item-title>
            </v-list-item>
            <v-list-item link @click="navigateToBroadcast">
              <v-list-item-title>전문가 라이브 방송</v-list-item-title>
            </v-list-item>
            <v-list-item link>
              <v-list-item-title @click="navigateToVenue">반려동물 시설/센터</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
      </div>
      <div style="display: flex; align-items: center; column-gap: 25px; margin-right: 15px">
        <!-- @이민지 로그인되었을 때 마이페이지로 갈 수 있게하는 라우터 필요. 자신의 프로필 이미지를 동그랗게 만들고 그걸 클릭하면 마이페지로 바로 보내면 됩니다.
        <img v-if="isLoggedIn" src="" alt=""> -->
        <a @click="gotoMyPage" v-if="isLoggedIn" class="home-link"> 마이페이지 </a>
        <a @click="handleLoginLogout" class="home-link">{{ isLoggedIn ? '로그아웃' : '로그인' }}</a>
        <v-menu v-if="!isLoggedIn" open-on-hover>
          <template v-slot:activator="{ props }">
            <a v-bind="props" class="home-link">
              회원가입
              <v-icon>mdi-triangle-small-down</v-icon>
            </a>
          </template>
          <v-list>
            <v-list-item link>
              <v-list-item-title @click="navigateToUserRegister"
                >보호자용 회원가입</v-list-item-title
              >
            </v-list-item>
            <v-list-item link>
              <v-list-item-title @click="navigateToVetRegister"
                >수의사용 회원가입</v-list-item-title
              >
            </v-list-item>
          </v-list>
        </v-menu>
      </div>
    </div>
  </v-app-bar>
</template>

<style scoped>
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
  box-shadow: none !important;
  color: rgb(59, 59, 59);
  transition: all 0.3s ease;
}

.is-scrolled .home-link {
  color: rgb(59, 59, 59);
}
</style>
