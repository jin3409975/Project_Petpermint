import RegisterView from '@/views/account/RegisterView.vue'
import RegisterVet from '@/components/register/RegisterVet.vue'
// import RegisterComplete from '@/components/register/RegisterComplete.vue'
import RegisterUserView from '@/views/RegisterUserView.vue'
import RegisterUser from '@/components/register/RegisterUser.vue'
// import RegisterPet from '@/components/register/RegisterPet.vue'

const register = [
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
            meta: { hideNavbarMain: true },
            component: RegisterUser
          }
        ]
      }
    ]
  }
]

export default register
