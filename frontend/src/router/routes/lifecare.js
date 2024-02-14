const lifecare = [
  {
    path: '/lifecare',
    name: 'lifecare',
    component: '@/views/lifecare/LifecareView.vue',
    redirect: { name: 'lifecare-venue' },
    children: [
      {
        path: 'community',
        name: 'lifecare-community',
        component: '',
        redirect: { name: '' },
        children: [
          {
            path: 'list',
            name: 'lifecare-community-list',
            meta: { backgroundColor: 'rgba(99, 136, 137, 0.3)', hideFooter: true },
            component: '@/views/lifecare/CommunityListView.vue'
          },
          {
            path: 'detail/:postId',
            name: 'lifecare-community-detail',
            component: '@/views/lifecare/CommunityDetailVue.vue'
          }
        ]
      },
      {
        path: 'venue',
        name: 'lifecare-venue',
        meta: { footerBgColor: '#3e6263' },
        component: '@/components/lifecare/VenueMap.vue'
      },
      {
        path: 'broadcast',
        name: 'lifecare-broadcast',
        meta: { footerBgColor: '#3e6263' },
        component: '@/views/lifecare/EnterBroadcastVue.vue',
        children: [
          {
            path: 'enter',
            name: 'lifecare-broadcast-enter',
            component: '@/components/lifecare/EnterBroadcast.vue',
            meta: { hideFooter: true }
          }
        ]
      }
    ]
  }
]

export default lifecare
