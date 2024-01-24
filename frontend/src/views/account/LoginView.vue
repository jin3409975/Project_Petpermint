<script setup>
import { useAccountStore } from '@/stores/account.js'
import { ref } from 'vue'

const store = useAccountStore()
const useremail = ref(null)
const password = ref(null)

const logIn = function () {
    const logindata = {
        useremail: useremail.value,
        password: password.value
    }
    store.logIn(logindata)
}
</script>

<template>
    <div>
        <div>
            <!-- 이메일 , 비밀번호 입력 폼  -->
            <form @submit.prevent="logIn">
                <div class="logininput">
                    email
                    <input type="text" placeholder="email" v-model.trim="useremail" />
                </div>
                <div class="logininput">
                    password
                    <input type="text" placeholder="password" v-model.trim="password" />
                </div>

                <button type="submit">로그인</button>
            </form>
        </div>
    </div>
</template>

<style></style>
