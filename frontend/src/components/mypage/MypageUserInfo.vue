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
const dialog = ref(false)

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
  password.value = ''
}
// 펫 정보 수정 사항 저장 변수
const newpetname = ref('')
const newpetspecie = ref('')
const newpetgender = ref('')
const newpetweight = ref('')
const newpetpicture = ref([])
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
const savenewpetInfo = async function () {
  const userId = localStorage.getItem('useremail')
  try {
    await accountstore.updatePet(
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
    // 비동기 요청 후 페이지 새로고침
    window.location.reload()
  } catch (error) {
    console.error('Pet info update failed:', error)
    // 오류 처리
  }
}

function navigateTocurrentlist() {
  router.push({ name: 'mypage-user-list-current' })
}
</script>

<template>
  <div class="info">
    <div class="box box1">
      <v-row justify="center" align="center" style="margin: 0px">
        <v-col cols="auto" class="d-flex justify-center align-center" style="padding-top: 18px">
          <v-avatar size="140" style="border: 2px solid rgb(212, 212, 212); margin: auto">
            <img :src="picture" alt="프로필 사진" style="max-width: 100%; height: auto" />
            <input
              type="file"
              @change="getFile"
              style="opacity: 0; position: absolute; width: 100%; height: 100%; cursor: pointer"
              :disabled="!isclicked"
            />
          </v-avatar>
          <v-col cols="auto" class="d-flex justify-center align-center">
            <span style="font-size: 32px; color: #545454; margin-left: 20px; font-weight: 400"
              ><strong>{{ userName }}</strong
              >님's 마이페이지</span
            >
          </v-col>
        </v-col>
      </v-row>
    </div>
    <div class="box-1 box2">
      <div style="background-color: #ffffff; width: 100%; height: 350px; border-radius: 12px">
        <div style="display: flex; justify-content: center">
          <v-icon
            size="30"
            color="#E27878"
            style="margin-top: 27px; margin-right: 8px; padding-bottom: 8px"
            >mdi-paw</v-icon
          >
          <span style="font-size: 25px; font-weight: bold; color: #545454; margin-top: 20px"
            >마이 펫 정보</span
          >
        </div>

        <v-carousel
          v-if="mypetlist.length > 0"
          hide-delimiters
          style="width: 100%; height: 100%"
          :continuous="false"
        >
          <v-carousel-item v-for="pet in mypetlist" :key="pet.id">
            <v-row justify="center" align="center" style="margin-top: 20px; margin-left: 40px">
              <v-col cols="4">
                <v-avatar class="pet-img" size="170">
                  <img
                    :src="pet.picture"
                    alt="펫 프로필 사진"
                    style="max-width: 100%; height: auto"
                  />
                </v-avatar>
              </v-col>
              <v-col cols="6" class="petinfo">
                <div style="text-align: start; color: #545454">
                  <span style="padding-top: 30px"><strong>이름:</strong> {{ pet.name }}</span
                  ><br />
                  <span><strong>나이:</strong> {{ pet.age }}</span>
                  <br />
                  <span><strong>종:</strong> {{ pet.specie }}</span>
                  <br />
                  <span><strong>성별:</strong> {{ pet.gender }}</span>
                  <br />
                  <span><strong>몸무게:</strong>{{ pet.weight }}</span>
                  <br />
                  <span><strong>메모:</strong> {{ pet.note }}</span>
                </div>
              </v-col>
              <v-col style="display: flex; justify-content: center">
                <v-btn color="#e27878" variant="outlined" rounded @click="editPetprofile(pet)"
                  >수정
                  <v-dialog v-model="dialog" activator="parent" width="auto">
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
                      <v-text-field
                        variant="underlined"
                        v-model="newpetname"
                        label="이름"
                      ></v-text-field>
                      <v-text-field
                        variant="underlined"
                        v-model="newpetage"
                        label="나이"
                      ></v-text-field>
                      <v-text-field
                        variant="underlined"
                        v-model="newpetspecie"
                        label="종"
                      ></v-text-field>
                      <v-text-field
                        variant="underlined"
                        v-model="newpetgender"
                        label="성별"
                      ></v-text-field>
                      <v-text-field
                        variant="underlined"
                        v-model="newpetweight"
                        label="무게"
                      ></v-text-field>
                      <v-text-field
                        variant="underlined"
                        v-model="newpetnote"
                        label="소개"
                      ></v-text-field>
                      <v-btn style="margin-bottom: 20px; margin-top: 10px" @click="savenewpetInfo"
                        >저장</v-btn
                      >
                    </v-sheet>
                  </v-dialog></v-btn
                >
              </v-col>
            </v-row>
          </v-carousel-item>
        </v-carousel>
      </div>
      <div style="width: 100%; height: 100px; border-radius: 12px; margin-top: 30px">
        <v-btn
          color="#E27878"
          block
          style="margin-bottom: 10px"
          size="large"
          @click="navigateTocurrentlist"
          >나의 예약 보기</v-btn
        >
      </div>
    </div>
    <div class="box box3">
      <div
        class="header-box2"
        style="
          width: 80%;
          display: flex;
          justify-content: space-between;
          align-items: center;
          margin-top: 30px;
        "
      >
        <div>
          <v-icon size="30" color="#E27878" style="margin-right: 8px; padding-bottom: 8px"
            >mdi-card-account-details-outline</v-icon
          >
          <span style="font-size: 25px; font-weight: bold; color: #545454">회원정보</span>
        </div>

        <v-btn
          variant="outlined"
          v-show="isclicked == false"
          style="margin: 10px; margin-right: 0px"
          @click="updateInfo"
          color="#E27878"
          rounded
          >개인정보 수정</v-btn
        >
      </div>
      <div style="display: flex; justify-content: center; width: 80%; gap: 30px">
        <v-text-field
          v-model="email"
          label="Email"
          variant="underlined"
          :readonly="isreadonly"
          style="flex-grow: 1"
        ></v-text-field>
        <v-text-field
          v-model="phoneNumber"
          label="전화번호"
          variant="underlined"
          :readonly="isreadonly"
          style="flex-grow: 1"
        ></v-text-field>
      </div>
      <div div style="display: flex; justify-content: center; width: 80%; gap: 30px">
        <v-text-field v-model="address" label="주소" variant="underlined" :readonly="isreadonly">
          <template v-slot:append>
            <v-btn
              v-if="isclicked"
              small
              @click="openKakaoAddressSearch"
              style="box-shadow: none; color: #e27878"
            >
              주소 변경
            </v-btn>
          </template></v-text-field
        >
      </div>
      <div
        div
        style="display: flex; justify-content: start; width: 80%; gap: 30px; margin-top: 20px"
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
      <v-btn
        color="#E27878"
        variant="outlined"
        v-if="isclicked"
        style="margin-top: 15px"
        @click="saveinfo"
        >저장</v-btn
      >
      <v-dialog v-model="opendialog" max-width="600px">
        <v-card>
          <v-card-title> 비밀번호 입력 </v-card-title>
          <v-text-field v-model="password" label="비밀번호" variant="underlined"></v-text-field>
          <v-card-actions>
            <v-btn variant="tonal" rounded color="primary" block @click="completeUpdate"
              >비밀번호 확인</v-btn
            >
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
  </div>
</template>

<style scoped>
/* :deep(.v-input) {
  align-items: center;
  justify-items: center;
} */
:deep(.v-field) {
  max-width: 100%;
}
.info {
  /* height: auto;
  justify-content: left;
  flex-direction: column;
  display: flex; */
  display: grid;
  grid-template-columns: repeat(2, 1fr); /* 두 개의 열로 구성 */
  grid-template-rows: auto; /* 각 열에 두 행씩 자동 크기로 구성 */
  gap: 10px; /* 그리드 사이의 간격 */
  height: 100vh; /* 전체 화면 높이 */
  padding: 10px; /* 컨테이너의 패딩 */
  box-sizing: border-box;
  box-sizing: border-box;
  align-content: start;
}
.box {
  /* border: 2px solid black;  */
  box-sizing: border-box;
  background-color: white;
  border-radius: 12px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
.box-1 {
  /* border: 2px solid black;  */
  box-sizing: border-box;
}
.box1 {
  grid-column: 1 / 2; /* 첫 번째 열에 배치 */
  grid-row: 1 / 2; /* 첫 번째 행에 배치 */
  width: 650px;
  height: 180px;
  margin-left: 120px;
  margin-right: 50px;
  margin-bottom: 20px;
  margin-top: 110px;
}

.box2 {
  grid-column: 2 / 3; /* 두 번째 열에 배치 */
  grid-row: 1 / 3; /* 첫 번째와 두 번째 행을 차지 */
  width: 550px;
  height: 530px;
  margin-top: 110px;
  margin-right: 30px;
}

.box3 {
  grid-column: 1 / 2; /* 첫 번째 열에 배치 */
  grid-row: 2 / 3; /* 두 번째 행에 배치 */
  width: 650px;
  height: 320px;
  margin-left: 120px;
  margin-right: 50px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.v-text-field {
  margin-bottom: 10px;
  width: 40%;
}

.header-box2 {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

/* .. */
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
.profile-info {
  width: 500px;
  margin-left: 0;
  background-color: #eef5ff;
  box-shadow: none;
  text-align: center;
}

.petcard {
  width: 20%;
}
.v-expand-transition {
  border: black;
  display: flex;
}

.petinfo {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
</style>
