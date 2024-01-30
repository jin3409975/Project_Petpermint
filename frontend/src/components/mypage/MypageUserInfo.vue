<script setup>
// import { ref } from 'vue'
// import { useRouter } from 'vue-router'

// const router = useRouter()
</script>

<template>
  <div class="my-info-container">
    <h2 class="my-info-title">{User}님의 정보</h2>
    <h6>
      고객님께서 가입하신 회원 정보입니다.<br />
      원활한 예약을 위하여 핸드폰 번호와 자택 주소를 필히 확인 부탁드립니다.
    </h6>

    <img src="" alt="profile-img" />
    <h5>프로필 이미지</h5>

    <label for="">아이디</label>
    <input type="text" />

    <label for="">비밀번호 수정</label>
    <input type="text" />

    <label for="">비밀번호 확인</label>
    <input type="text" />

    <label for="">이메일</label>
    <input type="text" />

    <label for="">핸드폰 번호</label>
    <input type="text" />

    <label for="">자택 주소</label>
    <input type="text" />
  </div>
</template>

<style scoped></style>
