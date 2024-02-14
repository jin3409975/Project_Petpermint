const reserve = [
  {
    path: '/reserve',
    name: 'reserve',
    component: '@/views/reserve/ReserveView.vue',
    redirect: { name: 'reserve-initial' },
    children: [
      {
        path: 'initial',
        name: 'reserve-initial',
        component: '@/views/reserve/ReserveInitialView.vue',
        redirect: { name: 'reserve-initial-time' },
        children: [
          {
            path: 'hospital',
            name: 'reserve-initial-hospital',
            meta: { hideFooter: true },
            component: '@/components/reserve/ReserveHospital.vue'
          },
          {
            path: 'time',
            name: 'reserve-initial-time',
            meta: { hideFooter: true },
            component: '@/components/reserve/ReserveTime.vue'
          },
          {
            path: 'pet',
            name: 'reserve-initial-pet',
            meta: { hideFooter: true },
            component: '@/components/reserve/ReservePet.vue'
          }
        ]
      },
      {
        path: 'complete',
        name: 'reserve-initial-complete',
        component: ''
      },
      {
        path: 'appoint',
        name: 'reserve-appoint',
        component: '@/views/reserve/ReserveAppointView.vue',
        redirect: { name: 'reserve-appoint-time' },
        children: [
          {
            path: 'hospital',
            name: 'reserve-appoint-hospital',
            meta: { hideFooter: true },
            component: '@/components/reserve/ReserveHospital.vue'
          },
          {
            path: 'time',
            name: 'reserve-appoint-time',
            meta: { hideFooter: true },
            component: '@/components/reserve/ReserveTime.vue'
          },
          {
            path: 'pet',
            name: 'reserve-appoint-pet',
            meta: { hideFooter: true },
            component: '@/components/reserve/ReservePet.vue'
          }
        ]
      },
      {
        path: 'vet',
        name: 'reserve-vet',
        component: ''
      },
      {
        path: 'emergency',
        name: 'reserve-emergency',
        meta: { hideFooter: true },
        component: '@/components/reserve/ReserveEmergency.vue'
      }
    ]
  }
]

export default reserve
