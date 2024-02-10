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
      <v-row no-gutters>
        <v-col cols="6">
          <v-sheet class="pa-2 ma-2">
            <h2 style="text-align: center">전문가 라이브 방송</h2>
            <p style="text-align: center; font-size: 20px; line-height: 45px">
              전문가 라이브 방송으로 우리 아이를 위한 모든 것을 배워보세요! 다양한 수제 간식
              만들기부터 장난감 리뷰, 입양에 필요한 꿀팁까지, 모든 정보를 실시간으로 제공합니다.
              이제 더 이상 고민하지 마시고, 전문가의 조언을 들으며 우리 아이에게 최고의 케어를
              제공해보세요. 지금 바로 참여하시고, 우리 아이를 위한 최선의 선택을 해보세요!
            </p>
          </v-sheet>
        </v-col>

        <v-col cols="6">
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
            <div>NOW ON-AIR</div>
            <div>
              <v-icon size="large" color="red-darken-2" icon="mdi-checkbox-blank-circle"></v-icon
              >생방송 중
            </div>
            <v-btn size="small" variant="outlined" color="red">
              <span style="color: white">입장하기 </span>
            </v-btn>
          </v-card>
        </v-col>
      </v-row>
    </v-container>

    <v-container
      fluid
      class="d-flex justify-center align-center"
      style="max-width: 70%; margin-top: 20px"
    >
      <v-row>
        <v-col cols="6" style="margin-left: 10px; margin-top: -50px">
          <h5>예정된 방송</h5>
          <v-sheet style="width: 500px; height: 150px; overflow: auto; border: 1px solid #ccc">
            <v-list>
              <v-list-item v-for="item in videos" :key="item">
                <v-list-item-content
                  >{{ item.startTime }} &nbsp; &nbsp; {{ item.roomName }}</v-list-item-content
                >
              </v-list-item>
            </v-list>
          </v-sheet>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<style scoped></style>
