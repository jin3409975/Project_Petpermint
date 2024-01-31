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
    const result = ref(false)
    const vetdata = ref({})

    const usersignup = function (userData) {
      console.log(userData)
      var data = new FormData()
      data.append('userId', userData.email)
      data.append('userName', userData.name)
      data.append('password', userData.password1)
      data.append('address', userData.address1)
      data.append('phone', userData.phone)
      data.append('picture', userData.picture)
      // var userId = userData.email
      // var userName = userData.name
      // var password = userData.password1
      // var address = userData.address1
      // var phone = userData.phone
      axios({
        method: 'post',
        url: API_URL + 'signup/normal',
        data,
        headers: {
          'Content-Type': 'multipart/formdata'
        }
      }).then((r) => {
        console.log(r)
        if (r.data.statusCode == 200) {
          result.value = false
          router.push({ name: 'register-complete' })
        }
      })
    }

    const vetsignup = function (userData) {
      console.log(userData)
      var data = new FormData()
      data.append('userId', userData.email)
      data.append('userName', userData.name)
      data.append('password', userData.password1)
      data.append('phone', userData.phone)
      data.append('address', userData.address)
      data.append('picture', userData.picture)
      data.append('licenseNumber', userData.license)
      data.append('hospitalName', userData.work)
      data.append('licensePicture', userData.licensePicture)
      // var userId = userData.email
      // var userName = userData.name
      // var password = userData.password1
      // var address = userData.address1
      // var phone = userData.phone
      // var licenseNumber = userData.licenseNumber
      // var hospitalName = userData.work
      console.log(data)
      axios({
        method: 'post',
        url: API_URL + 'signup/expert',
        data,
        headers: {
          'Content-Type': 'multipart/formdata'
        }
      }).then((r) => {
        console.log(r)
        if (r.data.statusCode == 200) {
          result.value = false
          router.push({ name: 'register-complete' })
        }
      })
    }

    const emailRequest = function (email) {
      axios({
        method: 'post',
        url: API_URL + 'email/request',
        data: {
          userId: email
        }
      }).then((r) => {
        console.log(r)
        if (r.data.statusCode == 200) {
          alert('이메일 인증 요청이 완료되었습니다. 5분 이내에 입력해 주세요.')
        } else {
          alert('이메일 인증 요청이 실패 했습니다. 입력한 이메일 주소를 확인해 주세요.')
        }
      })
    }

    const emailValidate = function (email, code) {
      console.log(email, code)
      axios({
        method: 'post',
        url: API_URL + 'email/validate',
        data: {
          userId: email,
          verificationCode: code
        }
      }).then((r) => {
        console.log(r)
        if (r.data.statusCode == 200) {
          alert('이메일 인증에 성공 하셨습니다.')
          result.value = true
        } else {
          alert('이메일 인증에 실패 하셨습니다.')
          result.value = false
        }
      })
    }

    return { usersignup, vetsignup, result, emailRequest, emailValidate }
  },
  { persist: true }
)
