import MypageView from '@/views/mypage/MypageView.vue'
import MypageUserView from '@/views/mypage/MypageUserView.vue'
import MypageVetView from '@/views/mypage/MypageVetView.vue'
import MypageUserInfo from '@/components/mypage/MypageUserInfo.vue'
import MypageUserListView from '@/views/mypage/MypageUserListView.vue'
import MypageUserListCurrent from '@/components/mypage/MypageUserListCurrent.vue'
import MypageVetInfo from '@/components/mypage/MypageVetInfo.vue'
import MypageVetListCurrent from '@/components/mypage/MypageVetListCurrent.vue'

const mypage = [
  {
    path: '/mypage',
    name: 'mypage',
    component: MypageView,
    redirect: { name: 'mypage-user' },
    children: [
      {
        path: 'user',
        name: 'mypage-user',
        component: MypageUserView,
        redirect: { name: 'mypage-user-info' },
        children: [
          {
            path: 'info',
            name: 'mypage-user-info',
            meta: {
              hideFooter: true,
              backgroundColor: 'rgba(102, 139, 167, 0.211)'
            },
            component: MypageUserInfo
          },
          {
            path: 'list',
            name: 'mypage-user-list',
            component: MypageUserListView,
            redirect: { name: 'mypage-user-list-current' },
            meta: {
              hideFooter: true
            },
            children: [
              {
                path: 'current',
                name: 'mypage-user-list-current',
                component: MypageUserListCurrent
              }
            ]
          }
        ]
      },
      {
        path: 'vet',
        name: 'mypage-vet',
        component: MypageVetView,
        redirect: { name: 'mypage-vet-info' },
        meta: { hideicon: true },
        children: [
          {
            path: 'info',
            name: 'mypage-vet-info',
            component: MypageVetInfo
          },
          {
            path: 'list',
            name: 'mypage-vet-list',
            component: '',
            redirect: { name: 'mypage-vet-list-current' },
            children: [
              {
                path: 'current',
                name: 'mypage-vet-list-current',
                component: MypageVetListCurrent
              }
            ]
          }
        ]
      }
    ]
  }
]

export default mypage
