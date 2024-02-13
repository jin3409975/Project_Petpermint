import AdminViewVue from '@/views/AdminView.vue'
import AdminAcountVue from '@/components/admin/AdminAcount.vue'

const admin = [
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

export default admin
