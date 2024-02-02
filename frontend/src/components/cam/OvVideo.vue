<script>
export default {
  name: 'OvVideo',

  props: {
    streamManager: Object
  },

  mounted() {
    this.streamManager.addVideoElement(this.$el)
  }
}
</script>

<template>
  <video autoplay />
</template>
