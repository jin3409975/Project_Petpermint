import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import { useRouter } from 'vue-router'

import axios from 'axios'
const { VITE_APP_SERVER_URI } = import.meta.env

export const useCommunityStore = defineStore(
  'community',
  () => {
    const router = useRouter()
    const API_URL = `${VITE_APP_SERVER_URI}/community/`
    // const token = ref(null)
    const result = ref(false)
    const articles = ref([])
    const article = ref()

    const communitydetail = function (postId) {
      return new Promise((resolve, reject) => {
        axios({
          method: 'get',
          url: API_URL + 'data',
          params: {
            postId: postId
          }
        })
          .then((response) => {
            article.value = response.data
            resolve(article.value)
            console.log(article)
          })
          .catch((error) => {
            reject(error)
          })
      })
    }
    const communitylist = function () {
      return new Promise((resolve, reject) => {
        axios({
          method: 'get',
          url: API_URL + 'list'
        })
          .then((response) => {
            articles.value = response.data.postList
            resolve(articles.value)
          })
          .catch((error) => {
            reject(error)
          })
      })
    }
    return {
      communitylist,
      articles,
      communitydetail,
      article
    }
  },
  { persist: true }
)
