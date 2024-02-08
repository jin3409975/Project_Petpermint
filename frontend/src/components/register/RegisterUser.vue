<script setup>
import { ref, computed, watch } from 'vue'
import { useRouter } from 'vue-router'
import { useAccountStore } from '@/stores/account.js'

// Props 정의
const props = defineProps({
  title: String
})

const router = useRouter()
const store = useAccountStore()

const email = ref('')
const confirm = ref('')
const password1 = ref(null)
const password2 = ref(null)
const name = ref(null)
const phone = ref(null)
const address1 = ref(null)
const address2 = ref(null)
const picture = ref(null)

const pets = ref([
  { id: 1, petspecies: '', petname: '', petage: '', petweight: '', more: '', name: 'Pet 1' }
])
const visible = ref(false)

const gender = ref(['암컷', '수컷'])

// 로고
const logoUrl = computed(() => {
  return new URL('/assets/img/logomain.png', import.meta.url).href
})

// 탭
const tab = ref(null)

// 탭의 개수를 결정하는 계산된 속성
const length = computed(() => pets.value.length)

// 각 펫의 petname을 감시하고, 변경 시 해당 탭 제목을 업데이트
watch(
  pets,
  (newPets) => {
    newPets.forEach((pet, index) => {
      if (pet.petname) {
        pet.name = pet.petname // 사용자가 입력한 이름으로 탭 제목 업데이트
      } else {
        pet.name = `Pet ${index + 1}` // petname이 비어있을 경우 기본 이름 "Pet X" 형식으로 설정
      }
    })
  },
  { deep: true }
)

// 펫 정보 폼 추가
const addPetForm = () => {
  if (pets.value.length < 5) {
    const newId = pets.value.reduce((maxId, pet) => Math.max(maxId, pet.id), 0) + 1
    const newName = `Pet ${newId}`
    pets.value.push({
      id: newId,
      name: newName,
      petname: '',
      petage: '',
      petspecies: '',
      petweight: '',
      more: ''
    })
    tab.value = newId - 1 // 새롭게 추가된 탭으로 이동 (0부터 시작하는 인덱스로 변환)
  } else if (pets.value.length >= 3) {
    // 폼의 개수가 최대치에 도달했을 경우
    alert('폼을 더 이상 추가할 수 없습니다. 최대 3개까지만 추가 가능합니다.')
  } else {
    // 필수 입력값이 모두 채워지지 않았을 경우
    alert('모든 필수 입력값을 채워주세요.')
  }
}

// 펫 정보 폼 삭제
function removePetForm(index) {
  if (pets.value.length > 1) {
    pets.value.splice(index, 1)
  } else {
    alert('최소 하나의 폼은 남겨야 합니다.')
  }
}

const currentStep = ref(1)

// 다음 버튼
const nextStep = () => {
  if (currentStep.value < 3) {
    currentStep.value++
  }
}

// 이전 버튼
const previousStep = () => {
  if (currentStep.value > 1) {
    currentStep.value--
  }
}

// 선택화면으로 돌아가기 버튼
const goToType = () => {
  router.push({ name: 'register-home' })
}

// 이메일 인증
const emailStatus = ref('request')

const emailAction = async () => {
  if (emailStatus.value === 'request') {
    console.log('이메일 인증 요청 user')
    let result = await store.emailRequest(email.value)
    if (result) {
      emailStatus.value = 'validate'
    } else {
      return false
    }
  } else if (emailStatus.value === 'validate') {
    console.log('이메일 확인 요청 user')
    let result = await store.emailValidate(email.value, confirm.value)
    if (result) {
      emailStatus.value = 'completed'
    } else {
      return false
    }
  }
}

//1페이지 검증 시작
const checkPage1 = ref(true)
const emailTest = (e) => {
  if (e == '' || e == null) {
    checkPage1.value = false
    return false
  }
  if (
    !/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i.test(e)
  ) {
    checkPage1.value = false
    return false
  }

  checkPage1.value = true
  return true
}

const passwordTest = (p) => {
  if (p != password1.value) {
    checkPage1.value = false
    return false
  }
  checkPage1.value = true
  return true
}

const page1Test = () => {
  if (emailStatus.value != 'completed') {
    alert('이메일 인증을 완료 해주세요')
    return false
  }
  if (checkPage1.value == false) {
    alert('기입한 정보를 확인 해주세요')
    return false
  }
  nextStep()
}
//1페이지 검증 완료

//2페이지 검증 시작
const checkPage2 = ref(true)

const nameTest = (n) => {
  if (n == '' || n == null) {
    checkPage2.value = false
    return false
  }

  checkPage2.value = true
  return true
}

const phoneTest = (p) => {
  if (!/^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/.test(p)) {
    checkPage2.value = false
    return false
  }

  checkPage2.value = true
  return true
}

const addressTest = (a) => {
  if (a == '' || a == null) {
    checkPage2.value = false
    return false
  }

  checkPage2.value = true
  return true
}

const page2Test = () => {
  if (checkPage2.value == false) {
    alert('기입한 정보를 확인 해주세요')
    return false
  }
  nextStep()
}
//2페이지 검증 완료

// 파일 업로드
const getFile = function (event) {
  console.log(event)
  picture.value = event.target.files[0]
}

const showSnackbar = ref(false)

function register() {
  // 회원가입 처리 로직 여기에 추가
  const payload = {
    email: email.value,
    password: password1.value,
    name: name.value,
    phone: phone.value,
    address: address1.value
  }
  store.usersignup(payload)
  // 여기서는 예시로 바로 스낵바를 표시합니다.
  showSnackbar.value = true

  // 스낵바가 표시되고 2000ms 후에 메인 페이지로 이동
  setTimeout(() => {
    router.push({ name: 'main-home' })
  }, 2000)
}

// 카카오 주소 검색
function openKakaoAddressSearch() {
  new window.daum.Postcode({
    oncomplete: (data) => {
      if (address2.value !== '') {
        address2.value = ''
      }
      if (data.userSelectedType === 'R') {
        // 사용자가 도로명 주소를 선택했을 경우
        address1.value = data.roadAddress
      } else {
        // 사용자가 지번 주소를 선택했을 경우(J)
        address1.value = data.jibunAddress
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
  <v-row no-gutters class="d-flex align-stretch" style="height: 100%">
    <v-col
      md="4"
      class="d-none d-md-flex align-end justify-center image-container"
      style="background-color: #d2e0fb; position: relative"
    >
      <!-- 로고 -->
      <RouterLink
        to="/"
        class="logo-container"
        style="position: absolute; top: 0; left: 50%; padding: 20px; transform: translateX(-50%)"
      >
        <img :src="logoUrl" style="height: 90px" />
      </RouterLink>
    </v-col>
    <v-col cols="12" md="8" class="d-flex align-center justify-center custom-padding">
      <v-container style="height: 100%; padding-bottom: 0px">
        <v-stepper v-model="currentStep" hide-actions :items="['Account', 'Personal', 'Pet Info']">
          <!-- 1. 계정정보 -->
          <template v-slot:item.1>
            <v-form ref="form" lazy-validation>
              <v-card flat>
                <h4 class="text-h4 mb-2" style="margin-top: 30px">계정 정보</h4>
                <p class="mb-5">계정 정보를 입력해 주세요.</p>
                <v-row class="mt-2">
                  <v-col cols="12" md="6">
                    <v-text-field
                      dense
                      v-model="email"
                      label="이메일"
                      placeholder="ssafy01@ssafy.com"
                      density="comfortable"
                      variant="outlined"
                      color="#668ba7"
                      bg-color="transparent"
                      :readonly="emailStatus === 'validate' || emailStatus === 'completed'"
                      :rules="[(v) => emailTest(v)]"
                    />
                  </v-col>

                  <v-col cols="12" md="6">
                    <v-text-field
                      v-model="confirm"
                      label="이메일 인증번호"
                      density="comfortable"
                      placeholder="123456"
                      variant="outlined"
                      color="#668ba7"
                      bg-color="transparent"
                      :readonly="emailStatus === 'completed'"
                    >
                      <template v-slot:append>
                        <v-btn
                          v-if="emailStatus === 'request'"
                          small
                          @click="emailAction"
                          style="box-shadow: none; color: #668ba7"
                        >
                          인증 요청
                        </v-btn>
                        <v-btn
                          v-else-if="emailStatus === 'validate'"
                          small
                          @click="emailAction"
                          style="box-shadow: none; color: #668ba7"
                        >
                          인증 확인
                        </v-btn>
                        <v-icon
                          v-else-if="emailStatus === 'completed'"
                          size="large"
                          color="#43A047"
                          icon="mdi-check-circle"
                        ></v-icon>
                      </template>
                    </v-text-field>
                  </v-col>

                  <v-col cols="12" md="6">
                    <v-text-field
                      :append-inner-icon="visible ? 'mdi-eye-off' : 'mdi-eye'"
                      :type="visible ? 'text' : 'password'"
                      @click:append-inner="visible = !visible"
                      v-model="password1"
                      label="비밀번호"
                      density="comfortable"
                      placeholder="············"
                      variant="outlined"
                      color="#668ba7"
                      bg-color="transparent"
                    />
                  </v-col>

                  <v-col cols="12" md="6">
                    <v-text-field
                      :append-inner-icon="visible ? 'mdi-eye-off' : 'mdi-eye'"
                      :type="visible ? 'text' : 'password'"
                      @click:append-inner="visible = !visible"
                      density="comfortable"
                      v-model="password2"
                      label="비밀번호 확인"
                      placeholder="············"
                      variant="outlined"
                      color="#668ba7"
                      bg-color="transparent"
                      :rules="[(v) => passwordTest(v)]"
                      hide-details="true"
                    />
                  </v-col>
                </v-row>
              </v-card>
              <div class="d-flex justify-space-between button-row">
                <v-btn @click="goToType" style="box-shadow: none" variant="outlined" color="#757575"
                  >돌아가기</v-btn
                >
                <v-btn @click="page1Test" style="box-shadow: none" variant="flat" color="#668ba7"
                  >다음</v-btn
                >
              </div>
            </v-form>
          </template>
          <!-- 2. 개인 정보 -->
          <template v-slot:item.2>
            <v-card flat>
              <h4 class="text-h4 mb-3">개인 정보</h4>
              <p class="mb-5">상담 및 병원 예약 시 필요한 개인 정보를 입력해 주세요.</p>
              <v-row class="mt-2">
                <v-col cols="12" md="6">
                  <v-text-field
                    label="이름"
                    density="comfortable"
                    v-model="name"
                    placeholder="김싸피"
                    variant="outlined"
                    color="#668ba7"
                    bg-color="transparent"
                    :rules="[(v) => nameTest(v)]"
                  />
                </v-col>

                <v-col cols="12" md="6">
                  <v-text-field
                    label="휴대전화번호"
                    density="comfortable"
                    v-model="phone"
                    placeholder="010-1234-5678"
                    variant="outlined"
                    color="#668ba7"
                    bg-color="transparent"
                    :rules="[(v) => phoneTest(v)]"
                  />
                </v-col>
                <v-col cols="12" md="10" style="padding-top: 20px; padding-bottom: 0">
                  <v-text-field
                    label="자택주소 검색하기"
                    density="comfortable"
                    prepend-inner-icon="mdi-home-search-outline"
                    v-model="address1"
                    variant="outlined"
                    color="#668ba7"
                    bg-color="transparent"
                    readonly
                    @click="openKakaoAddressSearch"
                  />
                </v-col>
                <v-col cols="12" md="10">
                  <v-text-field
                    label="자택 상세주소"
                    density="comfortable"
                    v-model="address2"
                    variant="outlined"
                    color="#668ba7"
                    bg-color="transparent"
                    :rules="[(v) => addressTest(v)]"
                  />
                </v-col>
              </v-row>
            </v-card>
            <div class="d-flex justify-space-between button-row">
              <v-btn
                @click="previousStep"
                style="box-shadow: none"
                variant="outlined"
                color="#757575"
                >이전</v-btn
              >
              <v-spacer></v-spacer>
              <v-btn @click="page2Test" style="box-shadow: none" variant="flat" color="#668ba7"
                >다음</v-btn
              >
            </div>
          </template>
          <!-- 3. 마이펫 정보 -->
          <template v-slot:item.3>
            <v-card flat>
              <h4 class="text-h4 mb-3">마이 펫 정보</h4>
              <p>내 반려동물의 정보를 입력해 주세요. (최대 5마리)</p>
              <!-- Tabs -->
              <v-tabs v-model="tab">
                <v-tab v-for="pet in pets" :key="pet.id" :value="pet.id">{{ pet.name }}</v-tab>
                <v-tab v-if="pets.length < 5" @click="addPetForm">+</v-tab>
              </v-tabs>
              <!-- Tabs 내용 -->
              <v-card-text>
                <v-window v-model="tab">
                  <v-window-item v-for="pet in pets" :key="pet.id" :value="pet.id">
                    <!-- 반려동물 정보 입력 폼 -->
                    <div class="pet-form">
                      <v-row class="mt-2">
                        <v-col cols="12" md="12" style="padding-top: 0%; padding-bottom: 0%">
                          <v-btn
                            class="custom-icon-color"
                            icon="mdi-minus-box-multiple"
                            size="large"
                            @click="removePetForm(index)"
                            style="box-shadow: none; position: absolute; top: 0; right: 0"
                          ></v-btn>
                        </v-col>
                        <v-col cols="12" md="5">
                          <v-text-field
                            label="펫 이름"
                            placeholder="금동이"
                            density="comfortable"
                            v-model="pet.petname"
                            variant="outlined"
                            color="#668ba7"
                            bg-color="transparent"
                          ></v-text-field>
                        </v-col>

                        <v-col cols="12" md="3">
                          <v-text-field
                            label="나이"
                            placeholder="5개월"
                            density="comfortable"
                            v-model="pet.petage"
                            variant="outlined"
                            color="#668ba7"
                            bg-color="transparent"
                          ></v-text-field>
                        </v-col>

                        <v-col cols="12" md="3">
                          <v-select
                            :items="gender"
                            v-model="pet.gender"
                            density="comfortable"
                            label="성별"
                            variant="outlined"
                          ></v-select>
                        </v-col>
                        <v-col cols="12" md="4">
                          <v-text-field
                            label="펫 종류"
                            density="comfortable"
                            placeholder="말티즈"
                            v-model="pet.petspecies"
                            variant="outlined"
                            color="#668ba7"
                            bg-color="transparent"
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" md="3">
                          <v-text-field
                            label="몸무게"
                            density="comfortable"
                            placeholder="3kg"
                            v-model="pet.petweight"
                            variant="outlined"
                            color="#668ba7"
                            bg-color="transparent"
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" md="5">
                          <v-file-input
                            label="반려동물 사진"
                            variant="underlined"
                            v-on:change="getFile"
                          ></v-file-input>
                        </v-col>

                        <v-col cols="12" md="12">
                          <v-text-field
                            label="특이사항"
                            density="comfortable"
                            v-model="pet.more"
                            placeholder="특이사항이 없을 시 '없음'으로 작성해주세요."
                            variant="outlined"
                            color="#668ba7"
                            bg-color="transparent"
                          ></v-text-field>
                        </v-col>
                      </v-row>
                    </div>
                  </v-window-item>
                </v-window>
              </v-card-text>
            </v-card>
            <div class="d-flex justify-space-between last-btn">
              <v-btn
                @click="previousStep"
                style="box-shadow: none"
                variant="outlined"
                color="#757575"
                >이전</v-btn
              >
              <v-spacer></v-spacer>
              <v-btn @click="register" color="#668ba7" variant="flat" v-bind="props"
                >회원가입</v-btn
              >
              <v-snackbar v-model="showSnackbar" :timeout="2000" color="#668ba7">
                <div class="text-center">
                  <h4>회원가입이 완료되었습니다.</h4>
                  <p>메인페이지로 이동합니다.</p>
                </div>
              </v-snackbar>
            </div>
          </template>
        </v-stepper>
      </v-container>
    </v-col>
  </v-row>
</template>

<style>
.custom-padding {
  padding-top: 30px !important;
  padding-left: 130px !important;
  padding-right: 130px !important;
}
.v-stepper,
.v-stepper-header,
.v-stepper-content {
  box-shadow: none !important;
}

.v-stepper-header {
  padding-bottom: 20px !important;
}
.v-input__details {
  display: none !important;
}
.last-btn {
  height: 0% !important;
  margin-top: 0px;
}
.button-row {
  height: 0% !important;
  margin-top: 30px;
}
.pet-form {
  border: 1px solid #ccc; /* 테두리 색상과 두께 설정 */
  padding: 10px; /* 내부 여백 추가 */
  margin-bottom: 10px; /* 폼 간 간격 추가 */
  padding-top: 0px;
  position: relative !important;
}
.custom-icon-color .v-icon {
  color: #ef5350; /* 아이콘 색상을 녹색으로 변경 */
}
.profile-upload {
  cursor: pointer;
}
.image-container {
  background-image: url('/assets/img/OwnerRegBg.png');
  background-size: cover; /* 이미지가 컨테이너를 완전히 채우도록 조정 */
  background-position: center; /* 이미지 중심이 컨테이너 중심과 일치하도록 조정 */
  width: 100%; /* 컨테이너의 전체 너비를 사용 */
  height: 100%; /* 컨테이너의 전체 높이를 사용 */
}
</style>
