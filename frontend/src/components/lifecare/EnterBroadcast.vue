<script setup>
import { ref, onBeforeMount, onMounted } from 'vue'
import axios from 'axios'
import { useCommunityStore } from '@/stores/community'

const community_stores = useCommunityStore()
const videos = ref([])
onMounted(async () => {
  await community_stores.videolist()
  videos.value = community_stores.videos.videoList
  console.log(videos.value)
})
</script>

<template>
  <div>
    <v-container
      fluid
      class="d-flex justify-center align-center"
      style="max-width: 70%; margin-top: 100px"
    >
      <v-row>
        <v-col cols="12" md="6">
          <v-sheet class="pa-2 ma-2">
            <h2 style="text-align: center">전문가 라이브 방송</h2>
            <p style="text-align: center; font-size: 16px; line-height: 30px">
              반려동물의 특성과 연령에 따른 <b>건강 관리법</b>을 알아보세요.
              <br />
              반려동물과 함께하는 <b>여행</b>은 어떻게 <b>준비</b>해야 할까요?
              <br />
              반려동물에게 직접 <b>간식</b>을 만들어주세요.
              <br />
              전문 훈련사와 함께라면 <b>문제행동 교정</b>도 어렵지 않아요.
              <br />
              올바른 사료 선택법과 식이 권장량을 알아보고<br /><b>건강한 식습관</b>을 만들어주세요.
              <br />
              <b>유기동물 입양</b>에 대해 알아볼까요?
              <br />
              전문가와 함께하는 <b>실시간 Q & A</b>
              <br />
              수많은 정보들을 얻을 수 있는 전문가 라이브 방송!
              <br />
              지금 바로 경험해보세요 :)
            </p>
          </v-sheet>
        </v-col>

        <v-col cols="12" md="6">
          <v-card
            class="black-square"
            color="black"
            style="
              min-height: 350px;
              display: flex;
              flex-direction: column;
              justify-content: center;
              align-items: center;
            "
          >
            <div style="padding-bottom: 30px">NOW ON - AIR</div>
            <div style="padding-bottom: 30px">
              <v-icon
                size="large"
                color="red-darken-2"
                icon="mdi-checkbox-blank-circle"
                style="margin-right: 10px"
              ></v-icon
              >생방송 중
            </div>
            <v-btn size="medium" variant="outlined" color="red" style="width: 80px; height: 35px">
              <span style="color: white">입장하기</span>
            </v-btn>
          </v-card>
        </v-col>
      </v-row>
    </v-container>

    <v-container fluid class="d-flex justify-center align-center" style="max-width: 70%">
      <v-row>
        <v-col cols="12" style="margin-left: 10px">
          <h4 style="text-align: center">예정된 방송</h4>
          <v-sheet style="width: 100%; height: 150px; overflow: auto; border: 1px solid #ccc">
            <v-list>
              <v-list-item v-for="item in videos" :key="item">
                <v-list-item-content
                  >{{ item.startTime }} &nbsp; &nbsp; &nbsp; &nbsp;
                  <b>{{ item.roomName }}</b> &nbsp; &nbsp; &nbsp; &nbsp;<i>{{ item.note }} </i>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-sheet>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<style scoped></style>
