<script setup>
const props = defineProps(['article'])

const checkLike = async function (postId, userId) {
  try {
    article.value = await stores.communitydetail(postId)
  } catch (error) {
    console.error('Error fetching community detail:', error)
  }
}
</script>

<template>
  <v-container fluid class="w-50">
    <div class="mx-auto">{{ article.content }}</div>
    Likes: {{ article.likes }} Views: {{ article.hits }}
    <v-btn>Like</v-btn>
    <v-btn>Back</v-btn>
  </v-container>
</template>

<style scoped></style>
