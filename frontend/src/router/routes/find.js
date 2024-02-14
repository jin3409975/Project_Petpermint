const find = [
  {
    path: '/find',
    name: 'find',
    component: '@/views/find/FindinfoView.vue',
    redirect: { name: 'find-id' },
    children: [
      {
        path: 'id',
        name: 'find-id',
        meta: { hideFooter: true, hideicon: true },
        component: '@/components/find/FindId.vue'
      },
      {
        path: 'password',
        name: 'find-password',
        meta: { hideFooter: true, hideicon: true },
        component: '@/components/find/FindPassword.vue'
      }
    ]
  }
]

export default find
