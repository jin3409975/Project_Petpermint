import { createRouter, createWebHistory } from 'vue-router'
// import HomeView from '@/views/MainView.vue'
import MainView from '@/views/MainView.vue'

import main from '@/router/routes/main'
import register from '@/router/routes/register'
import login from '@/router/routes/login'
import find from '@/router/routes/login'
import reserve from '@/router/routes/reserve'
import cam from '@/router/routes/cam'
import lifecare from '@/router/routes/lifecare'
import mypage from '@/router/routes/mypage'
import admin from '@/router/routes/admin'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'main',
      component: MainView,
      redirect: { name: 'main-home' },
      children: [
        ...main,
        ...register,
        ...login,
        ...find,
        ...reserve,
        ...cam,
        ...lifecare,
        ...mypage,
        ...admin
      ]
    }
  ]
})

export default router
