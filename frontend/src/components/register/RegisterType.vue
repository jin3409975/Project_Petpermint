<script setup>
const emit = defineEmits(['type-selected'])

const selectType = (type) => {
    emit('type-selected', type)
}
</script>

<template>
    <div>
        <button @click="selectType('vet')">수의사</button>
        <button @click="selectType('user')">일반 유저</button>
    </div>
</template>

<style scoped></style>
