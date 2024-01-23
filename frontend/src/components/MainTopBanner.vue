<script setup>
import { ref, computed } from 'vue'

// 반응형 데이터로 이미지 URL을 생성
const BigDogUrl = computed(() => {
    return new URL('@/assets/img/OwnerDog.png', import.meta.url).href
})
</script>

<template>
    <v-container>
        <v-row>
            <v-col cols="12" md="6">
                <div class="title my-8">사랑스러운 반려동물의 건강은</div>
                <div class="title mb-8">저희 펫퍼민트가</div>
                <div class="title mb-8">초기 화상 상담으로</div>
                <div class="title mb-8">지켜드려겠습니다.</div>
                <v-btn color="blue" class="mr-4">초기 상담 <br />예약하기</v-btn>
                <v-btn color="green">병원 진료 <br />예약하기</v-btn>
            </v-col>
            <v-col cols="12" md="6">
                <img :src="BigDogUrl" aspect-ratio="1" class="rounded-lg" />
            </v-col>
        </v-row>
    </v-container>
</template>

<style scoped>
.title {
    font-size: 30px;
    font-weight: bold;
}
.rounded-lg {
    border-radius: 0.5rem;
}
</style>
