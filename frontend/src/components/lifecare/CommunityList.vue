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
          <v-avatar size="36">
            <img v-if="article.picture !== '0'" :src="article.picture" />
            <v-else>
              <img  src="/assets/img/default_profile.png"/>
            </v-else>
          </v-avatar>
        </template>
      <v-card-actions>
        <row>
          <col class="d-flex justify-content-center">
          
            
          </col>
          <col class="d-flex justify-content-center">
          <v-layout justify-center align-center>
            <v-flex shrink>
            <img class="mx-auto justify-content-center" :src="article.url" style="max-width: 80%" />
          </v-flex>
            </v-layout>
          </col> 
          <col>
            <div class="text-right" style="margin-top:10px">
                <span style="font-size:12px"> 좋아요 {{ article.likes }}개 &nbsp;</span>
                <span style="font-size:12px"> 조회수 {{ article.hits }}개 </span>
              <span> 
                <router-link :to="{ name: 'lifecare-community-detail', params: { postId: article.postId } }"
                       class="article-title link-dark">
                  <v-btn style="margin-left:10px"> 더 알아보기 </v-btn>
                </router-link>
              </span>
            </div>
          </col>
        </row>
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
