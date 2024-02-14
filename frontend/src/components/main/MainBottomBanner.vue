<script setup></script>

<template>
  <v-container>
    <v-row align="center" justify="start" no-gutters style="padding-top: 80px; margin-bottom: 40px">
      <v-col cols="12" class="d-flex justify-center">
        <p class="main-text">자주 묻는 질문</p>
      </v-col>
    </v-row>
    <v-expansion-panels variant="accordion" class="my-5">
      <v-expansion-panel elevation="0"
        ><v-expansion-panel-title
          expand-icon="mdi-plus"
          collapse-icon="mdi-minus"
          class="font-weight-medium"
          style="font-size: 18px"
        >
          펫퍼민트는 어떤 웹사이트인가요?
        </v-expansion-panel-title>
        <v-expansion-panel-text>
          <p style="margin-top: 20px">
            'petpermint' 는 반려동물과의 건강한 동반 생활을 지원하는 온라인 펫 케어 플랫폼입니다.
          </p>
          <p style="margin-bottom: 3px">
            헬스케어 서비스로 수의사와의 온라인 초기상담, 병원 진료 예약 서비스, 응급 진료예약을
            지원합니다.
          </p>
          <p style="margin-bottom: 10px">
            또한, 라이프케어 서비스로 전문가와 함께하는 라이브 방송, 커뮤니티, 반려동물편의시설조회
            기능을 지원합니다.
          </p>
        </v-expansion-panel-text></v-expansion-panel
      >
      <v-expansion-panel elevation="0"
        ><v-expansion-panel-title
          expand-icon="mdi-plus"
          collapse-icon="mdi-minus"
          class="font-weight-medium"
          style="margin-top: 25px; font-size: 18px"
        >
          펫퍼민트는 누가 사용할 수 있나요?
        </v-expansion-panel-title>
        <v-expansion-panel-text>
          <p style="margin-top: 20px">
            사랑하는 애완동물의 건강과 건강을 걱정하는 사람이라면 누구나 펫퍼민트를 사용할 수
            있습니다.
          </p>
          <p>
            (초기상담)병원 방문 필요여부를 확인하고 싶은 보호자 , 수의사와 반려동물 건강에 대한
            상담을 받고 싶은 보호자, (진료예약) 병원 방문전 진료예약으로 긴 대기 시간을 줄이고 싶은
            보호자, (라이프케어-라이브방송) 전문가들이 제공하는 양질의 정보를 얻고 싶은 보호자,
            수의사 : 간단한 상담은 온라인으로 진행하여 시간의 효율을 높이고 싶은 수의사, 병원
            진료시간외 자율적 근무를 하고 싶은 수의사 , 예약 시스템으로 체계적으로 스케쥴을 관리하고
            싶은 수의사등 어느 누구나 쉽고 편리하게 사용하실 수 있습니다.
          </p>
        </v-expansion-panel-text></v-expansion-panel
      >
      <v-expansion-panel elevation="0"
        ><v-expansion-panel-title
          expand-icon="mdi-plus"
          collapse-icon="mdi-minus"
          class="font-weight-medium"
          style="margin-top: 25px; font-size: 18px"
        >
          초기 화상 상담에 대해서 자세히 알려주세요.
        </v-expansion-panel-title>
        <v-expansion-panel-text>
          <p style="margin-top: 20px; word-break: keep-all">
            "수의사와 함께하는 반려동물 건강 상담" 펫퍼민트의 초기상담은 내가 원하는 날짜, 시간을
            고르면 해당 시간에 상담이 가능한 수의사 리스트를 확인할 수 있습니다. 상담을 원하는
            수의사를 선택후 상담을 진행할 반려동물을 고른후 예약을 하면 해당 시간에 온라인으로
            수의사와 상담을 진행할 수 있습니다.
          </p>
        </v-expansion-panel-text></v-expansion-panel
      >
      <v-expansion-panel elevation="0"
        ><v-expansion-panel-title
          expand-icon="mdi-plus"
          collapse-icon="mdi-minus"
          class="font-weight-medium"
          style="margin-top: 25px; border-bottom: 1px solid rgb(225, 224, 224); font-size: 18px"
        >
          응급 예약은 어떻게 하나요?
        </v-expansion-panel-title>
        <v-expansion-panel-text>
          <p style="margin-bottom: 0px; margin-top: 15px">
            응급예약은 회원가입시 등록한 주소를 반경으로 가장 가까운 예약 가능한 병원 5개를
            추천합니다.
          </p>
          <p style="margin-bottom: 0px">
            지도상에서 추천한 병원중 예약할 병원의 마크를 고르면 예약 다이얼로그가 뜹니다.
          </p>
          <p style="margin-bottom: 10px">예약 버튼을 누르면 신속하게 응급예약이 진행됩니다.</p>
        </v-expansion-panel-text></v-expansion-panel
      >
    </v-expansion-panels>
  </v-container>
</template>

<style scoped>
.main-text {
  font-weight: bold;
  font-size: 45px;
}
:deep(.mdi-plus::before) {
  color: #668ba7;
}
:deep(.mdi-minus::before) {
  color: #668ba7;
}
</style>
