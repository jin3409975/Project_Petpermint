import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

// Vuetify
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'

// import jQuery from 'jquery'

// MDI CSS 추가
import '@mdi/font/css/materialdesignicons.css'

// Axios 추가
import axios from 'axios'

const app = createApp(App)

const vuetify = createVuetify({
  components,
  directives,
  icons: {
    iconfont: 'mdi'
  }
})

// global.$ = jQuery

app.use(createPinia())
app.use(router)
app.use(vuetify)
app.use(axios)
app.mount('#app')
