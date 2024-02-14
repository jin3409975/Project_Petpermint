const cam = [
  {
    path: '/cam',
    name: 'cam',
    meta: { hideNavbarMain: true, hideNavbar: true, hideicon: true, hideFooter: true },
    component: '',
    redirect: { name: 'cam-consult' },
    children: [
      {
        path: 'consult/:appointId',
        name: 'cam-consult',
        component: () => import('@/components/cam/CamConsult.vue')
      },
      {
        path: 'broadcast/:sessionId',
        name: 'cam-broadcast',
        component: () => import('@/components/cam/CamBroadcast.vue')
      }
    ]
  }
]

export default cam
