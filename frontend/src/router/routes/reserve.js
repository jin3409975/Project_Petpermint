import ReserveView from '@/views/reserve/ReserveView.vue'
import ReserveInitialView from '@/views/reserve/ReserveInitialView.vue'
import ReserveAppointView from '@/views/reserve/ReserveAppointView.vue'
import ReserveEmergency from '@/components/reserve/ReserveEmergency.vue'
import ReserveHospital from '@/components/reserve/ReserveHospital.vue'
import ReserveTime from '@/components/reserve/ReserveTime.vue'
import ReservePet from '@/components/reserve/ReservePet.vue'

const reserve = [
  {
    path: '/reserve',
    name: 'reserve',
    component: ReserveView,
    redirect: { name: 'reserve-initial' },
    children: [
      {
        path: 'initial',
        name: 'reserve-initial',
        component: ReserveInitialView,
        redirect: { name: 'reserve-initial-time' },
        children: [
          {
            path: 'hospital',
            name: 'reserve-initial-hospital',
            meta: { hideFooter: true },
            component: ReserveHospital
          },
          {
            path: 'time',
            name: 'reserve-initial-time',
            meta: { hideFooter: true },
            component: ReserveTime
          },
          {
            path: 'pet',
            name: 'reserve-initial-pet',
            meta: { hideFooter: true },
            component: ReservePet
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
        component: ReserveAppointView,
        redirect: { name: 'reserve-appoint-time' },
        children: [
          {
            path: 'hospital',
            name: 'reserve-appoint-hospital',
            meta: { hideFooter: true },
            component: ReserveHospital
          },
          {
            path: 'time',
            name: 'reserve-appoint-time',
            meta: { hideFooter: true },
            component: ReserveTime
          },
          {
            path: 'pet',
            name: 'reserve-appoint-pet',
            meta: { hideFooter: true },
            component: ReservePet
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
        meta: { hideicon: true, hideFooter: true },
        component: ReserveEmergency
      }
    ]
  }
]

export default reserve
