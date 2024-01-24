<script setup>
const emit = defineEmits(['select-type'])

const selectType = (type) => {
    emit('select-type', type)
}
</script>

<template>
    <div class="d-flex">
        <v-btn prepend-icon="$vuetify" variant="outlined" @click="selectType('vet')">수의사</v-btn>
        <v-btn prepend-icon="$vuetify" variant="outlined" @click="selectType('user')">일반 유저</v-btn>
    </div>
</template>

<style>
.d-flex {
    display: flex;
    justify-content: center; /* 수직 정렬 */
    align-items: center; /* 수평 정렬 */
    height: 100vh; /* 전체 뷰포트 높이 */
}
</style>
