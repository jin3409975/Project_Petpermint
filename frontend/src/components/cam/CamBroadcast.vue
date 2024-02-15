<script>
import $ from 'jquery'
import './openviduBroadcast/openvidu-webcomponent-2.29.0'
import './openviduBroadcast/openvidu-webcomponent-2.29.0.css'
import { useAccountStore } from '@/stores/account'

const { VITE_APP_SERVER_URI } = import.meta.env

export default {
  data() {
    return {
      sessionName: this.$route.params.sessionId,
      participantName: '',
      userEmail: localStorage.getItem('useremail'),
      APPLICATION_SERVER_URL: `${VITE_APP_SERVER_URI}/`,
      webComponent: null,
      adminAccount: 'ssafyjam@gmail.com'
    }
  },
  mounted() {
    this.webComponent = this.$refs.webComponent

    this.webComponent.addEventListener('onSessionCreated', (event) => {
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

      session.on('sessionDisconnected', (e) => {
        console.log('sessionDisconnected', e)
        this.$router.push({ name: 'lifecare-broadcast-enter' })
      })

      session.on('exception', (exception) => {
        console.error(exception)
      })
    })

    this.getUserName()
    this.joinSession()
  },
  methods: {
    async getUserName() {
      const accountStores = useAccountStore()
      const userEmail = localStorage.getItem('useremail')
      await accountStores.getnormalprofile(userEmail)
      this.participantName = accountStores.userdata.userName
      this.webComponent.participantName = this.participantName
    },
    async joinSession() {
      const tokens = await Promise.all([
        this.getToken(this.sessionName),
        this.getToken(this.sessionName)
      ])
      this.webComponent.tokens = {
        webcam: tokens[0],
        screen: tokens[1]
      }
    },
    async getToken(mySessionId) {
      const sessionId = await this.createSession(mySessionId)
      return this.createToken(sessionId)
    },
    createSession(sessionId) {
      // const access_token = this.$store.state.access_token
      const config = {
        headers: {
          'Content-Type': 'application/json'
          // Authorization: `Bearer ${access_token}`
        }
      }

      return new Promise((resolve, reject) => {
        $.ajax({
          type: 'POST',
          url: this.APPLICATION_SERVER_URL + 'api/sessions',
          config: config,
          data: JSON.stringify({ customSessionId: sessionId }),
          headers: { 'Content-Type': 'application/json' },
          success: (response) => resolve(response),
          error: (error) => reject(error)
        })
      })
    },
    createToken(sessionId) {
      // const access_token = this.$store.state.access_token
      const config = {
        headers: {
          'Content-Type': 'application/json'
          // Authorization: `Bearer ${access_token}`
        }
      }

      return new Promise((resolve, reject) => {
        $.ajax({
          type: 'POST',
          url: this.APPLICATION_SERVER_URL + 'api/sessions/' + sessionId + '/connections',
          config: config,
          data: JSON.stringify({}),
          headers: { 'Content-Type': 'application/json' },
          success: (response) => resolve(response),
          error: (error) => reject(error)
        })
      })
    }
  },
  beforeDestroy() {
    this.webComponent.disconnect()
    this.webComponent.tokens = null
  }
}
</script>

<template style="display: flex; justify-content: center; height: 100%; width: 100%">
  <div id="main" style="text-align: center; width: 100%; height: 100%">
    <openvidu-webcomponent
      ref="webComponent"
      prejoin="false"
      :audio-muted="this.userEmail === this.adminAccount ? false : true"
      :video-muted="this.userEmail === this.adminAccount ? false : true"
      leave-button="false"
      recording-button="false"
      toolbar-screenshare-button="false"
      toolbar-recording-button="false"
      toolbar-broadcasting-button="false"
      toolbar-activities-panel-button="false"
      toolbar-display-logo="false"
      toolbar-display-session-name="false"
      stream-frame-rate="120"
      stream-resolution="'1280x720'"
      style="height: 100vh; width: 100%; display: block !important"
    >
    </openvidu-webcomponent>
  </div>
</template>
<style>
:root {
  --ov-primary-color: #303030;
  --ov-secondary-color: #3e3f3f;
  --ov-tertiary-color: #598eff;
  --ov-warn-color: #eb5144;
  --ov-accent-color: #ffae35;
  --ov-light-color: #e6e6e6;

  --ov-logo-background-color: #3a3d3d;
  --ov-text-color: #ffffff;

  --ov-panel-text-color: #1d1d1d;
  --ov-panel-background: #ffffff;

  --ov-buttons-radius: 50%;
  --ov-leave-button-radius: 10px;
  --ov-video-radius: 5px;
  --ov-panel-radius: 5px;
}
</style>
./openviduConsult/openvidu-webcomponent-2.29.0./openviduConsult/openvidu-webcomponent-2.29.0.css
