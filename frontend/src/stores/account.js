// import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import { useRouter } from 'vue-router'
// import axios from 'axios'

export const useAccountStore = defineStore('account', () => {
    const router = useRouter()
    // const API_URL = 'http://127.0.0.1:8000'
    // const token = ref(null)
    // const userdata = ref({})
    // const isLogin = computed(() => {
    //     if (token.value === null) {
    //       return false
    //     } else {
    //       return true
    //     }
    //   })
  
    const logIn = function (userdata) {
      const { useremail, password } = userdata
  
      axios({
        method: 'post',
        // url: `${API_URL}/accounts/login/`,
        data: {
          useremail, password
        }
      })
        .then((res) => {
          console.log(res.data)
        //   token.value = res.data.key
          router.push({ name: 'HomeView' })
        })
        .catch((err) => {
          console.log('Error occurred:', err)
        })
    }
  
    // const logOut = function () {
    //   axios({
    //     method: 'post',
    //     url: `${API_URL}/accounts/logout/`,
    //   })
    //     .then((res) => {
    //       token.value = null
    //       window.alert('로그아웃 되었습니다.')
    //       router.push({ name: 'MainPage' })
    //     })
    //     .catch((err) => {
    //       console.log(err)
    //     })
    // }

    
  
    return { logIn }},
     {persist: true})