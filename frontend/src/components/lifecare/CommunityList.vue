<script setup>
import { ref, onBeforeMount } from 'vue'
import axios from 'axios'
const props = defineProps(['article'])
const files = ref([{}])

onBeforeMount(() => {})
</script>

<template>
  <v-card
    class="card"
    :title="article.userName"
    :subtitle="article.registTime"
    :text="article.content"
  >
    <template v-slot:prepend>
      <v-avatar size="30">
        <img v-if="article.picture !== '0'" :src="article.picture" style="max-width: 100%" />
        <v-else>
          <img src="/assets/img/default_profile.png" style="max-width: 100%" />
        </v-else>
      </v-avatar>
    </template>
    <template v-slot:append>
      <v-icon icon="mdi-format-align-justify" color="white"></v-icon>
    </template>
    <v-card-actions>
      <v-col>
        <v-row class="d-flex justify-content-center">
          <img class="mx-auto" :src="article.url" style="max-width: 60%" />
        </v-row>
        <v-row>
          <v-col class="text-right">
            <div class="text-right" style="margin-top: 10px">
              <span style="font-size: 12px"> 좋아요 {{ article.likes }}개 &nbsp;</span>
              <span style="font-size: 12px"> 조회수 {{ article.hits }}개 </span>
              <span>
                <router-link
                  :to="{ name: 'lifecare-community-detail', params: { postId: article.postId } }"
                  class="article-title link-dark"
                >
                  <v-btn style="margin-left: 10px"> 더 알아보기 </v-btn>
                </router-link>
              </span>
            </div>
          </v-col>
        </v-row>
      </v-col>
    </v-card-actions>
  </v-card>
</template>

<style scoped>
.card {
  margin-bottom: 30px;
  border: 1px solid #e0e0e0;
}
a {
  text-decoration: none;
}
p {
  margin: 0px;
}
</style>
