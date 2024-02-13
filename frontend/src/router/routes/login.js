import LoginView from '@/views/login/LoginView.vue'
import Login from '@/components/login/Login.vue'

const login = [
  {
    path: '/login',
    name: 'login',
    component: LoginView,
    redirect: { name: 'login-home' },
    children: [
      {
        path: '',
        name: 'login-home',
        meta: {
          hideicon: true,
          hideFooter: true,
          backgroundColor: 'rgba(189, 243, 254, 0.211)'
        },
        component: Login
      },
      {
        path: 'pending',
        name: 'login-pending',
        component: ''
      }
    ]
  }
]

export default login
