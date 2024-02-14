const admin = [
  {
    path: '/admin',
    name: 'admin',
    component: '@/views/admin/AdminView.vue',
    meta: { hideNavbarMain: true },
    redirect: { name: 'admin-account' },
    children: [
      {
        path: 'account',
        name: 'admin-account',
        component: '@/components/admin/AdminAcount.vue'
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

export default admin
