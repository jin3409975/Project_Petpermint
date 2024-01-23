<template>
    <div>
        <form @submit.prevent="signUp">
          <div>
            <input type="text" placeholder="User email" v-model.trim="email">
            <input type="text" placeholder="Password" v-model.trim="password1">
            <input type="text" placeholder="Password Check" v-model.trim="password2">
            <input type="text" placeholder="Username" v-model.trim="username">
            <input type="text" placeholder="Address" v-model.trim="address">
            <input type="text" placeholder="Phone number" v-model.trim="phone">
          </div>          
          <input type="submit">
    </form>
    </div>
</template>

<script setup>
  import { ref } from 'vue'
  import { useAccountStore } from '@/stores/account.js'
  
  const store = useAccountStore()
  const username = ref(null)
  const email = ref(null)
  const address = ref(null)
  const phone = ref(null)
  const password1 = ref(null)
  const password2 = ref(null)
  
  const signUp = function () {
    const payload = {
        username: username.value,
        email: email.value,
        address: address.value,
        phone: phone.value,
        password1: password1.value,
        password2: password2.value,
    }
    store.signUp(payload)
  }
</script>

<style>

</style>