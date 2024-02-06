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
    const hit = ref()
    const likecheck = ref()

    const communitydetail = function (postId) {
      return new Promise((resolve, reject) => {
        axios({
          method: 'get',
          url: API_URL + 'data',
          params: {
            postId: postId,
            userId: 'user'
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
    const communitylist = function (page, postIds) {
      return new Promise((resolve, reject) => {
        axios({
          method: 'patch',
          url: API_URL + 'list',
          data: {
            page: page,
            postIds: postIds
          }
        })
          .then((response) => {
            articles.value = response.data.postList
            resolve(articles.value)
            console.log(articles.value)
          })
          .catch((error) => {
            reject(error)
          })
      })
    }
    const communitylike = function (postId, userId) {
      return new Promise((resolve, reject) => {
        axios({
          method: 'put',
          url: API_URL + 'like',
          data: {
            postId: postId,
            userId: userId
          }
        })
          .then((response) => {
            resolve(likecheck.value)
            console.log(response)
          })
          .catch((error) => {
            reject(error)
          })
      })
    }
    const communitylikecheck = function (postId, userId) {
      return new Promise((resolve, reject) => {
        axios({
          method: 'get',
          url: API_URL + 'likecheck',
          params: {
            postId: postId,
            userId: userId
          }
        })
          .then((response) => {
            likecheck.value = response.data.message
            resolve(likecheck.value)
            console.log(response)
          })
          .catch((error) => {
            reject(error)
          })
      })
    }
    const communityhit = function (postId) {
      return new Promise((resolve, reject) => {
        axios({
          method: 'put',
          url: API_URL + 'hit',
          data: {
            postId: postId
          }
        })
          .then((response) => {
            hit.value = response.data
            resolve(hit.value)
            console.log('hit')
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
      article,
      communityhit,
      communitylikecheck,
      likecheck,
      communitylike
    }
  },
  { persist: true }
)
