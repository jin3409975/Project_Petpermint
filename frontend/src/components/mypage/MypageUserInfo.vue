<script setup>
import { ref, onMounted, onBeforeMount } from 'vue'
import { useRouter } from 'vue-router'
import { useAccountStore } from '@/stores/account'
import { storeToRefs } from 'pinia'

const accountstore = useAccountStore()
// const router = useRouter()
const { userdata } = storeToRefs(accountstore)
const opendialog = ref(false)
const data = ref()
onBeforeMount(() => {
  const userId = localStorage.getItem('useremail')
  accountstore.getnormalprofile(userId)
  setTimeout(() => {
    init()
  }, 1)
})

function init() {
  console.log('pa1515919159119159ge', userdata)
  userName.value = userdata.value.userName
  email.value = userdata.value.userId
  phoneNumber.value = userdata.value.phone
  address.value = userdata.value.address
  picture.value = userdata.value.picture
  console.log('llllll', userdata.value)
}
// 유저 프로필 변수
const userName = ref(userdata.value.userName)
const email = ref('')
const password = ref('')
const phoneNumber = ref('')
const address = ref('')
const picture = ref('')
// 폼 출력 여부 변수
const isreadonly = ref(true)
const isclicked = ref(false)
var file = null
const isfiled = false
// 프로필 사진 파일 업로드
const getFile = function (event) {
  file = event.target.files[0]
  if (file && file.type.match('image.*')) {
    const reader = new FileReader()
    reader.onload = (e) => {
      picture.value = e.target.result
    }
    reader.readAsDataURL(file)
  } else {
    alert('선택된 파일이 이미지 형식이 아닙니다.')
  }
}

// 카카오 주소 검색
function openKakaoAddressSearch() {
  new window.daum.Postcode({
    oncomplete: (data) => {
      if (address.value !== '') {
        address.value = ''
      }
      if (data.userSelectedType === 'R') {
        // 사용자가 도로명 주소를 선택했을 경우
        address.value = data.roadAddress
      } else {
        // 사용자가 지번 주소를 선택했을 경우(J)
        address.value = data.jibunAddress
      }
      address.value
    }
  }).open()
}
function updateInfo() {
  isreadonly.value = false
  isclicked.value = true
  console.log(userdata.value)
}
// 개인정보 수정 저장
function saveinfo() {
  isreadonly.value = true
  isclicked.value = false
  // 개인정보 변경 실행
  opendialog.value = true
}
function completeUpdate() {
  console.log('file', file)

  accountstore.updateNormal(
    email.value,
    userName.value,
    password.value,
    file,
    address.value,
    phoneNumber.value
  )
  opendialog.value = false
}
</script>

<template>
  <!-- 유저의 프로필  -->
  <v-container class="profile-container">
    <!-- 유저의 프로필 이미지 -->
    <v-card class="profile-img">
      <v-avatar size="230" variant="outlined">
        <img :src="picture" alt="프로필 사진" style="max-width: 100%; height: auto" />
        <input
          type="file"
          @change="getFile"
          style="opacity: 0; position: absolute; width: 100%; height: 100%; cursor: pointer"
        />
      </v-avatar>
    </v-card>
    <!-- 유저의 기본정보 -->
    <v-card class="profile-info">
      <v-text-field label="이름" v-model="userName" variant="solo" readonly></v-text-field>
      <v-text-field v-model="email" label="Email" variant="solo" readonly></v-text-field>
      <v-text-field v-model="phoneNumber" label="전화번호" variant="solo" readonly></v-text-field>
      <v-text-field
        v-model="address"
        label="주소"
        variant="solo"
        :readonly="isreadonly"
      ></v-text-field
      ><v-btn v-if="isclicked" @click="openKakaoAddressSearch">주소 변경</v-btn>
      <v-card v-if="isclicked">
        <a
          class="text-caption text-decoration-none text-blue"
          href="/find/password"
          rel="noopener noreferrer"
          target="_self"
        >
          비밀번호 변경</a
        >

        <v-btn @click="saveinfo">저장</v-btn>
      </v-card>
      <v-btn v-show="isclicked == false" @click="updateInfo">수정</v-btn>
    </v-card>
  </v-container>

  <v-container class="profile-container">
    <!-- <div v-for="pet in petlist">
      <v-card class="petcard"> </v-card>
    </div> -->
  </v-container>

  <v-dialog v-model="opendialog" max-width="600px">
    <v-card>
      <v-card-title> 비밀번호 입력 </v-card-title>
      <v-text-field v-model="password" label="비밀번호" variant="solo"></v-text-field>
      <v-card-actions>
        <v-btn color="primary" block @click="completeUpdate">비밀번호확인</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<style scoped>
.profile-container {
  /* max-width: 900px; */
  margin: 15px auto;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  padding: 20px;
  display: flex;
  gap: 20px;
}
.profile-img {
  flex: 0.3;
  height: 250px;
}
.profile-info {
  flex: 0.7; /* Ensure both cards share the available space equally */
}
.v-text-field {
  margin-bottom: 10px;
  width: 85%;
}
.petcard {
  width: 20%;
}
</style>
