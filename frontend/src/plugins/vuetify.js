// src/plugins/vuetify.js
import '@mdi/font/css/materialdesignicons.css' // MDI CSS 임포트
import Vue from 'vue'
import Vuetify from 'vuetify/lib'
import { createVuetify, VuetifyDateAdapter } from 'vuetify'
import { VCalendar } from 'vuetify/labs/VCalendar'

Vue.use(Vuetify)

export default createVuetify({
  icons: {
    iconfont: 'mdi' // MDI 아이콘 사용 설정
  },
  date: {
    adapter: VuetifyDateAdapter
  },
  components: {
    VCalendar
  }
})
