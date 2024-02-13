import LifecareViewVue from '@/views/lifecare/LifecareView.vue'

import CommunityListVue from '@/views/lifecare/CommunityListView.vue'
import CommunityDetailVue from '@/views/lifecare/CommunityDetailVue.vue'

import VenueMapVue from '@/components/lifecare/VenueMap.vue'

import EnterBroadcast from '@/components/lifecare/EnterBroadcast.vue'
import EnterBroadcastVue from '@/views/lifecare/EnterBroadcastVue.vue'

const lifecare = [
  {
    path: '/lifecare',
    name: 'lifecare',
    component: LifecareViewVue,
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
            component: CommunityListVue
          },
          {
            path: 'detail/:postId',
            name: 'lifecare-community-detail',
            component: CommunityDetailVue
          }
        ]
      },
      {
        path: 'venue',
        name: 'lifecare-venue',
        meta: { footerBgColor: '#3e6263' },
        component: VenueMapVue
      },
      {
        path: 'broadcast',
        name: 'lifecare-broadcast',
        meta: { footerBgColor: '#3e6263' },
        component: EnterBroadcastVue,
        children: [
          {
            path: 'enter',
            name: 'lifecare-broadcast-enter',
            component: EnterBroadcast,
            meta: { hideFooter: true }
          }
        ]
      }
    ]
  }
]

export default lifecare
