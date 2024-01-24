<script setup>
import { ref } from 'vue'
import RegisterType from '@/components/register/RegisterType.vue'
import RegisterVet from '@/components/register/RegisterVet.vue'
import RegisterUser from '@/components/register/RegisterUser.vue'

const selectedType = ref(null)

const setType = (type) => {
    selectedType.value = type
}
</script>

<template>
    <div>
        <!-- vet과 user중에 하나를 선택 -->
        <RegisterType @type-selected="setType" />
        <!-- vet이면 보여지는 컴포넌트 -->
        <RegisterVet v-if="selectedType.value === 'vet'" />
        <!-- user이면 보여지는 컴포넌트 -->
        <RegisterUser v-if="selectedType.value === 'user'" />
    </div>
</template>

<style></style>
