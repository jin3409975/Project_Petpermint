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
    const comments = ref([])
    const videos = ref([])
    const video = ref()

    const communitywrite = function (formData) {
      return new Promise((resolve, reject) => {
        axios({
          method: 'post',
          url: API_URL + 'write',
          headers: {
            'Content-Type': 'multipart/form-data'
          },
          data: formData
        })
          .then((response) => {
            console.log(response)
          })
          .catch((error) => {
            reject(error)
          })
      })
    }
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
    const communityupdate = function (formData) {
      return new Promise((resolve, reject) => {
        axios({
          method: 'put',
          url: API_URL + 'update',
          headers: {
            'Content-Type': 'multipart/form-data'
          },
          data: formData
        })
          .then((response) => {
            console.log(response)
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
    const communitylikecancel = function (postId, userId) {
      return new Promise((resolve, reject) => {
        axios({
          method: 'put',
          url: API_URL + 'likecancel',
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
    const communitydelete = function (postId) {
      return new Promise((resolve, reject) => {
        axios({
          method: 'delete',
          url: API_URL + 'delete',
          data: {
            postId: postId
          }
        })
          .then((response) => {
            console.log(response)
          })
          .catch((error) => {
            reject(error)
          })
      })
    }
    const commentlist = function (postId) {
      return new Promise((resolve, reject) => {
        axios({
          method: 'get',
          url: API_URL + 'comment/data',
          params: {
            postId: postId
          }
        })
          .then((response) => {
            comments.value = response.data.postCommentList
            resolve(comments.value)
          })
          .catch((error) => {
            reject(error)
          })
      })
    }
    const commentwrite = function (postId, userId, content) {
      return new Promise((resolve, reject) => {
        axios({
          method: 'post',
          url: API_URL + 'comment/create',
          data: {
            userId: userId,
            content: content,
            postId: postId
          }
        })
          .then((response) => {
            console.log(response)
          })
          .catch((error) => {
            reject(error)
          })
      })
    }
    const commentdelete = function (commentNo, postId) {
      return new Promise((resolve, reject) => {
        axios({
          method: 'delete',
          url: API_URL + 'comment/delete',
          data: {
            commentNo: commentNo,
            postId: postId
          }
        })
          .then((response) => {
            console.log(response)
          })
          .catch((error) => {
            reject(error)
          })
      })
    }
    const videolist = () => {
      return new Promise((resolve, reject) => {
        axios({
          method: 'get',
          url: API_URL + 'video/list'
        })
          .then((response) => {
            videos.value = response.data
            resolve(videos.value)
          })
          .catch((error) => {
            reject(error)
          })
      })
    }
    const videocurrent = () => {
      return new Promise((resolve, reject) => {
        axios({
          method: 'get',
          url: API_URL + 'video/current'
        })
          .then((response) => {
            video.value = response.data
            resolve(video.value)
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
      communitylike,
      communitylikecancel,
      communitywrite,
      communitydelete,
      communityupdate,
      commentlist,
      comments,
      commentwrite,
      commentdelete,
      videolist,
      videos,
      videocurrent,
      video
    }
  },
  { persist: true }
)
