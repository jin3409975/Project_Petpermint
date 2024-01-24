<script setup>
import { ref } from 'vue'
import RegisterType from '@/components/register/RegisterType.vue'
import RegisterVet from '@/components/register/RegisterVet.vue'
import RegisterUser from '@/components/register/RegisterUser.vue'

const selectedType = ref('')

const setType = (type) => {
    selectedType.value = type
}
</script>

<template>
    <div>
        <!-- vet과 user중에 하나를 선택 -->
        <RegisterType v-if="selectedType === ''" @select-type="setType" />
        <!-- vet이면 보여지는 컴포넌트 -->
        <RegisterVet v-if="selectedType === 'vet'" />
        <!-- user이면 보여지는 컴포넌트 -->
        <RegisterUser v-if="selectedType === 'user'" />
    </div>
</template>

<style></style>
