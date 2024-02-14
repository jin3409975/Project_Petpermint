const main = [
  {
    path: '',
    name: 'main',
    component: '@/views/main/MainView.vue',
    redirect: { name: 'main-home' },
    children: [
      {
        path: '',
        meta: { hideNavbarMain: true },
        name: 'main-home',
        component: '@/components/main/Main.vue'
      },
      {
        path: 'vet',
        name: 'main-vet',
        meta: { hideNavbarMain: true },
        component: '@/components/main/MainVet.vue'
      }
    ]
  }
]

export default main
