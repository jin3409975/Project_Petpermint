<script setup>
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
// import { useAccountStore } from '@/stores/account.js'
// import Complete from '@/components/register/Complete.vue'

const router = useRouter()
// const store = useAccountStore()

const email = ref(null)
const confirm = ref(null)
const password1 = ref(null)
const password2 = ref(null)
const name = ref(null)
const phone = ref(null)
const address1 = ref(null)
const address2 = ref(null)

const pets = ref([createPet()])

function createPet() {
  return reactive({
    radios: '',
    type: '',
    petname: '',
    petage: '',
    more: ''
  })
}

// 펫 정보 폼 추가
function addPetForm() {
  if (pets.value.every((pet) => pet.radios && pet.type && pet.petname && pet.petage)) {
    pets.value.push(createPet())
  } else {
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

// 회원가입 폼 / 회원가입이 완료 컴포넌트
// const showSignUpForm = ref(true)
// const showSignUpComplete = ref(false)

// 회원가입 완료하기 버튼
const InfoConfirm = function () {
  // const payload = {
  //     email: email.value,
  //     confirm: confirm.value,
  //     address: address.value,
  //     password1: password1.value,
  //     password2: password2.value,
  //     name: name.value,
  //     phone: phone.value,
  //     address1: address1.value,
  //     address2: address2.value,
  // }
  // store.signUp(payload) (추후 개발시 활성화)
  router.push({ name: 'register-complete' })

  // 데이터가 잘 전달 되면 회원가입 완료 페이지 띄우기(추후수정)
  //   try {
  //
  //     await store.signUp(payload)

  //   // 회원가입이 성공하면 상태를 업데이트, 회원가입 완료표시
  //   showSignUpForm.value = false
  //   showSignUpComplete.value = true
  // } catch (error) {
  //   // 회원가입 실패
  //   console.error('회원가입 실패:', error)
  // }
}
</script>

<template>
  <!-- 로고 -->
  <!-- <RouterLink to="/">
    <div class="auth-logo d-flex align-center gap-x-3">
      <VNodeRenderer :nodes="themeConfig.app.logo" />
      <h1 class="auth-title">
        {{ themeConfig.app.title }}
      </h1>
    </div>
  </RouterLink> -->

  <v-row no-gutters style="height: 100%">
    <!-- 왼쪽 이미지 -->
    <v-col md="4" class="d-none d-md-flex" style="background-color: #d2e0fb">
      <div
        class="d-flex align-center justify-end"
        style="width: 100%; height: 100%; padding-bottom: 0%"
      >
        <img
          src="@/assets/img/doctor_dog.png"
          style="max-width: 100%; height: auto; object-fit: contain"
        />
      </div>
    </v-col>
    <v-col cols="12" md="8" class="d-flex align-center justify-center custom-padding">
      <v-container>
        <v-stepper v-model="currentStep" hide-actions :items="['Account', 'Personal', 'Pet Info']">
          <!-- 1. 계정정보 -->
          <template v-slot:item.1>
            <v-card flat>
              <h4 class="text-h4 mb-2">계정 정보</h4>
              <p class="mb-5">계정 정보를 입력해 주세요.</p>
              <v-row class="mt-2">
                <v-col cols="12" md="6">
                  <v-text-field
                    dense
                    v-model="email"
                    label="이메일"
                    placeholder="ssafy01@ssafy.com"
                    variant="outlined"
                    color="#668ba7"
                    bg-color="transparent"
                  />
                </v-col>

                <v-col cols="12" md="6">
                  <v-text-field
                    v-model="confirm"
                    label="이메일 인증번호"
                    placeholder="123456"
                    variant="outlined"
                    color="#668ba7"
                    bg-color="transparent"
                  />
                </v-col>

                <v-col cols="12" md="6">
                  <v-text-field
                    v-model="password1"
                    label="비밀번호"
                    placeholder="············"
                    variant="outlined"
                    color="#668ba7"
                    bg-color="transparent"
                  />
                </v-col>

                <v-col cols="12" md="6">
                  <v-text-field
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
            <div class="button-row">
              <v-btn @click="goToType" style="box-shadow: none" variant="outlined" color="#757575"
                >돌아가기</v-btn
              >
              <v-spacer></v-spacer>
              <v-btn @click="nextStep" style="box-shadow: none" variant="flat" color="#668ba7"
                >다음</v-btn
              >
            </div>
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
                    v-model="phone"
                    placeholder="01012345678"
                    variant="outlined"
                    color="#668ba7"
                    bg-color="transparent"
                  />
                </v-col>

                <v-col cols="12" md="12">
                  <v-text-field
                    label="자택 주소"
                    v-model="address1"
                    variant="outlined"
                    color="#668ba7"
                    bg-color="transparent"
                  />
                </v-col>
                <v-col cols="12" md="12">
                  <v-text-field
                    label="상세 주소"
                    v-model="address2"
                    variant="outlined"
                    color="#668ba7"
                    bg-color="transparent"
                  />
                </v-col>
              </v-row>
            </v-card>
            <div class="button-row">
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

          <template v-slot:item.3>
            <v-card flat>
              <h4 class="text-h4 mb-3">마이 펫 정보</h4>
              <p class="mb-5">내 반려동물의 정보를 입력해 주세요.</p>
              <div v-for="(pet, index) in pets" :key="index" class="pet-form">
                <v-row class="mt-2">
                  <v-col cols="12" md="6">
                    <v-radio-group v-model="pet.radios">
                      <v-radio label="강아지" value="강아지"></v-radio>
                      <v-radio label="고양이" value="고양이"></v-radio>
                      <v-radio label="기타" value="기타"></v-radio>
                    </v-radio-group>
                  </v-col>
                  <v-col cols="12" md="6">
                    <v-text-field
                      label="반려동물 종"
                      v-model="pet.type"
                      variant="outlined"
                      color="#668ba7"
                      bg-color="transparent"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" md="6">
                    <v-text-field
                      label="반려동물 이름 "
                      v-model="pet.petname"
                      variant="outlined"
                      color="#668ba7"
                      bg-color="transparent"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" md="6">
                    <v-text-field
                      label="반려동물 나이"
                      v-model="pet.petage"
                      variant="outlined"
                      color="#668ba7"
                      bg-color="transparent"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" md="12">
                    <v-text-field
                      label="특이사항"
                      v-model="pet.more"
                      placeholder="특이사항이 없을 시 '없음'으로 작성해주세요."
                      variant="outlined"
                      color="#668ba7"
                      bg-color="transparent"
                    ></v-text-field>
                  </v-col>
                </v-row>
                <!-- 반려동물 종류 선택 라디오 버튼 및 기타 입력 필드 -->
                <div class="button-row">
                  <v-btn @click="removePetForm(index)">삭제하기</v-btn>
                  <v-spacer></v-spacer>
                  <v-btn @click="addPetForm">추가하기</v-btn>
                </div>
              </div>
            </v-card>
            <div class="button-row">
              <v-btn
                @click="previousStep"
                style="box-shadow: none"
                variant="outlined"
                color="#757575"
                >이전</v-btn
              >
              <v-spacer></v-spacer>
              <v-btn
                @click="InfoConfirm"
                style="box-shadow: none; color: white"
                variant="flat"
                color="#d9c56a"
                >회원가입</v-btn
              >
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
.button-row {
  margin-top: 30px;
}
</style>
