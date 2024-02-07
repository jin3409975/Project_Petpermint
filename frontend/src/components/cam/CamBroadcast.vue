<script>
import $ from 'jquery'
import '../../../public/assets/openvidu/openvidu-webcomponent-2.29.0'
import '../../../public/assets/openvidu/openvidu-webcomponent-2.29.0.css'
export default {
  data() {
    return {
      sessionName: this.$route.params.clubId,
      participantName: sessionStorage.getItem('user_id'),
      APPLICATION_SERVER_URL: `https://i10b303.p.ssafy.io:5000/`,
      webComponent: null
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

      session.on('sessionDisconnected', (event) => {
        console.warn('sessionDisconnected event')
        this.joinSession()
      })

      session.on('exception', (exception) => {
        console.error(exception)
      })
    })

    // this.getUserName()
    this.joinSession()
  },
  methods: {
    async getUserName() {
      // const access_token = this.$store.state.access_token
      const config = {
        headers: {
          'Content-Type': 'application/json'
          // Authorization: `Bearer ${access_token}`
        }
      }

      const userId = sessionStorage.getItem('user_id')

      // await this.$axios.get(`/user-service/user/${userId}/userName`, config).then((res) => {
      //   this.webComponent.participantName = res.data
      // })
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
