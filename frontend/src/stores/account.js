import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import { useRouter } from 'vue-router'
import axios from 'axios'

export const useAccountStore = defineStore(
  'account',
  () => {
    const router = useRouter()
    const API_URL = 'http://localhost:8080/user/'
    // const token = ref(null)
    const userdata = ref({})
    const result = ref({})
    const vetdata = ref({})

    const usersignup = function (userData) {
      var userId = userData.email
      var userName = userData.name
      var password = userData.password1
      var address = userData.address1
      var phone = userData.phone
      axios({
        method: 'post',
        url: API_URL + 'signup/normal',
        data: {
          userId: userId,
          userName: userName,
          password: password,
          address: address,
          phone: phone,
          type: 1
        }
      }).then((r) => {
        console.log(r)
        if (r.data.statusCode == 200) {
          router.push({ name: 'register-complete' })
        }
      })
      //console.log(result)
    }
    const vetsignup = function (userData) {
      var userId = userData.email
      var userName = userData.name
      var password = userData.password1
      // var address = userData.address1
      var phone = userData.phone
      var licenseNumber = userData.licenseNumber
      var hospitalName = userData.work
      axios({
        method: 'post',
        url: API_URL + 'signup/normal',
        data: {
          userId: userId,
          userName: userName,
          password: password,
          phone: phone,
          type: 2,
          licenseNumber: licenseNumber,
          hospitalName: hospitalName
        }
      }).then((r) => {
        console.log(r)
        if (r.data.statusCode == 200) {
          router.push({ name: 'register-complete' })
        }
      })
      //console.log(result)
    }

    return { usersignup, vetsignup, result }
  },
  { persist: true }
)
