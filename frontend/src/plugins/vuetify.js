// src/plugins/vuetify.js
import '@mdi/font/css/materialdesignicons.css' // MDI CSS 임포트
import Vue from 'vue'
import Vuetify from 'vuetify/lib'

Vue.use(Vuetify)

export default new Vuetify({
    icons: {
        iconfont: 'mdi', // MDI 아이콘 사용 설정
    },
})
