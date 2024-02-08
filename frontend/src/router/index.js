import { createRouter, createWebHistory } from 'vue-router'
// import HomeView from '@/views/MainView.vue'
import RegisterView from '@/views/account/RegisterView.vue'
import LoginView from '@/views/account/LoginView.vue'
import Login from '@/components/login/Login.vue'
import MainView from '@/views/MainView.vue'
import MainVet from '@/components/main/MainVet.vue'
import Main from '@/components/main/Main.vue'
import MainUser from '@/components/main/MainUser.vue'
import RegisterVet from '@/components/register/RegisterVet.vue'
// import RegisterComplete from '@/components/register/RegisterComplete.vue'
import RegisterUserView from '@/views/RegisterUserView.vue'
import RegisterUser from '@/components/register/RegisterUser.vue'
// import RegisterPet from '@/components/register/RegisterPet.vue'

import ReserveView from '@/views/ReserveView.vue'
import ReserveInitialView from '@/views/ReserveInitialView.vue'
import ReserveAppointView from '@/views/ReserveAppointView.vue'

import ReserveHospital from '@/components/reserve/ReserveHospital.vue'
import ReserveTime from '@/components/reserve/ReserveTime.vue'
import ReservePet from '@/components/reserve/ReservePet.vue'

import AdminViewVue from '@/views/AdminView.vue'
import AdminAcountVue from '@/components/admin/AdminAcount.vue'
import MypageView from '@/views/MypageView.vue'
import MypageUserView from '@/views/MypageUserView.vue'
import MypageVetView from '@/views/MypageVetView.vue'
import MypageUserInfo from '@/components/mypage/MypageUserInfo.vue'
import MypageUserListView from '@/views/MypageUserListView.vue'
import MypageUserListCurrent from '@/components/mypage/MypageUserListCurrent.vue'
import MypageVetInfo from '@/components/mypage/MypageVetInfo.vue'
import MypageVetListCurrent from '@/components/mypage/MypageVetListCurrent.vue'

import FindId from '@/components/login/FindId.vue'
import FindPassword from '@/components/login/FindPassword.vue'
import FindinfoView from '@/views/account/FindinfoView.vue'
import CamBroadcastVue from '@/components/cam/CamBroadcast.vue'
import CommunityListVue from '@/views/CommunityListView.vue'
import CommunityDetailVue from '@/views/CommunityDetailVue.vue'

import LifecareViewVue from '@/views/LifecareView.vue'
import VenueMapVue from '@/components/lifecare/VenueMap.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'main',
      component: MainView,
      redirect: { name: 'main-home' },
      children: [
        {
          path: '',
          name: 'main',
          component: MainView,
          redirect: { name: 'main-home' },
          children: [
            {
              path: '',
              meta: { hideNavbarMain: true },
              name: 'main-home',
              component: Main
            },
            {
              path: 'user',
              name: 'main-user',
              meta: { hideNavbarMain: true },
              component: MainUser
            },
            {
              path: 'vet',
              name: 'main-vet',
              component: MainVet
            }
          ]
        },
        {
          path: '/register',
          name: 'register',
          component: RegisterView,
          meta: { hideFooter: true },
          redirect: { name: 'register-home' },
          children: [
            // {
            //   path: '',
            //   name: 'register-home',
            //   component: RegisterType
            // },
            {
              path: 'vet',
              name: 'register-vet',
              meta: { hideNavbarMain: true, hideNavbar: true, hideicon: true },
              component: RegisterVet
            },
            {
              path: 'user',
              name: 'register-user',
              meta: { hideNavbarMain: true, hideicon: true },
              component: RegisterUserView,
              redirect: { name: 'register-info' },
              children: [
                {
                  path: 'info',
                  name: 'register-info',
                  component: RegisterUser
                }
              ]
            }
          ]
        },
        {
          path: '/login',
          name: 'login',
          component: LoginView,
          redirect: { name: 'login-home' },
          children: [
            {
              path: '',
              name: 'login-home',
              meta: { hideicon: true },
              component: Login
            },
            {
              path: 'pending',
              name: 'login-pending',
              component: ''
            }
          ]
        },
        {
          path: '/find',
          name: 'find',
          component: FindinfoView,
          redirect: { name: 'find-id' },
          children: [
            {
              path: 'id',
              name: 'find-id',
              component: FindId
            },
            {
              path: 'password',
              name: 'find-password',
              component: FindPassword
            }
          ]
        },
        {
          path: '/reserve',
          name: 'reserve',
          component: ReserveView,
          redirect: { name: 'reserve-initial' },
          children: [
            {
              path: 'initial',
              name: 'reserve-initial',
              component: ReserveInitialView,
              redirect: { name: 'reserve-initial-time' },
              children: [
                {
                  path: 'hospital',
                  name: 'reserve-initial-hospital',
                  component: ReserveHospital
                },
                {
                  path: 'time',
                  name: 'reserve-initial-time',
                  component: ReserveTime
                },
                {
                  path: 'pet',
                  name: 'reserve-initial-pet',
                  component: ReservePet
                }
              ]
            },
            {
              path: 'complete',
              name: 'reserve-initial-complete',
              component: ''
            },
            {
              path: 'appoint',
              name: 'reserve-appoint',
              component: ReserveAppointView,
              redirect: { name: 'reserve-appoint-time' },
              children: [
                {
                  path: 'hospital',
                  name: 'reserve-appoint-hospital',
                  component: ReserveHospital
                },
                {
                  path: 'time',
                  name: 'reserve-appoint-time',
                  component: ReserveTime
                },
                {
                  path: 'pet',
                  name: 'reserve-appoint-pet',
                  component: ReservePet
                }
              ]
            },
            {
              path: 'vet',
              name: 'reserve-vet',
              component: ''
            },
            {
              path: 'emergency',
              name: 'reserve-emergency',
              component: ''
            }
          ]
        },
        {
          path: '/cam',
          name: 'cam',
          meta: { hideNavbarMain: true },
          component: '',
          redirect: { name: 'cam-consult' },
          children: [
            {
              path: 'consult',
              name: 'cam-consult',
              component: () => import('@/components/cam/CamConsult.vue')
            },
            {
              path: 'broadcast',
              name: 'cam-broadcast',
              component: CamBroadcastVue
            }
          ]
        },
        {
          path: '/lifecare',
          name: 'lifecare',
          component: LifecareViewVue,
          redirect: { name: 'lifecare-venue' },
          children: [
            {
              path: 'community',
              name: 'lifecare-community',
              component: '',
              redirect: { name: '' },
              children: [
                {
                  path: 'list',
                  name: 'lifecare-community-list',
                  component: CommunityListVue
                },
                {
                  path: 'detail/:postId',
                  name: 'lifecare-community-detail',
                  component: CommunityDetailVue
                }
              ]
            },
            {
              path: 'venue',
              name: 'lifecare-venue',
              component: VenueMapVue
            },
            {
              path: 'broadcast',
              name: 'lifecare-broadcast',
              component: '',
              children: [
                {
                  path: 'enter',
                  name: 'lifecare-broadcast-enter',
                  component: ''
                }
              ]
            }
          ]
        },
        {
          path: '/mypage',
          name: 'mypage',
          component: MypageView,
          redirect: { name: 'mypage-user' },
          children: [
            {
              path: 'user',
              name: 'mypage-user',
              component: MypageUserView,
              redirect: { name: 'mypage-user-info' },
              children: [
                {
                  path: 'info',
                  name: 'mypage-user-info',
                  component: MypageUserInfo
                },
                {
                  path: 'list',
                  name: 'mypage-user-list',
                  component: MypageUserListView,
                  redirect: { name: 'mypage-user-list-current' },
                  children: [
                    {
                      path: 'current',
                      name: 'mypage-user-list-current',
                      component: MypageUserListCurrent
                    }
                  ]
                }
              ]
            },
            {
              path: 'vet',
              name: 'mypage-vet',
              component: MypageVetView,
              redirect: { name: 'mypage-vet-info' },
              children: [
                {
                  path: 'info',
                  name: 'mypage-vet-info',
                  component: MypageVetInfo
                },
                {
                  path: 'list',
                  name: 'mypage-vet-list',
                  component: '',
                  redirect: { name: 'mypage-vet-list-current' },
                  children: [
                    {
                      path: 'current',
                      name: 'mypage-vet-list-current',
                      component: MypageVetListCurrent
                    }
                  ]
                }
              ]
            }
          ]
        },
        {
          path: '/admin',
          name: 'admin',
          component: AdminViewVue,
          meta: { hideNavbarMain: true },
          redirect: { name: 'admin-account' },
          children: [
            {
              path: 'account',
              name: 'admin-account',
              component: AdminAcountVue
            },
            {
              path: 'reserve',
              name: 'admin-reserve',
              component: ''
            },
            {
              path: 'community',
              name: 'admin-community',
              component: ''
            }
          ]
        }
      ]
    }
  ]
})

export default router
