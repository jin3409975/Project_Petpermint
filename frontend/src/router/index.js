import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/account/LoginView.vue'
import RegisterView from '../views/account/RegisterView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'main',
      component: HomeView,
      redirect: { name: 'main-default' },
      children: [
        {
          path: 'default',
          name: 'main-default',
          component: '',
        },
        {
          path: 'user',
          name: 'main-user',
          component: '',
        },
        {
          path: 'vet',
          name: 'main-vet',
          component: '',
        }
      ],
    },
    {
      path: '/register',
      name: 'register',
      component: "",
      redirect: { name: 'register-home' },
      children: [
        {
          path: '/',
          name: 'register-home',
          component: '',
        },
        {
          path: 'vet',
          name: 'register-vet',
          component: '',
        },
        {
          path: 'user',
          name: 'register-user',
          component: '',
          children: [
            {
              path: 'pet',
              name: 'register-pet',
              component: '',
            }
          ],
        },
        {
          path: 'complete',
          name: 'register-complete',
          component: '',
        }
      ],
    },
    {
      path: '/login',
      name: 'login',
      component: "",
      redirect: { name: 'login-home' },
      children: [
        {
          path: '/',
          name: 'login-home',
          component: '',
        },
        {
          path: 'pending',
          name: 'login-pending',
          component: '',
        },
      ],
    },
    {
      path: '/find',
      name: 'find',
      component: "",
      redirect: { name: 'find-id' },
      children: [
        {
          path: 'id',
          name: 'find-id',
          component: '',
        },
        {
          path: 'password',
          name: 'find-password',
          component: '',
        },
      ],
    },
    {
      path: '/reserve',
      name: 'reserve',
      component: "",
      redirect: { name: 'reserve-user' },
      children: [
        {
          path: 'initial',
          name: 'reserve-initial',
          component: '',
          redirect: { name: '' },
          children: [
            {
              path: 'date',
              name: 'reserve-initial-date',
              component: '',
            },
            {
              path: 'hospital',
              name: 'reserve-initial-hospital',
              component: '',
            },
            {
              path: 'time',
              name: 'reserve-initial-time',
              component: '',
            },
            {
              path: 'pet',
              name: 'reserve-initial-pet',
              component: '',
            },
            {
              path: 'complete',
              name: 'reserve-initial-complete',
              component: '',
            },
          ],
        },
        {
          path: 'appoint',
          name: 'reserve-appoint',
          component: '',
          redirect: { name: '' },
          children: [
            {
              path: 'date',
              name: 'reserve-appoint-date',
              component: '',
            },
            {
              path: 'hospital',
              name: 'reserve-appoint-hospital',
              component: '',
            },
            {
              path: 'time',
              name: 'reserve-appoint-time',
              component: '',
            },
            {
              path: 'pet',
              name: 'reserve-appoint-pet',
              component: '',
            },
            {
              path: 'complete',
              name: 'reserve-appoint-complete',
              component: '',
            },
          ],
        },
        {
          path: 'vet',
          name: 'reserve-vet',
          component: '',
        },
        {
          path: 'emergency',
          name: 'reserve-emergency',
          component: '',
        },
      ],
    },
    {
      path: '/cam',
      name: 'cam',
      component: '',
      redirect: { name: 'cam-consult' },
      children: [
        {
          path: 'consult',
          name: 'cam-consult',
          component: '',
        },
        {
          path: 'broadcast',
          name: 'cam-broadcast',
          component: '',
        }
      ],
    },
    {
      path: '/lifecare',
      name: 'lifecare',
      component: '',
      redirect: { name: 'lifecare-community' },
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
              component: '',
            },
            {
              path: 'detail/:listno',
              name: 'lifecare-venue-detail',
              component: '',
            },
          ],
        },
        {
          path: 'venue',
          name: 'lifecare-venue',
          component: '',
        },
        {
          path: 'broadcast',
          name: 'lifecare-venue',
          component: '',
          children: [
            {
              path: 'enter',
              name: 'lifecare-broadcast-enter',
              component: '',
            },
          ],
        }
      ],
    },
    {
      path: '/mypage',
      name: 'mypage',
      component: "",
      redirect: { name: 'mypage-user' },
      children: [
        {
          path: 'user',
          name: 'mypage-user',
          component: '',
          redirect: { name: 'mypage-user-info' },
          children: [
            {
              path: 'info',
              name: 'mypage-user-info',
              component: '',
            },
            {
              path: 'list',
              name: 'mypage-user-list',
              component: '',
              redirect: { name: 'mypage-user-list-current' },
              children: [
                {
                  path: 'current',
                  name: 'mypage-user-list-current',
                  component: '',
                  
                },
                {
                  path: 'past',
                  name: 'mypage-user-list-past',
                  component: '',
                },
              ],
            },
          ],
        },
        {
          path: 'vet',
          name: 'mypage-user',
          component: '',
          redirect: { name: 'mypage-vet-info' },
          children: [
            {
              path: 'info',
              name: 'mypage-vet-info',
              component: '',
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
                  component: '',
                  
                },
                {
                  path: 'past',
                  name: 'mypage-vet-list-past',
                  component: '',
                },
              ],
            },
          ],
        },
      ],
    },
    {
      path: '/admin',
      name: 'admin',
      component: "",
      redirect: { name: 'admin-account' },
      children: [
        {
          path: 'account',
          name: 'admin-account',
          component: "",
        },
        {
          path: 'reserve',
          name: 'admin-reserve',
          component: "",
        },
        {
          path: 'community',
          name: 'admin-community',
          component: "",
        }
      ]
    }
  ]
})

export default router
