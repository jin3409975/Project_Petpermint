<script setup>
import { ref, onBeforeMount, onMounted, computed, inject } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
import { useCommunityStore } from '@/stores/community'

const vuetify = inject('vuetify')

const display = computed(() => vuetify.value.breakpoint)

const community_stores = useCommunityStore()
const router = useRouter()

const appointId = ref()
const videos = ref([])
const video = ref()
onMounted(async () => {
  await community_stores.videolist()
  videos.value = community_stores.videos.videoList
  await community_stores.videocurrent()
  video.value = community_stores.video
  appointId.value = community_stores.video.sessionId
  console.log(video.value)
})
const handleEnteringBroadcastButton = () => {
  router.push({ name: 'cam-broadcast', params: { appointId: appointId.value } })
}
const sheet = ref(false)
</script>

<template>
  <div style="margin-top: 75px; margin-bottom: 80px">
    <div
      style="
        background-color: #3e6263;
        color: white;
        text-align: center;
        padding: 50px 0;
        margin-bottom: 20px;
      "
    >
      <h1>Welcome to the Expert Live Broadcasts!</h1>
    </div>
    <v-container fluid style="max-width: 70%">
      <div class="d-flex align-center justify-center flex-wrap text-center">
        <div style="font-size: 30px; font-weight: bold; color: rgb(70, 69, 69)">
          펫퍼민트만의 반려동물을 위한
        </div>
        <div style="font-size: 30px; font-weight: bold; color: rgb(70, 69, 69)">라이브 방송!</div>
      </div>
      <v-row>
        <v-col cols="12" md="6" style="margin-top: 20px">
          <v-sheet>
            <v-expansion-panels
              variant="accordion"
              style="margin-top: 30px; margin-bottom: 20px"
              multiple
              color="rgb(157, 188, 152, 0.6)"
            >
              <v-expansion-panel
                title="1. 정확한 정보와 지식"
                text="반려동물의 건강과 행복을 위해 전문가로부터 신뢰할 수 있는 지식을 얻을 수 있습니다."
              ></v-expansion-panel>

              <v-expansion-panel
                title="2. 건강 관리 및 예방"
                text="반려동물이 오래도록 건강하게 생활하려면 연령과 특성에 맞는 건강 관리 방법을 알아야 합니다. 올바른 사료 선택, 적절한 운동, 질병 예방 접종 등 다양한 헬스케어 라이브 방송을 만나보세요."
              ></v-expansion-panel>

              <v-expansion-panel
                title="3. 행동 문제 해결"
                text="전문 훈련사의 조언을 통해 행동 문제를 효과적으로 해결해 보세요."
              ></v-expansion-panel>

              <v-expansion-panel
                title="4. 유기동물 입양 지원"
                text="입양 과정, 필요한 준비사항, 입양 후 적응 과정등을 배워 입양 과정에 대해 자세히 알아 보세요."
              ></v-expansion-panel>

              <v-expansion-panel
                title="5. 행동 문제 해결"
                text="실시간 채팅을 통해 전문가의 직접적인 피드백을 받아 보세요."
              ></v-expansion-panel>

              <v-expansion-panel
                title="6. 행동 문제 해결"
                text="반려동물을 키우는 사람들과 서로 소통하고 지식과 경험을 공유해 보세요."
              ></v-expansion-panel>
            </v-expansion-panels>
            <div style="font-size: 17px; font-weight: 450; color: rgb(60, 60, 60)">
              이 외에도 다양한 정보들과 컨텐츠를 경험해 보세요.
            </div>
            <div style="font-size: 17px; font-weight: 450; color: rgb(60, 60, 60)">
              반려동물과의 삶을 더욱 풍요롭고 건강하게 저희 펫퍼민트와 함께하세요!
            </div>
          </v-sheet>
        </v-col>

        <v-col cols="12" md="5" offset="1" style="margin-top: 50px">
          <v-card
            v-if="video"
            class="black-square"
            color="black"
            style="
              min-height: 300px;
              display: flex;
              flex-direction: column;
              justify-content: center;
              align-items: center;
            "
          >
            <div style="padding-bottom: 30px; font-size: 20px; margin-right: 15px">
              <v-icon
                color="red-darken-2"
                icon="mdi-checkbox-blank-circle"
                style="margin-right: 2px; font-size: 20px; margin-bottom: 2px"
              ></v-icon
              >On-Air
            </div>
            <div style="margin: -20px">
              <b>{{ video.roomName }}</b>
            </div>
            <div style="display: flex; justify-content: center; gap: 10px; margin-top: 30px">
              <button class="enter2" @click="sheet = !sheet">방송 일정 확인하기</button>

              <button class="enter" @click="handleEnteringBroadcastButton">입장하기</button>
            </div>

            <v-bottom-sheet v-model="sheet" inset>
              <v-card class="text-center" height="250">
                <v-card-text>
                  <v-btn variant="text" @click="sheet = !sheet"> close </v-btn>
                  <br />
                  <br />
                  <v-sheet
                    style="width: 100%; height: 150px; overflow: auto; border: 1px solid #ccc"
                  >
                    <v-list>
                      <v-list-item v-for="item in videos" :key="item">
                        <v-list-item-content
                          >{{ item.startTime }} &nbsp; &nbsp;
                          {{ item.roomName }}</v-list-item-content
                        >
                      </v-list-item>
                    </v-list>
                  </v-sheet>
                </v-card-text>
              </v-card>
            </v-bottom-sheet>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<style scoped>
:deep(.v-expansion-panel-text) {
  font-weight: 410;
  color: rgb(108, 108, 108);
}
:deep(.v-expansion-panel-text__wrapper) {
  padding: 20px 24px 20px;
}
:deep(.v-expansion-panel-title) {
  font-weight: bold;
  color: rgb(58, 58, 58);
}
.enter {
  height: 2.8em;
  width: 10em;
  background: transparent;
  -webkit-animation: jello-horizontal 0.9s both;
  animation: jello-horizontal 0.9s both;
  border: 2px solid #f65f5f;
  outline: none;
  cursor: pointer;
  font-size: 17px;
  border-radius: 10px;
}

.enter:hover {
  background: #f65f5f;
  color: #ffffff;
  animation: squeeze3124 0.9s both;
}

.enter2 {
  height: 2.8em;
  width: 10em;
  background: transparent;
  -webkit-animation: jello-horizontal 0.9s both;
  animation: jello-horizontal 0.9s both;
  border: 2px solid #04b0ff;
  outline: none;
  cursor: pointer;
  font-size: 17px;
  border-radius: 10px;
}

.enter2:hover {
  background: #04b0ff;
  color: #ffffff;
  animation: squeeze3124 0.9s both;
}

@keyframes squeeze3124 {
  0% {
    -webkit-transform: scale3d(1, 1, 1);
    transform: scale3d(1, 1, 1);
  }

  30% {
    -webkit-transform: scale3d(1.25, 0.75, 1);
    transform: scale3d(1.25, 0.75, 1);
  }

  40% {
    -webkit-transform: scale3d(0.75, 1.25, 1);
    transform: scale3d(0.75, 1.25, 1);
  }

  50% {
    -webkit-transform: scale3d(1.15, 0.85, 1);
    transform: scale3d(1.15, 0.85, 1);
  }

  65% {
    -webkit-transform: scale3d(0.95, 1.05, 1);
    transform: scale3d(0.95, 1.05, 1);
  }

  75% {
    -webkit-transform: scale3d(1.05, 0.95, 1);
    transform: scale3d(1.05, 0.95, 1);
  }

  100% {
    -webkit-transform: scale3d(1, 1, 1);
    transform: scale3d(1, 1, 1);
  }
}
</style>
