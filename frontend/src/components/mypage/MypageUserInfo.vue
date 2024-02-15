<script setup>
import { ref, onBeforeMount } from 'vue'
import { useRouter } from 'vue-router'
import { useAccountStore } from '@/stores/account'
import { storeToRefs } from 'pinia'

const router = useRouter()
const accountstore = useAccountStore()
const { userdata, mypetlist } = storeToRefs(accountstore)
const opendialog = ref(false)
const showeditpet = ref(false)

onBeforeMount(() => {
  const userId = localStorage.getItem('useremail')
  accountstore.getnormalprofile(userId)
  accountstore.getpetlist(userId)
  //console.log(mypetlist, 'mypetlist')
  setTimeout(() => {
    init()
  }, 50)
})

function init() {
  userName.value = userdata.value.userName
  email.value = userdata.value.userId
  phoneNumber.value = userdata.value.phone
  address.value = userdata.value.address
  if (userdata.value.picture == 0) {
    picture.value = 'https://i.pinimg.com/474x/3b/73/a1/3b73a13983f88f8b84e130bb3fb29e17.jpg'
  } else {
    picture.value = userdata.value.picture
  }
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
var petfile = null
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
}
// 개인정보 수정 저장
function saveinfo() {
  isreadonly.value = true
  isclicked.value = false
  // 개인정보 변경 실행
  opendialog.value = true
}
function completeUpdate() {
  //console.log('file', file)

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
// 펫 정보 수정 사항 저장 변수
const newpetname = ref('')
const newpetspecie = ref('')
const newpetgender = ref('')
const newpetweight = ref('')
const newpetpicture = ref('')
const newpetId = ref(0)
const newpetnote = ref('')
const newpetage = ref('')

// 수정버튼 클릭 ->동물 정보 편집 textlist 보여짐
const editPetprofile = (pet) => {
  showeditpet.value = true
  newpetId.value = pet.animalId
  newpetname.value = pet.name
  newpetspecie.value = pet.specie
  newpetgender.value = pet.gender
  newpetweight.value = pet.weight
  newpetnote.value = pet.note
  newpetage.value = pet.age
}
// 펫 프로필 사진 파일 업로드
const getpetFile = function (event) {
  petfile = event.target.files[0]
  if (petfile && petfile.type.match('image.*')) {
    const reader = new FileReader()
    reader.onload = (e) => {
      newpetpicture.value = e.target.result
    }
    reader.readAsDataURL(petfile)
  } else {
    alert('선택된 파일이 이미지 형식이 아닙니다.')
  }
}
// 펫 정보 수정후 저장 버튼 클릭시 axios 요청 account.js 의 323번줄 updatePet
const savenewpetInfo = function () {
  const userId = localStorage.getItem('useremail')
  accountstore.updatePet(
    userId,
    newpetname.value,
    newpetId.value,
    petfile,
    newpetspecie.value,
    newpetage.value,
    newpetnote.value,
    newpetweight.value,
    newpetgender.value
  )
  showeditpet.value = false
  router.push({ name: 'mypage-user-info' })
}

function navigateTocurrentlist() {
  router.push({ name: 'mypage-user-list-current' })
}
</script>

<template>
  <div class="info">
    <!-- 나의 예약 일정 보기로 이동  -->
    <v-btn
      width="200px"
      style="margin-left: 250px; margin-bottom: 10px"
      @click="navigateTocurrentlist"
      >나의 예약 보기</v-btn
    >
    <!-- 유저의 프로필  -->
    <v-container
      class="profile-container"
      elevation="8"
      width="auto"
      style="margin-top: 5px; margin-bottom: 100px"
    >
      <v-row>
        <div class="profile-img">
          <v-avatar size="230" variant="inlined">
            <img :src="picture" alt="프로필 사진" style="max-width: 100%; height: auto" />
            <input
              type="file"
              @change="getFile"
              style="opacity: 0; position: absolute; width: 100%; height: 100%; cursor: pointer"
              :disabled="!isclicked"
            />
          </v-avatar>
          <v-btn v-show="isclicked == false" style="margin: 10px" @click="updateInfo"
            >개인정보수정</v-btn
          >
          <a
            class="text-caption text-decoration-none text-blue"
            href="/find/password"
            rel="noopener noreferrer"
            target="_self"
          >
            비밀번호 변경</a
          >
        </div>

        <v-col cols="12" class="text-center">
          <!-- 유저의 기본정보 -->
          <v-card class="profile-info">
            <v-text-field
              label="이름"
              v-model="userName"
              variant="solo"
              :readonly="isreadonly"
            ></v-text-field>
            <v-text-field
              v-model="email"
              label="Email"
              variant="solo"
              :readonly="isreadonly"
            ></v-text-field>
            <v-text-field
              v-model="phoneNumber"
              label="전화번호"
              variant="solo"
              :readonly="isreadonly"
            ></v-text-field>
            <div style="position: relative">
              <v-text-field
                v-model="address"
                label="주소"
                variant="solo"
                :readonly="isreadonly"
              ></v-text-field>

              <v-btn
                v-if="isclicked"
                @click="openKakaoAddressSearch"
                style="position: absolute; top: 10px; right: 0; z-index: 10"
                >주소 변경</v-btn
              >
            </div>

            <v-btn v-if="isclicked" style="margin-bottom: 10px" @click="saveinfo">저장</v-btn>
          </v-card>
        </v-col>
      </v-row>

      <!-- 유저 펫리스트 -->
      <v-sheet
        class="mx-auto"
        elevation="2"
        width="400px"
        height="350px"
        style="background-color: #ffebeb"
      >
        <v-slide-group class="pa-4" show-arrows>
          <v-slide-group-item v-for="pet in mypetlist" :key="pet.id">
            <v-card width="265px" style="background-color: #ffebeb; max-width: 450px">
              <v-row style="margin-top: 5px">
                <v-col cols="4">
                  <v-avatar class="pet-img" size="120" variant="inlined">
                    <img
                      :src="pet.picture"
                      alt="펫 프로필 사진"
                      style="max-width: 100%; height: auto"
                    />
                  </v-avatar>
                </v-col>
                <v-col cols="6" class="petinfo">
                  <div style="text-align: center">
                    {{ pet.name }}<br />
                    {{ pet.age }}<br />
                    {{ pet.specie }}<br />
                    {{ pet.gender }}<br />
                    {{ pet.weight }}<br />
                    {{ pet.note }}
                  </div>

                  <v-btn @click="editPetprofile(pet)">수정</v-btn>
                </v-col>
              </v-row>
            </v-card>
          </v-slide-group-item>
        </v-slide-group>

        <!-- 펫 수정 폼  -->
        <v-expand-transition v-if="showeditpet" style="height: auto">
          <v-sheet
            width="300px"
            height="auto"
            class="d-flex flex-column align-center justify-center mx-auto"
          >
            <v-file-input
              v-model="newpetpicture"
              label="프로필 사진 업로드"
              prepend-icon="mdi-camera"
              @change="getpetFile"
              style="width: 100%"
              variant="underlined"
            ></v-file-input>
            <v-text-field variant="underlined" v-model="newpetname" label="이름"></v-text-field>
            <v-text-field variant="underlined" v-model="newpetage" label="나이"></v-text-field>
            <v-text-field variant="underlined" v-model="newpetspecie" label="종"></v-text-field>
            <v-text-field variant="underlined" v-model="newpetgender" label="성별"></v-text-field>
            <v-text-field variant="underlined" v-model="newpetweight" label="무게"></v-text-field>
            <v-text-field variant="underlined" v-model="newpetnote" label="소개"></v-text-field>
            <v-btn @click="savenewpetInfo">저장</v-btn>
            <!-- <div class="d-flex fill-height align-center justify-center"> -->
            <!-- </div> -->
          </v-sheet>
        </v-expand-transition>
      </v-sheet>
    </v-container>
  </div>

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
.info {
  height: auto;
  justify-content: left;
  flex-direction: column;
  display: flex;
  margin-bottom: 100px;
  margin-top: 100px;
}
.slide-item {
  width: 300px;
}
.profile-container {
  /* max-width: 900px; */
  margin: 200px auto;
  background-color: #eef5ff;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  padding: 20px;
  display: flex;
  gap: 20px;
  margin: 0 auto;
}
.profile-img {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 500px;
  margin-bottom: 10px;
}
.v-btn {
  background-color: #ffd0d0;
}
.profile-info {
  width: 500px;
  margin-left: 0;
  background-color: #eef5ff;
  box-shadow: none;
  text-align: center;
}
.v-text-field {
  margin-bottom: 10px;
  width: 100%;
}
.petcard {
  width: 20%;
}
.v-expand-transition {
  border: black;
  display: flex;
}
:deep(.v-field--variant-solo, .v-field--variant-solo-filled) {
  background-color: #ffebeb;
  box-shadow: none;
}
.petinfo {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
</style>
