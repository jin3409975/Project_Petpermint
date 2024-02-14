const mypage = [
  {
    path: '/mypage',
    name: 'mypage',
    component: '@/views/mypage/MypageView.vue',
    redirect: { name: 'mypage-user' },
    children: [
      {
        path: 'user',
        name: 'mypage-user',
        component: '@/views/mypage/MypageUserView.vue',
        redirect: { name: 'mypage-user-info' },
        children: [
          {
            path: 'info',
            name: 'mypage-user-info',
            component: '@/components/mypage/MypageUserInfo.vue'
          },
          {
            path: 'list',
            name: 'mypage-user-list',
            component: '@/views/mypage/MypageUserListView.vue',
            redirect: { name: 'mypage-user-list-current' },
            children: [
              {
                path: 'current',
                name: 'mypage-user-list-current',
                component: '@/components/mypage/MypageUserListCurrent.vue'
              }
            ]
          }
        ]
      },
      {
        path: 'vet',
        name: 'mypage-vet',
        component: '@/views/mypage/MypageVetView.vue',
        redirect: { name: 'mypage-vet-info' },
        meta: { hideicon: true },
        children: [
          {
            path: 'info',
            name: 'mypage-vet-info',
            component: '@/components/mypage/MypageVetInfo.vue'
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
                component: '@/components/mypage/MypageVetListCurrent.vue'
              }
            ]
          }
        ]
      }
    ]
  }
]

export default mypage
