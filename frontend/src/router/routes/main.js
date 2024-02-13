import MainView from '@/views/MainView.vue'
import Main from '@/components/main/Main.vue'
import MainVet from '@/components/main/MainVet.vue'

const main = [
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
        path: 'vet',
        name: 'main-vet',
        meta: { hideNavbarMain: true },
        component: MainVet
      }
    ]
  }
]

export default main
