import FindinfoView from '@/views/find/FindinfoView.vue'
import FindId from '@/components/find/FindId.vue'
import FindPassword from '@/components/find/FindPassword.vue'

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
