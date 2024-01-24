<script setup>
const emit = defineEmits(['select-type'])

const selectType = (type) => {
    emit('select-type', type)
}
</script>

<template>
    <div>
        <v-btn prepend-icon="$vuetify" variant="outlined" @click="selectType('vet')">
            수의사
        </v-btn>
        <v-btn prepend-icon="$vuetify" variant="outlined" @click="selectType('user')">
            일반 유저
        </v-btn>
    </div>
</template>

<style scoped></style>
