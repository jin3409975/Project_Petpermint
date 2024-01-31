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

const pets = ref([{ id: 1, radios: '', petname: '', petage: '', more: '', name: 'Pet 1' }])
const visible = ref(false)

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
    pets.value.push({ id: newId, name: newName, petname: '', petage: '', more: '' })
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

const rules = {
  required: (value) => !!value || '입력해주세요'
}

// function validateForm() {
//   if (!form.value.validate()) {
//     alert('폼을 완성해주세요.')
//   } else {
//     nextStep()
//   }
// }

const emailStatus = ref('request')

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

// 채은: 프로필수정은 마이페이지에서 하는게 좋을듯..
// const getFile = function (event) {
//   console.log(event)
//   picture.value = event.target.files[0]
// }

const showSnackbar = ref(false)

function register() {
  // 회원가입 처리 로직 여기에 추가
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
    address1: address1.value,
    address2: address2.value,
    picture: picture.value
  }
  console.log(payload)
  store.usersignup(payload)
  // 여기서는 예시로 바로 스낵바를 표시합니다.
  showSnackbar.value = true

  // 스낵바가 표시되고 2000ms 후에 메인 페이지로 이동
  setTimeout(() => {
    router.push({ name: 'main-home' })
  }, 2000)
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

  <v-row no-gutters class="d-flex align-stretch" style="height: 100%">
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
      <v-container style="height: 100%; padding-bottom: 0px">
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
                <v-col style="padding-bottom: 0%">
                  <p>자택 주소</p>
                </v-col>
                <v-col cols="12" md="12" style="padding-top: 6px; padding-bottom: 0">
                  <v-text-field
                    label="주소 검색하기"
                    prepend-inner-icon="mdi-home-search-outline"
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
                <!-- 채은: 마이페이지에서 프로필 수정하는게 좋을듯.. -->
                <!-- <input type="file" v-on:change="getFile">프로필 사진 업로드</input> -->
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
          <!-- 3. 마이펫 정보 -->
          <template v-slot:item.3>
            <v-card flat>
              <h4 class="text-h4 mb-3">마이 펫 정보</h4>
              <p class="mb-5">내 반려동물의 정보를 입력해 주세요.</p>
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
                          <v-col style="padding-bottom: 0%">
                            <p>반려동물 종류</p>
                          </v-col>
                          <v-radio-group inline v-model="pet.radios">
                            <v-col cols="12" md="4" style="padding-bottom: 0%">
                              <v-radio label="강아지" value="강아지"></v-radio>
                            </v-col>
                            <v-col cols="12" md="4">
                              <v-radio label="고양이" value="고양이"></v-radio>
                            </v-col>
                            <v-col cols="12" md="4">
                              <v-radio label="기타" value="기타"></v-radio>
                            </v-col>
                          </v-radio-group>
                        </v-col>

                        <v-col cols="12" md="6">
                          <v-text-field
                            label="반려동물 이름 "
                            :rules="[rules.required]"
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
              <v-btn @click="register" color="primary" variant="tonal" v-bind="props"
                >회원가입</v-btn
              >
              <v-snackbar v-model="showSnackbar" :timeout="2000" color="primary">
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
</style>
