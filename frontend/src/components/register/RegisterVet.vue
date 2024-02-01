<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useAccountStore } from '@/stores/account.js'
// import Complete from '@/components/register/Complete.vue'

const store = useAccountStore()
const router = useRouter()

const email = ref(null)
const confirm = ref(null)
const password1 = ref(null)
const password2 = ref(null)
const name = ref(null)
// const identification = ref(null)
const phone = ref(null)
const license = ref(null)
// const copy = ref(null)
// const university = ref(null)
// const graduate = ref(null)
const work = ref(null)
// const address = ref(null)
const profile = ref(null)
const licensePicture = ref(null)
const emailcheck = ref(false)
const address = ref(null)

const getProfile = function (e) {
  profile.value = e.target.files[0]
}

const getLicense = function (e) {
  licensePicture.value = e.target.files[0]
}

const snackbar = ref(false)

const signUp = function () {
  console.log('asdasdsd',emailcheck.value)
  if(!store.result) {
    alert('이메일 인증을 진행 해주세요')
    return false
  }
  const payload = {
    email: email.value,
    confirm: confirm.value,
    password1: password1.value,
    password2: password2.value,
    address: address.value,
    name: name.value,
    phone: phone.value,
    license: license.value,
    work: work.value,
    picture : profile.value,
    licensePicture : licensePicture.value
  }
  store.vetsignup(payload)
  snackbar.value = true // 회원가입 완료 시 스낵바 표시

  // 스낵바가 표시된 후 일정 시간이 지난 후 페이지 이동
  setTimeout(() => {
    router.push({ name: 'main-home' })
  }, 3000) // 예: 3초 후 이동
}


const emailRequest = function () {
  console.log('이메일 인증 요청', emailcheck.value)
  store.emailRequest(email.value)
  console.log('결과',store.result)
}

const emailValidate = function () {
  console.log('이메일 확인 요청', emailcheck.value)
  emailcheck.value = store.emailValidate(email.value, confirm.value)
  console.log('결과',store.result)
}

const emailRules = ref([
  (v) => !!v || '이메일은 필수입니다.',
  (v) => (v && v.length >= 5) || '아이디는 최소 5자 이상이어야 합니다.'
])
const confirmRules = ref([
  (v) => !!v || '이메일 확인은 필수입니다.',
  (v) => (v && v.length == 5) || '인증번호를 다시 입력해 주세요.'
])
const password1Rules = ref([
  (v) => !!v || '비밀번호는 필수입니다.',
  (v) => (v && v.length >= 5) || '비밀번호는 최소 5자 이상이어야 합니다.'
])
const password2Rules = ref([
  (v) => !!v || '비밀번호 확인은 필수입니다.',
  (v) => v === password1.value || '비밀번호가 일치하지 않습니다.'
])
const nameRules = ref([(v) => !!v || '이름은 필수입니다.'])
const identificationRules = ref([
  (v) => !!v || '주민등록번호는 필수입니다.',
  (v) => (v && v.length == 6) || '주민등록번호를 다시 입력해 주세요.'
])
const phoneRules = ref([
  (v) => !!v || '핸드폰번호는 필수입니다.',
  (v) => (v && v.length == 13) || '010-0000-0000 형식으로 입력해주세요'
])
const licenseRules = ref([
  (v) => !!v || '수의사면호번호는 필수입니다. 수의사 인증에 필요하므로 정확히 입력해주세요.',
  (v) => (v && v.length == 5) || '면호번호를 다시 입력해 주세요.'
])
const copyRules = ref([
  (v) => !!v || '면허증 사본은 필수입니다. 수의사 인증에 필요하므로 정확히 입력해주세요.'
])
const universityRules = ref([
  (v) => !!v || '출신대학은 필수입니다. 수의사 인증에 필요하므로 정확히 입력해주세요.'
])
const graduateRules = ref([
  (v) => !!v || '출신연도는 필수입니다. 수의사 인증에 필요하므로 정확히 입력해주세요.'
])
const workRules = ref([
  (v) => !!v || '직장명은 필수입니다. 수의사 인증에 필요하므로 정확히 입력해주세요.',
  (v) => (v && v.length == 4) || '숫자 4자리를 입력해주세요. 예) 2024'
])
const address1Rules = ref([(v) => !!v || '자택 주소는 필수입니다.'])

// 카카오 주소 검색
function openKakaoAddressSearch() {
  new window.daum.Postcode({
    oncomplete: (data) => {
      // if (address2.value !== '') {
      //   address2.value = ''
      // }
      if (data.userSelectedType === 'R') {
        // 사용자가 도로명 주소를 선택했을 경우
        address.value = data.roadAddress
      } else {
        // 사용자가 지번 주소를 선택했을 경우(J)
        address.value = data.jibunAddress
      }

      // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
      // if (data.userSelectedType === 'R') {
      //   // 법정동명이 있을 경우 추가한다. (법정리는 제외)
      //   // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
      //   if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
      //     address2.value += data.bname
      //   }
      //   // 건물명이 있고, 공동주택일 경우 추가한다.
      //   if (data.buildingName !== '' && data.apartment === 'Y') {
      //     address2.value += address2.value !== '' ? `, ${data.buildingName}` : data.buildingName
      //   }
      //   // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
      //   // if (address2.value !== '') {
      //   //   address2.value = `(${address2.value})`
      //   // }
      // } else {
      //   address2.value = ''
      // }
      // 우편번호를 입력한다.
      // this.postcode = data.zonecode
    }
  }).open()
}

</script>

<template>
  <div class="sign-up-container">
    <h1 class="title">수의사용 회원가입</h1>
    <!-- <form v-if="showSignUpForm" @submit.prevent="signUp"> -->
    <form @submit.prevent="signUp" class="sign-up-form">
      <div>
        <v-text-field
          label="이메일 *"
          v-model="email"
          :rules="emailRules"
          hide-details="auto"
        ></v-text-field>
        <v-btn @click="emailRequest()">인증 요청</v-btn>
        <v-text-field
          label="인증번호 숫자 6자리 입력  *"
          v-model="confirm"
          :rules="confirmRules"
          hide-details="auto"
        ></v-text-field>
        <v-btn @click="emailValidate()">인증 확인</v-btn>
        <v-text-field
          label="비밀번호 *"
          v-model="password1"
          :rules="password1Rules"
          hide-details="auto"
        ></v-text-field>
        <v-text-field
          label="비밀번호 확인 *"
          v-model="password2"
          :rules="password2Rules"
          hide-details="auto"
        ></v-text-field>
        <v-text-field
          label="이름 *"
          v-model="name"
          :rules="nameRules"
          hide-details="auto"
        ></v-text-field>
        <!-- <v-text-field
          label="주민등록번호 (앞 6자리) *"
          v-model="identification"
          :rules="identificationRules"
          hide-details="auto"
        ></v-text-field> -->
        <v-text-field
          label="핸드폰번호 *"
          v-model="phone"
          :rules="phoneRules"
          hide-details="auto"
        ></v-text-field>
        <v-text-field
          label="주소 *"
          v-model="address"
          :rules="address1Rules"
          hide-details="auto"
          @click="openKakaoAddressSearch"
        ></v-text-field>
        <v-text-field
          label="수의사면허번호 *"
          v-model="license"
          :rules="licenseRules"
          hide-details="auto"
        ></v-text-field>
        <!-- <v-text-field
          label="면허증 사본 *"
          v-model="copy"
          :rules="copyRules"
          hide-details="auto"
        ></v-text-field>
        <v-text-field
          label="출신대학 *"
          v-model="university"
          :rules="universityRules"
          hide-details="auto"
        ></v-text-field>
        <v-text-field
          label="졸업연도 *"
          v-model="graduate"
          :rules="graduateRules"
          hide-details="auto"
        ></v-text-field> -->
        <v-text-field
          label="직장명 *"
          v-model="work"
          :rules="workRules"
          hide-details="auto"
        ></v-text-field>
        <!-- <v-text-field label="직장주소" hide-details="auto"></v-text-field> -->
        <!-- <v-text-field label="나머지 주소" v-model="address" hide-details="auto"></v-text-field> -->
        <input type="file" v-on:change="getProfile">프로필 사진 업로드</input>
        <input type="file" v-on:change="getLicense">면허증 사진 업로드</input>
      </div>
      <div class="button-container">
        <v-btn type="submit" color="indigo" @click="signUp">회원가입</v-btn>

        <v-snackbar v-model="snackbar" :timeout="3000" vertical>
          <div class="text-subtitle-1 pb-2">회원가입이 완료되었습니다.</div>

          <p>인증과정에 1~2일 가량 소요될 수 있습니다.</p>

          <p>홈페이지로 이동합니다.</p>

          <template v-slot:actions> </template>
        </v-snackbar>
      </div>
    </form>
  </div>
  <!-- `showSignUpComplete`이 true이면 회원가입 완료 화면을 표시합니다. -->
  <!-- <Complete v-if="showSignUpComplete" /> -->
</template>

<style>
.sign-up-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 20px;
}

.title {
  margin-bottom: 30px; /* h1과 form 사이의 간격 */
}

.sign-up-form {
  width: 100%; /* 폼의 너비 */
  max-width: 500px; /* 최대 폼 너비 */
  margin-bottom: 30px; /* form과 버튼 사이의 간격 */
}

.submit-btn {
  margin-top: 20px; /* 버튼 상단 간격 */
}

.button-container {
  display: flex;
  justify-content: center;
  margin-top: 10px; /* 버튼 상단 간격 */
}
</style>
