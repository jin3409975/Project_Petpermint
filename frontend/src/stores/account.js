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
    const mypetlist = ref([])

    const usersignup = async function (userData, pets) {
      //console.log('pets', pets)
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
        //console.log(r)
        if (r.data.statusCode == 200) {
          return true
        }
      })

      for (let i = 0; i < pets.length; i++) {
        data = new FormData()
        data.append('name', pets[i].name)
        //console.log(pets[i].name)
        data.append('picture', pets[i].picture)
        //console.log(pets[i].picture)
        data.append('specie', pets[i].petspecies)
        //console.log(pets[i].petspecies)
        data.append('age', pets[i].petage)
        //console.log(pets[i].petage)
        data.append('note', pets[i].more)
        //console.log(pets[i].more)
        data.append('weight', pets[i].petweight)
        //console.log(pets[i].petweight)
        data.append('gender', pets[i].gender)
        //console.log(pets[i].gender)
        data.append('userId', userData.email)
        //console.log(userData.email)
        axios({
          method: 'post',
          url: API_URL + 'pet/create',
          data,
          headers: {
            'Content-Type': 'multipart/formdata'
          }
        }).then((r) => {}) //console.log(r))
      }
      return result
    }

    const vetsignup = async function (userData) {
      //console.log(userData)
      var data = new FormData()
      data.append('userId', userData.email)
      data.append('userName', userData.name)
      data.append('password', userData.password)
      data.append('phone', userData.phone)
      data.append('address', userData.address)
      data.append('licenseNumber', userData.license)
      data.append('hospitalName', userData.hospitalname)
      data.append('startTime', userData.start)
      data.append('endTime', userData.end)
      data.append('note', userData.note)

      let result = await axios({
        method: 'post',
        url: API_URL + 'signup/expert',
        data,
        headers: {
          'Content-Type': 'multipart/formdata'
        }
      }).then((r) => {
        //console.log(r)
        if (r.data.statusCode == 200) {
          return true
        } else {
          return false
        }
      })
      return result
    }

    const emailRequest = async function (email, type) {
      var url = ''
      if (type == 'regist') {
        url = API_URL + 'email/request/regist'
      } else {
        url = API_URL + 'email/request/find'
      }
      let emailcheck = await axios({
        method: 'post',
        url: url,
        data: {
          userId: email
        }
      }).then((r) => {
        //console.log(r)
        if (r.data.message == 'Success') {
          alert('이메일 인증 요청이 완료되었습니다. 5분 이내에 입력해 주세요.')
          return true
        } else if (r.data.message == 'Duplication') {
          alert('이미 가입된 이메일 주소 입니다.')
          return false
        } else if (r.data.message == 'Not Found') {
          alert('회원 정보를 찾을 수 없습니다.')
          return false
        } else {
          alert('이메일 인증 요청이 실패 했습니다. 입력한 이메일 주소를 확인해 주세요.')
          return false
        }
      })
      return emailcheck
    }

    const emailValidate = async function (email, code) {
      //console.log(email, code)
      let emailcheck = await axios({
        method: 'post',
        url: API_URL + 'email/validate',
        data: {
          userId: email,
          verificationCode: code
        }
      })
        .then((r) => {
          //console.log(r)
          if (r.data.statusCode == 200) {
            alert('이메일 인증에 성공 하셨습니다.')
            // //console.log(result, 'result 값 확인')
            return true
          } else {
            alert('이메일 인증에 실패 하셨습니다.')
            return false
          }
        })
        .catch((error) => {
          alert('이메일 인증에 실패 하셨습니다.')
          return false
        })
      return emailcheck
    }

    const loginStatus = ref(localStorage.getItem('useremail') != null ? true : false)
    //const loginStatus = ref(false)
    const logIn = function (userdata) {
      //console.log('logindatacheck', userdata)
      axios({
        method: 'post',
        url: API_URL + 'login',
        data: {
          userId: userdata.useremail,
          password: userdata.password
        }
      })
        .then((r) => {
          if (r.data.statusCode == 200) {
            // //console.log('success login', r)
            loginStatus.value = true
            localStorage.setItem('useremail', r.data.userId)
            localStorage.setItem('usertype', r.data.type)
            localStorage.setItem('token', r.data.accessToken)
            if (r.data.type == 2) {
              // //console.log('type', r.data.type)
              localStorage.setItem('licenseNumber', r.data.licenseNumber)
              router.push({ name: 'main-vet', replace: true })
            } else {
              router.push({ name: 'main-home', replace: true })
            }
          } else {
            // //console.log('failed login', r)
            alert('로그인에 실패 했습니다.')
          }
        })
        .catch((e) => {
          //console.log(e)
        })
    }
    const findId = function (username, phone) {
      //console.log('아이디찾기 실행', username, phone)
      axios({
        method: 'post',
        url: API_URL + 'recover/id',
        data: {
          userName: username,
          phone: phone
        }
      }).then((r) => {
        if (r.data.statusCode == 200) {
          // //console.log('success findID', r)
          alert(`당신의 ID는 ${r.data.userId} 입니다.`)
        } else {
          // //console.log('failed login', r)
          alert('아이디 찾기에 실패 했습니다.')
        }
      })
    }
    const updatePassword = function (userId, userName, password) {
      //console.log('비밀번호 수정 실행')
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
          // //console.log('success update password', r)
          alert(`당신의 패스워드가 변경 되었습니다.`)
        } else {
          // //console.log('failed update password', r)
          alert('패스워드 변경에 실패 했습니다.')
        }
      })
    }
    const logout = function () {
      localStorage.removeItem('useremail')
      localStorage.removeItem('usertype')
      localStorage.removeItem('token')
      localStorage.removeItem('licenseNumber')
      loginStatus.value = false
    }
    // 일반 유저 프로필 정보 불러오기

    const getnormalprofile = function (userId) {
      return new Promise((resolve, reject) => {
        axios({
          method: 'get',
          url: API_URL + 'me/normal',
          params: {
            userId: userId
          }
        })
          .then((response) => {
            if (response.data.statusCode === 200) {
              // //console.log('success get userinfo', response)
              resolve(response.data)
              userdata.value = response.data
            } else {
              // //console.log('failed get userinfo', response)
              reject(new Error('유저 정보를 불러오는데 실패 했습니다.'))
            }
          })
          .catch((error) => {
            // console.error('Error in getnormalprofile:', error)
            reject(error)
          })
      })
    }
    const updateNormal = function (userId, userName, password, picture, address, phone) {
      var data = new FormData()
      //console.log(picture)
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
          // //console.log('success update userinfo', r)
          isUpdated.value = true
        } else {
          // //console.log('failed update password', r)
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
          // //console.log('success get vet info', r.data)
          vetdata.value = r.data
        } else {
          // //console.log('failed get userinfo', r)
          alert('유저 정보를 불러오는데 실패 했습니다.')
        }
      })
    }
    const updateExpert = function (
      userId,
      userName,
      password,
      picture,
      address,
      phone,
      note,
      starttime,
      endtime
    ) {
      var data = new FormData()
      // //console.log(picture)
      // //console.log('startend', starttime, endtime)
      data.append('userId', userId)
      data.append('userName', userName)
      data.append('password', password)
      if (picture != null) {
        data.append('picture', picture)
      }
      data.append('address', address)
      data.append('phone', phone)
      data.append('note', note)
      data.append('startTime', starttime)
      data.append('endTime', endtime)
      axios({
        method: 'put',
        url: API_URL + 'update/expert',
        data,
        headers: {
          'Content-Type': 'multipart/formdata'
        }
      }).then((r) => {
        if (r.data.statusCode == 200) {
          // //console.log('success update expertinfo', r)
          isUpdated.value = true
        } else {
          // //console.log('failed update password', r)
          alert('비밀번호가 올바른지 확인해 보세요')
        }
      })
    }
    const getpetlist = function (userId) {
      axios({
        method: 'get',
        url: API_URL + 'pet/data',
        params: {
          userId: userId
        }
      }).then((r) => {
        if (r.data.statusCode == 200) {
          // //console.log('success get pet list info', r.data)
          mypetlist.value = r.data.result
        } else {
          // //console.log('failed get pet list info', r)
          alert('유저 정보를 불러오는데 실패 했습니다.')
        }
      })
    }
    const updatePet = function (
      userId,
      petname,
      animalId,
      picture,
      specie,
      age,
      note,
      weight,
      gender
    ) {
      var data = new FormData()
      data.append('userId', userId)
      data.append('name', petname)
      data.append('animalId', animalId)
      if (picture != null) {
        data.append('picture', picture)
      }
      data.append('specie', specie)
      data.append('age', age)
      data.append('note', note)
      data.append('weight', weight)
      data.append('gender', gender)
      axios({
        method: 'put',
        url: API_URL + 'pet/update',
        data,
        headers: {
          'Content-Type': 'multipart/formdata'
        }
      }).then((r) => {
        if (r.data.statusCode == 200) {
          // //console.log('success update petinfo', r)
        } else {
          // //console.log('failed update password', r)
          alert('비밀번호가 올바른지 확인해 보세요')
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
      loginStatus,
      updateNormal,
      getnormalprofile,
      userdata,
      isUpdated,
      getexpertprofile,
      vetdata,
      updateExpert,
      getpetlist,
      mypetlist,
      updatePet
    }
  },
  { persist: true }
)
