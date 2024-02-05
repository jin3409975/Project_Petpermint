<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

// nav 해당 페이지로 이동
const navigateTo = (path) => {
  router.push(path)
}

const vetName = ref('김재민')
const email = ref('test@test.com')
const changPassword = ref(null)
const confirmPassword = ref(null)
const phoneNumber = ref('010-0000-0000')
const hospitalAddress = ref('삼성화재 유성연수원')
const changeHospitalAddress = ref(null)
const picture = ref(null)

// 파일 업로드
const getFile = function (event) {
  console.log(event)
  picture.value = event.target.files[0]
}

// 카카오 주소 검색
function openKakaoAddressSearch() {
  new window.daum.Postcode({
    oncomplete: (data) => {
      if (changeHospitalAddress.value !== '') {
        changeHospitalAddress.value = ''
      }
      if (data.userSelectedType === 'R') {
        // 사용자가 도로명 주소를 선택했을 경우
        changeHospitalAddress.value = data.roadAddress
      } else {
        // 사용자가 지번 주소를 선택했을 경우(J)
        changeHospitalAddress.value = data.jibunAddress
      }
    }
  }).open()
}
</script>

<template>
  <v-container class="profile-container">
    <v-layout>
      <v-navigation-drawer class="accent-4 rounded-custom" permanent width="200">
        <v-list>
          <v-list-item
            title="개인 정보 확인"
            value="개인 정보 확인"
            @click="navigateTo('/mypage/vet/info')"
          ></v-list-item>
          <v-list-item
            title="예약 내역 확인"
            value="예약 내역 확인"
            @click="navigateTo('/mypage/vet/list/current')"
          ></v-list-item>
          <v-list-item
            title="과거 예약 내역"
            value="과거 예약 내역"
            @click="navigateTo('/mypage/vet/list/past')"
          ></v-list-item>
        </v-list>
      </v-navigation-drawer>

      <v-main>
        <v-row justify="center">
          <v-col cols="12" md="12">
            <v-card
              style="
                border: 1px solid;
                margin-left: 20px;
                margin-right: 10px;
                padding-right: 10px;
                padding-left: 10px;
              "
              elevation="0"
            >
              <v-row>
                <v-col cols="8">
                  <v-card-title style="font-size: 30px; font-weight: bold; padding: 16px">
                    Dr. {{ vetName }}님의 정보
                  </v-card-title>
                  <v-card-text class="mt-4">
                    수의사님께서 입력하신 회원정보입니다. <br />
                    원활한 상담과 예약을 위해 핸드폰 번호와 병원 주소를 반드시 확인해주세요.
                  </v-card-text>
                </v-col>

                <v-col cols="3" class="text-right">
                  <v-avatar size="100" variant="outlined" class="mt-5 mr-10">
                    <img
                      :src="
                        picture
                          ? URL.createObjectURL(picture)
                          : 'https://cdn.vuetifyjs.com/images/john.jpg'
                      "
                      alt="프로필 사진"
                    />
                    <input
                      type="file"
                      @change="getFile"
                      style="
                        opacity: 0;
                        position: absolute;
                        width: 100%;
                        height: 100%;
                        cursor: pointer;
                      "
                    />
                  </v-avatar>
                  <p class="profile-description">프로필 사진을 누르면 수정할 수 있습니다.</p>
                </v-col>

                <v-row class="profile-text">
                  <v-col cols="12" md="7">
                    <p class="title">Email</p>
                    <p>{{ email }}</p>
                  </v-col>

                  <v-col cols="12" md="6">
                    <p class="title">비밀번호 수정</p>
                    <v-text-field
                      v-model="changPassword"
                      placeholder="비밀번호 수정"
                      type="changePassword"
                      density="compact"
                      variant="outlined"
                    ></v-text-field>
                  </v-col>

                  <v-col cols="12" md="6">
                    <p class="title">비밀번호 확인</p>
                    <v-text-field
                      v-model="confirmPassword"
                      placeholder="비밀번호 확인"
                      type="password"
                      density="compact"
                      variant="outlined"
                    ></v-text-field>
                  </v-col>

                  <v-col cols="12" md="6">
                    <p class="title">핸드폰 번호</p>
                    <p>{{ phoneNumber }}</p>
                  </v-col>

                  <v-col cols="12" md="7">
                    <p class="title">병원 주소</p>
                    <p>{{ hospitalAddress }}</p>
                  </v-col>

                  <v-col cols="12" md="6">
                    <p class="title">병원 주소 변경</p>
                    <v-text-field
                      placeholder="주소 선택 후 상세 주소를 입력해주세요."
                      v-model="changeHospitalAddress"
                      density="compact"
                      variant="outlined"
                      style="padding-bottom: 0px"
                      @click="openKakaoAddressSearch"
                    ></v-text-field>
                  </v-col>

                  <v-col
                    cols="12"
                    class="text-right"
                    style="padding-right: 12px; padding-top: 0px; padding-bottom: 20px"
                  >
                    <v-btn type="submit" color="indigo" elevation="0">저장</v-btn>
                  </v-col>
                </v-row>
              </v-row>
            </v-card>
          </v-col>
        </v-row>
      </v-main>
    </v-layout>
  </v-container>
</template>

<style scoped>
.profile-container {
  max-width: 30000px;
  margin-left: 5px;
  margin-right: 5px;
}

.rounded-custom {
  border-radius: 4px;
  border: 1px solid;
  text-align: center;
}

.profile-description {
  font-size: 10px;
  margin-top: 10px;
}

.title {
  font-weight: bold;
  font-size: 15px;
  margin-bottom: 5px;
}

.profile-text {
  padding-top: 7px;
  padding-left: 30px;
  padding-right: 30px;
  margin-bottom: 10px;
}
</style>
