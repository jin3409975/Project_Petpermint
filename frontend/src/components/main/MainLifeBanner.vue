<script setup>
import { computed } from 'vue'

const videoUrl = computed(() => {
    return new URL('@/assets/img/video.PNG', import.meta.url).href
})
</script>

<template>
    <v-container>
        <v-carousel show-arrows="hover">
            <v-carousel-item>
                <div class="custom-background fill-height">
                    <v-row class="fill-height" align="center" justify="space-between">
                        <v-col cols="5" class="text-content">
                            <div class="display-4 font-weight-bold mb-4">전문가 라이브 방송</div>
                            <div class="subtitle-1 font-weight-bold mb-6">
                                다양한 주제로 전문가들이 진행하는 라이브 방송을 통해 건강한 수제
                                간식 만들기부터 장난감 리뷰, 입양 전 필수 체크리스트까지 반려동물의
                                삶을 풍요롭게 하는 꿀팁을 얻어보세요.
                            </div>
                            <v-btn color="primary" large>방송 시청하기</v-btn>
                        </v-col>
                        <v-col cols="6" md="6">
                            <img :src="videoUrl" height="300" class="rounded-lg" />
                        </v-col>
                    </v-row>
                </div>
            </v-carousel-item>

            <v-carousel-item
                src="https://cdn.vuetifyjs.com/images/cards/hotel.jpg"
                cover
            ></v-carousel-item>

            <v-carousel-item
                src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
                cover
            ></v-carousel-item>
        </v-carousel>
    </v-container>
</template>

<style>
.custom-background {
    background-color: #d2e0fb;
}

.display-4 {
    font-size: 2rem; /* 32px */
    font-weight: bold;
}

.subtitle-1 {
    font-size: 1.25rem; /* 20px */
    font-weight: bold;
}

.rounded-lg {
    border-radius: 0.5rem;
}
.text-content {
    margin-left: 50px;
    padding-right: 30px;
}
</style>
