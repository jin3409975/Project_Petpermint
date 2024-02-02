<script setup>
import { ref, onBeforeMount } from 'vue'
import axios from 'axios'
const props = defineProps({ article: Object })
const files = ref([{}])

onBeforeMount(() => {
  var filelist = `/api/reviewboardfilelist?articleno=${props.article.articleno}`

  async function getfiles(filelist) {
    const response = await axios.get(filelist)
    files.value = response.data.resdata
  }
  getfiles(filelist).catch((error) => {
    console.log(error)
  })
})
</script>

<template>
  <v-card title="Card title" subtitle="Subtitle" text="...">
    <v-card-actions>
      <v-btn>Click me</v-btn>
    </v-card-actions>
  </v-card>
</template>

<style scoped>
a {
  text-decoration: none;
}
p {
  margin: 0px;
}
</style>
