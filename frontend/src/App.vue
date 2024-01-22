<template>
    <v-app>
        <v-app-bar app fixed>
        <div class="flex-container">
            <!-- 왼쪽 항목 -->
            <v-toolbar-items>
            <v-btn text>Home</v-btn>
            <v-btn text>초기 상담 예약</v-btn>
            <v-btn text>병원 진료 예약</v-btn>
            <v-btn text>라이프케어</v-btn>
            </v-toolbar-items>
        </div>
        <!-- 로고 이미지 -->
        <v-img
            :src="logoUrl"
            class="my-2"
            contain
            height="40"
        ></v-img>
        <div class="flex-container">
            <!-- 오른쪽 항목 -->
            <v-toolbar-items>
            <v-btn text>로그인</v-btn>
            <v-btn text>회원가입</v-btn>
            </v-toolbar-items>
        </div>
        </v-app-bar>
    </v-app>
</template>
<script>

// 동적 이미지 경로 
export default {
computed: {
    logoUrl() {
    return new URL('@/assets/img/logo.png', import.meta.url).href
    }
}
}
</script>
<style>
.flex-container {
    flex: 1
}
</style>