<script setup>
import CommunityList from '../components/lifecare/CommunityList.vue'
import { ref, onMounted } from 'vue'
import axios from 'axios'

const articles = ref()

onMounted(() => {
  getReviews()
})

const getReviews = function () {
  var url = '/community/list'

  async function getTodo(url) {
    const response = await axios.get(url)
    console.log(response)
    //articles.value = response.data.resdata.list;
    //totalpage.value = response.data.resdata.totalpage;
  }
  getTodo(url).catch((error) => {
    console.log(error)
  })
}
</script>

<template>
  <v-container fluid class="w-50"> hello </v-container>
</template>

<style></style>
