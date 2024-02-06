<script setup>
import { onMounted } from 'vue'
import '../../assets/openvidu/openvidu-webcomponent-2.29.0'
import '../../assets/openvidu/openvidu-webcomponent-2.29.0.css'
import axios from 'axios'

onMounted(() => {
  const webComponent = document.querySelector('openvidu-webcomponent')
  console.log(webComponent)
  webComponent.addEventListener('onSessionCreated', (event) => {
    const session = event.detail
    session.on('connectionCreated', (e) => {
      console.log('connectionCreated', e)
    })

    session.on('streamDestroyed', (e) => {
      console.log('streamDestroyed', e)
    })

    session.on('streamCreated', (e) => {
      console.log('streamCreated', e)
    })

    session.on('sessionDisconnected', (event) => {
      console.warn('sessionDisconnected event')
      webComponent.style.display = 'none'
    })

    session.on('exception', (exception) => {
      console.error(exception)
    })
  })

  webComponent.addEventListener('onJoinButtonClicked', (event) => {})
  webComponent.addEventListener('onToolbarLeaveButtonClicked', (event) => {})
  webComponent.addEventListener('onToolbarCameraButtonClicked', (event) => {})
  webComponent.addEventListener('onToolbarMicrophoneButtonClicked', (event) => {})
  webComponent.addEventListener('onToolbarScreenshareButtonClicked', (event) => {})
  webComponent.addEventListener('onToolbarParticipantsPanelButtonClicked', (event) => {})
  webComponent.addEventListener('onToolbarChatPanelButtonClicked', (event) => {})
  webComponent.addEventListener('onToolbarFullscreenButtonClicked', (event) => {})
  webComponent.addEventListener('onParticipantCreated', (event) => {})
})

async function createToken(sessionId) {
  console.log('Start createToken ' + sessionId)
  const config = {
    method: 'post',
    url: 'https://i10b303.p.ssafy.io:5000/api/sessions/' + sessionId + '/connection',
    headers: {
      'Content-Type': 'application/json',
      Authorization: 'Basic ' + btoa('OPENVIDUAPP:' + 'MY_SECRET')
    }
  }

  const res = await axios(config)

  return res.data
}

async function getToken(mySessionId) {
  const sessionId = await createSession(mySessionId)
  return createToken(sessionId)
}

async function joinSession() {
  const sessionId = 'AAA-BBB-CCC'

  console.warn('SESSION ID', sessionId)

  var promiseResults = await Promise.all([getToken(sessionId), getToken(sessionId)])

  console.warn('promiseResults', promiseResults)

  var tokens = { webcam: promiseResults[0].token, screen: promiseResults[1].token }

  var webComponent = document.querySelector('openvidu-webcomponent')
  webComponent.tokens = tokens

  hideForm()
}

function hideForm() {
  var form = document.getElementById('main')
  form.style.display = 'none'
}

function createSession(sessionId) {
  console.log('createSession ', sessionId)
  const data = JSON.stringify({
    customSessionId: sessionId
  })

  const config = {
    method: 'post',
    url: 'https://i10b303.p.ssafy.io:5000/api/sessions',
    headers: {
      'Content-Type': 'application/json',
      Authorization: 'Basic ' + btoa('OPENVIDUAPP:' + 'MY_SECRET')
    },
    data: data
  }

  axios(config)
    .then(function (response) {
      console.log(response.data)
      return response.data
    })
    .catch(function (error) {
      console.log(error)
    })
}
</script>

<template>
  <div>
    <!-- Form to connect to a video-session -->
    <div id="main" style="text-align: center">
      <h1>Join a video session</h1>
      <form :onsubmit="joinSession()" style="padding: 80px; margin: auto">
        <p>
          <label>Session:</label>
          <input type="text" id="sessionName" value="SessionA" required />
        </p>
        <p>
          <label>User:</label>
          <input type="text" id="user" value="User1" />
        </p>
        <p>
          <input type="submit" value="JOIN" />
        </p>
      </form>
    </div>
    <openvidu-webcomponent></openvidu-webcomponent>
  </div>
</template>

<style scoped></style>
