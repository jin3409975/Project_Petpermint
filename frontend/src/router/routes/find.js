import FindinfoView from '@/views/account/FindinfoView.vue'
import FindId from '@/components/login/FindId.vue'
import FindPassword from '@/components/login/FindPassword.vue'

const find = [
  {
    path: '/find',
    name: 'find',
    component: FindinfoView,
    redirect: { name: 'find-id' },
    children: [
      {
        path: 'id',
        name: 'find-id',
        meta: { hideFooter: true, hideicon: true },
        component: FindId
      },
      {
        path: 'password',
        name: 'find-password',
        meta: { hideFooter: true, hideicon: true },
        component: FindPassword
      }
    ]
  }
]

export default find
