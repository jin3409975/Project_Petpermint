import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import { useRouter } from 'vue-router'

import axios from 'axios'
const { VITE_APP_SERVER_URI } = import.meta.env

export const useAccountStore = defineStore(
  'account',
  () => {
    const router = useRouter()
    const API_URL = `${VITE_APP_SERVER_URI}/user/`
    // const token = ref(null)
    const userdata = ref([])
    const result = ref(false)
    const vetdata = ref([])
    //로그인 여부 확인 변수
    const isLoggedIn = ref(false)
    const isUpdated = ref(false)
    const usersignup = async function (userData) {
      console.log(userData)
      var data = new FormData()
      data.append('userId', userData.email)
      data.append('userName', userData.name)
      data.append('password', userData.password)
      data.append('address', userData.address)
      data.append('phone', userData.phone)
      // var userId = userData.email
      // var userName = userData.name
      // var password = userData.password1
      // var address = userData.address1
      // var phone = userData.phone
      let result = await axios({
        method: 'post',
        url: API_URL + 'signup/normal',
        data,
        headers: {
          'Content-Type': 'multipart/formdata'
        }
      }).then((r) => {
        console.log(r)
        if (r.data.statusCode == 200) {
          return true
        }
      })
      return result
    }

    const vetsignup = async function (userData) {
      console.log(userData)
      var data = new FormData()
      data.append('userId', userData.email)
      data.append('userName', userData.name)
      data.append('password', userData.password)
      data.append('phone', userData.phone)
      data.append('address', userData.address)
      //data.append('picture', userData.picture)
      data.append('licenseNumber', userData.license)
      data.append('hospitalName', userData.hospitalname)
      data.append('startTime', userData.start)
      data.append('endTime', userData.end)
      data.append('note', userData.note)
      //data.append('licensePicture', userData.licensePicture)
      // var userId = userData.email
      // var userName = userData.name
      // var password = userData.password1
      // var address = userData.address1
      // var phone = userData.phone
      // var licenseNumber = userData.licenseNumber
      // var hospitalName = userData.work
      console.log(data)
      let result = await axios({
        method: 'post',
        url: API_URL + 'signup/expert',
        data,
        headers: {
          'Content-Type': 'multipart/formdata'
        }
      }).then((r) => {
        console.log(r)
        if (r.data.statusCode == 200) {
          return true
        } else {
          return false
        }
      })
      return result
    }

    const emailRequest = async function (email) {
      let emailcheck = await axios({
        method: 'post',
        url: API_URL + 'email/request',
        data: {
          userId: email
        }
      }).then((r) => {
        console.log(r)
        if (r.data.statusCode == 200) {
          alert('이메일 인증 요청이 완료되었습니다. 5분 이내에 입력해 주세요.')
          return true
        } else {
          alert('이메일 인증 요청이 실패 했습니다. 입력한 이메일 주소를 확인해 주세요.')
          return false
        }
      })
      return emailcheck
    }

    const emailValidate = async function (email, code) {
      console.log(email, code)
      let emailcheck = await axios({
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
          console.log(result, 'result 값 확인')
          return true
        } else {
          alert('이메일 인증에 실패 하셨습니다.')
          return false
        }
      })
      return emailcheck
    }
    const logIn = function (userdata) {
      console.log('logindatacheck', userdata)
      axios({
        method: 'post',
        url: API_URL + 'login',
        data: {
          userId: userdata.useremail,
          password: userdata.password
        }
      }).then((r) => {
        if (r.data.statusCode == 200) {
          console.log('success login', r)
          localStorage.setItem('useremail', r.data.userId)
          localStorage.setItem('usertype', r.data.type)
          localStorage.setItem('token', r.data.accessToken)
          if (r.data.type == 2) {
            console.log('type', r.data.type)
            localStorage.setItem('licenseNumber', r.data.licenseNumber)
          }
        } else {
          console.log('failed login', r)
        }
      })
    }
    const findId = function (username, phone) {
      console.log('아이디찾기 실행', username, phone)
      axios({
        method: 'post',
        url: API_URL + 'recover/id',
        data: {
          userName: username,
          phone: phone
        }
      }).then((r) => {
        if (r.data.statusCode == 200) {
          console.log('success findID', r)
          alert(`당신의 ID는 ${r.data.userId} 입니다.`)
        } else {
          console.log('failed login', r)
          alert('아이디 찾기에 실패 했습니다.')
        }
      })
    }
    const updatePassword = function (userId, userName, password) {
      console.log('비밀번호 수정 실행')
      axios({
        method: 'post',
        url: API_URL + 'recover/password',
        data: {
          userId: userId,
          userName: userName,
          password: password
        }
      }).then((r) => {
        if (r.data.statusCode == 200) {
          console.log('success update password', r)
          alert(`당신의 패스워드가 변경 되었습니다.`)
        } else {
          console.log('failed update password', r)
          alert('패스워드 변경에 실패 했습니다.')
        }
      })
    }
    const logout = function () {
      localStorage.removeItem('useremail')
      localStorage.removeItem('usertype')
      localStorage.removeItem('token')
    }
    // 일반 유저 프로필 정보 불러오기
    const getnormalprofile = function (userId) {
      axios({
        method: 'get',
        url: API_URL + 'me/normal',
        params: {
          userId: userId
        }
      }).then((r) => {
        if (r.data.statusCode == 200) {
          console.log('success get userinfo', r)
          userdata.value = r.data
        } else {
          console.log('failed get userinfo', r)
          alert('유저 정보를 불러오는데 실패 했습니다.')
        }
      })
    }
    const updateNormal = function (userId, userName, password, picture, address, phone) {
      var data = new FormData()
      console.log(picture)
      data.append('userId', userId)
      data.append('userName', userName)
      data.append('password', password)
      if (picture != null) {
        data.append('picture', picture)
      }
      data.append('address', address)
      data.append('phone', phone)
      axios({
        method: 'put',
        url: API_URL + 'update/normal',
        data,
        headers: {
          'Content-Type': 'multipart/formdata'
        }
      }).then((r) => {
        if (r.data.statusCode == 200) {
          console.log('success update userinfo', r)
          isUpdated.value = true
        } else {
          console.log('failed update password', r)
          alert('비밀번호가 올바른지 확인해 보세요')
        }
      })
    }
    const getexpertprofile = function (userId) {
      axios({
        method: 'get',
        url: API_URL + 'me/expert',
        params: {
          userId: userId
        }
      }).then((r) => {
        if (r.data.statusCode == 200) {
          console.log('success get vet info', r.data)
          vetdata.value = r.data
        } else {
          console.log('failed get userinfo', r)
          alert('유저 정보를 불러오는데 실패 했습니다.')
        }
      })
    }
    return {
      usersignup,
      vetsignup,
      result,
      emailRequest,
      emailValidate,
      logIn,
      findId,
      updatePassword,
      logout,
      isLoggedIn,
      updateNormal,
      getnormalprofile,
      userdata,
      isUpdated,
      getexpertprofile,
      vetdata
    }
  },
  { persist: true }
)
