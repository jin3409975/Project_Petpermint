<script setup>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const pets = ref([createPet()]) // 반려동물 정보 배열

const radios = ref(null)
const type = ref(null)
const petname = ref(null)
const petage = ref(null)
const more = ref(null)

const rules = {
  radios: [(v) => !!v || '반려동물의 종류를 선택해주세요.'],
  type: [(v) => !!v || '반려동물의 종을 입력해주세요.'],
  petname: [(v) => !!v || '반려동물의 이름을 입력해주세요.'],
  petage: [(v) => !!v || '반려동물의 나이을 입력해주세요.']
}

function createPet() {
  return reactive({
    radios: '',
    type: '',
    petname: '',
    petage: '',
    more: ''
  })
}

function addPetForm() {
  if (pets.value.every((pet) => pet.radios && pet.type && pet.petname && pet.petage)) {
    pets.value.push(createPet())
  } else {
    alert('모든 필수 입력값을 채워주세요.')
  }
}

function removePetForm(index) {
  if (pets.value.length > 1) {
    pets.value.splice(index, 1)
  } else {
    alert('최소 하나의 폼은 남겨야 합니다.')
  }
}

const signUp = function () {
  // const payload = {
  //     type: type.value,
  //     petname: petname.value,
  //     petage: petage.value,
  //     more: more.value,
  // }
  router.push({ name: 'register-complete' })
}
</script>

<template>
  <div class="sign-up-container">
    <p>나의 반려동물 정보</p>
    <form @submit.prevent="signUp" class="sign-up-form">
      <div v-for="(pet, index) in pets" :key="index" class="pet-form">
        <v-container fluid>
          <p>반려동물 종: {{ pet.radios }}</p>
          <v-radio-group v-model="pet.radios" :rules="rules.radios">
            <v-radio label="강아지" value="강아지"></v-radio>
            <v-radio label="고양이" value="고양이"></v-radio>
            <v-radio label="기타" value="기타"></v-radio>
          </v-radio-group>
        </v-container>
        <v-text-field
          label="반려동물 종 *"
          v-model="pet.type"
          :rules="rules.type"
          hide-details="auto"
        ></v-text-field>
        <v-text-field
          label="반려동물 이름  *"
          v-model="pet.petname"
          :rules="rules.petname"
          hide-details="auto"
        ></v-text-field>
        <v-text-field
          label="반려동물 나이 *"
          v-model="pet.petage"
          :rules="rules.petage"
          hide-details="auto"
        ></v-text-field>
        <v-text-field label="특이사항" v-model="pet.more" hide-details="auto"></v-text-field>
        <!-- 반려동물 종류 선택 라디오 버튼 및 기타 입력 필드 -->
        <div class="button-row">
          <v-btn @click="removePetForm(index)">삭제하기</v-btn>
          <v-spacer></v-spacer>
          <!-- 공간 분할 -->
          <v-btn @click="addPetForm">추가하기</v-btn>
        </div>
      </div>
      <div class="button-container">
        <v-btn type="submit" variant="outlined" class="submit-btn">회원가입</v-btn>
      </div>
    </form>
  </div>
</template>

<style>
.sign-up-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 20px;
}

.sign-up-form {
  width: 100%;
  max-width: 500px;
  margin-bottom: 30px;
}

.button-row {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}

.submit-btn {
  margin-top: 20px;
}

.button-container {
  display: flex;
  justify-content: center;
  margin-top: 10px;
}
</style>
