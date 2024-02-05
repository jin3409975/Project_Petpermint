<script setup>
import { ref, computed, watch } from 'vue'
import { useRouter } from 'vue-router'
import { useAccountStore } from '@/stores/account.js'

// Props 정의
const props = defineProps({
  title: String
})

const visible = ref(false)

const store = useAccountStore()
const router = useRouter()

const email = ref(null)
const confirm = ref(null)
const password1 = ref(null)
const password2 = ref(null)
const name = ref(null)
const phone = ref(null)
const license = ref(null)
const hospitalname = ref(null)
const start = ref(null)
const end = ref(null)
const more = ref(false)
const address1 = ref(null)
const address2 = ref(null)
const picture = ref(null)

// 로고
const logoUrl = computed(() => {
  return new URL('@/assets/img/logomain.png', import.meta.url).href
})

// 파일 업로드
const getFile = function (event) {
  console.log(event)
  picture.value = event.target.files[0]
}

// 이메일 인증
const emailStatus = ref('request')

const emailRequest = function () {
  console.log('이메일 인증 요청', emailcheck.value)
  store.emailRequest(email.value)
  console.log('결과', store.result)
}

const emailValidate = function () {
  console.log('이메일 확인 요청', emailcheck.value)
  emailcheck.value = store.emailValidate(email.value, confirm.value)
  console.log('결과', store.result)
}

const emailAction = () => {
  if (emailStatus.value === 'request') {
    console.log('이메일 인증 요청')
    store.emailRequest(email.value)
    emailStatus.value = 'validate'
  } else if (emailStatus.value === 'validate') {
    console.log('이메일 확인 요청')
    store.emailValidate(email.value, confirm.value)
    emailStatus.value = 'completed'
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

const showSnackbar = ref(false)

const register = function () {
  if (!store.result) {
    alert('이메일 인증을 진행 해주세요')
    return false
  }
  const payload = {
    email: email.value,
    confirm: confirm.value,
    password1: password1.value,
    password2: password2.value,
    name: name.value,
    phone: phone.value,
    license: license.value,
    hospitalname: work.value,
    start: start.value,
    end: end.value,
    address1: address1.value,
    address2: address2.value,
    picture: profile.value
  }
  store.vetsignup(payload)
  showSnackbar.value = true // 회원가입 완료 시 스낵바 표시

  // 스낵바가 표시된 후 일정 시간이 지난 후 페이지 이동
  setTimeout(() => {
    router.push({ name: 'main-home' })
  }, 3000) // 예: 3초 후 이동
}

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
  <v-row no-gutters class="d-flex align-stretch" style="height: 100%">
    <v-col
      md="4"
      class="d-none d-md-flex align-end justify-center vetimage-container"
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
        <v-stepper
          v-model="currentStep"
          hide-actions
          :items="['Account', 'Personal/veterinarian', 'Hospital']"
        >
          <!-- 1. 계정 정보 -->
          <template v-slot:item.1>
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
                    :readonly="emailStatus === 'validate' || emailStatus === 'completed'"
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
                  />
                </v-col>
              </v-row>
            </v-card>
            <div class="d-flex justify-space-between button-row">
              <v-btn @click="goToType" style="box-shadow: none" variant="outlined" color="#757575"
                >돌아가기</v-btn
              >
              <v-btn @click="nextStep" style="box-shadow: none" variant="flat" color="#668ba7"
                >다음</v-btn
              >
            </div>
          </template>
          <!-- 2. 개인 및 수의사 정보 -->
          <template v-slot:item.2>
            <v-card flat>
              <h4 class="text-h4 mb-3">개인 및 수의사 정보</h4>
              <p class="mb-5">
                수의사 인증 절차에 필요한 개인 정보와 수의사 인증 정보를 정확히 입력해 주세요.
              </p>
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
                  />
                </v-col>

                <v-col cols="12" md="6">
                  <v-text-field
                    label="휴대전화번호"
                    density="comfortable"
                    v-model="phone"
                    placeholder="01012345678"
                    variant="outlined"
                    color="#668ba7"
                    bg-color="transparent"
                  />
                </v-col>
                <v-col cols="12" md="4">
                  <v-text-field
                    label="수의사면허번호"
                    density="comfortable"
                    v-model="license"
                    variant="outlined"
                    color="#668ba7"
                    bg-color="transparent"
                  ></v-text-field>
                </v-col>
                <v-col cols="12" md="8">
                  <v-file-input
                    label="면허증 사진 업로드"
                    variant="underlined"
                    v-on:change="getFile"
                  ></v-file-input>
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
              <v-btn @click="nextStep" style="box-shadow: none" variant="flat" color="#668ba7"
                >다음</v-btn
              >
            </div>
          </template>
          <!-- 3. 병원 정보 -->
          <template v-slot:item.3>
            <v-card flat>
              <h4 class="text-h4 mb-3">동물병원 정보</h4>
              <p class="mb-5">현재 일하고 계신 동물병원 정보를 입력해 주세요.</p>
              <v-row class="mt-2">
                <v-col cols="12" md="4">
                  <v-text-field
                    label="병원이름(필수)"
                    v-model="hospitalname"
                    density="comfortable"
                    placeholder="김싸피"
                    variant="outlined"
                    color="#668ba7"
                    bg-color="transparent"
                  />
                </v-col>

                <v-col cols="12" md="4">
                  <v-text-field
                    label="진료 시작 시간"
                    v-model="start"
                    density="comfortable"
                    placeholder="09:00"
                    variant="outlined"
                    color="#668ba7"
                    bg-color="transparent"
                  />
                </v-col>
                <v-col cols="12" md="4">
                  <v-text-field
                    label="진료 종료 시간"
                    v-model="end"
                    density="comfortable"
                    placeholder="18:00"
                    variant="outlined"
                    color="#668ba7"
                    bg-color="transparent"
                  />
                </v-col>
                <v-col cols="12" md="12" style="padding-top: 15 px; padding-bottom: 0">
                  <v-text-field
                    label="병원주소 검색하기(필수)"
                    v-model="address1"
                    density="comfortable"
                    prepend-inner-icon="mdi-home-search-outline"
                    variant="outlined"
                    color="#668ba7"
                    bg-color="transparent"
                    readonly
                    @click="openKakaoAddressSearch"
                  />
                </v-col>
                <v-col cols="12" md="12">
                  <v-text-field
                    label="병원 상세주소(필수)"
                    v-model="address2"
                    density="comfortable"
                    variant="outlined"
                    color="#668ba7"
                    bg-color="transparent"
                  />
                </v-col>
                <v-col cols="12" md="12" style="margin-top: 8px">
                  <v-textarea
                    label="자기소개/병원소개"
                    v-model="more"
                    auto-grow
                    variant="outlined"
                    rows="3"
                    row-height="25"
                    shaped
                    placeholder="안녕하세요. 싸피동물병원 원장 이채은입니다."
                  ></v-textarea>
                </v-col>
              </v-row>
            </v-card>
            <div class="d-flex justify-space-between last-btn mt-5">
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
              <v-snackbar v-model="showSnackbar" :timeout="2000" vertical color="#668ba7">
                <h2 style="margin-bottom: 20px">회원가입이 완료되었습니다.</h2>

                <p>인증과정에 1~2일 가량 소요될 수 있습니다.</p>

                <p>홈페이지로 이동합니다.</p>

                <template v-slot:actions> </template>
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

.profile-upload {
  cursor: pointer;
}
.vetimage-container {
  background-image: url('../../assets/img/vet.PNG');
  background-size: cover; /* 이미지가 컨테이너를 완전히 채우도록 조정 */
  background-position: center; /* 이미지 중심이 컨테이너 중심과 일치하도록 조정 */
  width: 100%; /* 컨테이너의 전체 너비를 사용 */
  height: 100%; /* 컨테이너의 전체 높이를 사용 */
}
</style>
