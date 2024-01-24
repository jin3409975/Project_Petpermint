<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import RegisterType from '@/components/register/RegisterType.vue'
import RegisterVet from '@/components/register/RegisterVet.vue'
import RegisterUser from '@/components/register/RegisterUser.vue'

const router = useRouter()
const selectedType = ref('')

const setType = (type) => {
    selectedType.value = type
    if (type === 'vet') {
        router.push({ name: 'register-vet' })
    } else if (type === 'user') {
        router.push({ name: 'register-user' })
    }
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
