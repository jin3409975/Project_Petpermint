const register = [
  {
    path: '/register',
    name: 'register',
    component: '@/views/register/RegisterView.vue',
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
        component: '@/components/register/RegisterVet.vue'
      },
      {
        path: 'user',
        name: 'register-user',
        meta: { hideNavbarMain: true, hideicon: true },
        component: '@/views/register/RegisterUserView.vue',
        redirect: { name: 'register-info' },
        children: [
          {
            path: 'info',
            name: 'register-info',
            meta: { hideNavbarMain: true },
            component: '@/components/register/RegisterUser.vue'
          }
        ]
      }
    ]
  }
]

export default register
