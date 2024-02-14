const login = [
  {
    path: '/login',
    name: 'login',
    component: '@/views/login/LoginView.vue',
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
        component: '@/components/login/Login.vue'
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
