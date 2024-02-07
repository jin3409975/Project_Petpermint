<script setup>
import { ref } from 'vue'

import { reactive, computed } from 'vue'
import dynamics from 'dynamics.js'
const sideWidth = 60

let isDragging = false
const start = { x: 0, y: 0 }
const c = reactive({ x: sideWidth / 2, y: 0 })

const headerPath = computed(() => {
  const radius = 20
  const width = c.x
  const height = 80
  return `M0,0 
          H${width - radius} Q${width},${0} ${width},${radius} 
          V${height - radius} Q${width},${height} ${width - radius},${height} 
          H0 Z`
})

const textVisibleOnClick = ref(true)

const contentPosition = computed(() => {
  const dx = c.x - sideWidth / 2
  const dampen = dx > 0 ? 2 : 4
  return {
    transform: `translate(${dx / dampen}px, 0)`
  }
})

function startDrag(e) {
  e = e.changedTouches ? e.changedTouches[0] : e
  isDragging = true
  start.x = e.pageX
  start.y = e.pageY
  textVisibleOnClick.value = false
}
const dialog = ref(false)
function onDrag(e) {
  console.log('Dragging')
  e = e.changedTouches ? e.changedTouches[0] : e
  if (isDragging) {
    const dx = e.pageX - start.x
    const dampen = dx > 0 ? 1.5 : 4
    c.x = sideWidth / 2 + dx / dampen
    if (Math.abs(dx) > 40) {
      console.log('Opening dialog')
      // Ensure stopDrag logic is executed to reset the position
      stopDrag() // This will also set isDragging to false
      // Then show the dialog
      dialog.value = true
    }
  }
}

function stopDrag() {
  if (isDragging) {
    isDragging = false
    textVisibleOnClick.value = true
    dynamics.animate(
      c,
      { x: sideWidth / 2, y: 0 },
      { type: dynamics.spring, duration: 700, friction: 280 }
    )
  }
}
function toggleTextVisibility() {
  textVisibleOnClick.value = !textVisibleOnClick.value
}

const showHoverText = ref(false)
const handleMouseEnter = () => {
  showHoverText.value = true
}
const handleMouseLeave = () => {
  stopDrag()
  showHoverText.value = false
}
</script>

<template>
  <div>
    <div
      class="draggable"
      @mousedown="startDrag"
      @touchstart="startDrag"
      @mousemove="onDrag"
      @touchmove="onDrag"
      @mouseup="stopDrag"
      @touchend="stopDrag"
      @mouseleave="handleMouseLeave"
    >
      <svg class="bg" width="100" height="80">
        <path :d="headerPath" fill="#D2E0FB"></path>
      </svg>
      <div class="header" @mouseenter="handleMouseEnter">My 예약표</div>
    </div>
    <div v-if="showHoverText && textVisibleOnClick" class="text-rot">
      오른쪽으로 잡아당기세요
      <div class="arrow">
        <span></span>
        <span></span>
        <span></span>
      </div>
    </div>
    <v-dialog v-model="dialog" persistent width="auto">
      <v-card>
        <v-card-title class="text-h5"> Use Google's location service? </v-card-title>
        <v-card-text
          >Let Google help apps determine location. This means sending anonymous location data to
          Google, even when no apps are running.</v-card-text
        >
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green-darken-1" text @click="dialog = false"> Disagree </v-btn>
          <v-btn color="green-darken-1" text @click="dialog = false"> Agree </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<style scoped>
.text-rot {
  z-index: 1000;
  position: fixed;
  top: 50%;
  left: 0;
  transform: translateY(-120%) translateX(20%);
  font-weight: bold;
  color: #fcfcfca5;
}

.draggable {
  background-color: transparent;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.15);
  width: 60px;
  height: 80px;
  overflow: hidden;
  text-align: center;
  font-size: 14px;
  font-weight: 300;
  user-select: none;
  box-shadow: none;
  cursor: pointer;
  z-index: 1000;
  position: fixed;
  top: 50%;
  left: 0;
  transform: translateY(-50%);
}
.bg {
  position: absolute;
  top: 0;
  left: 0;
  z-index: 0;
}
.header,
.content {
  position: relative;
  z-index: 1;
  box-sizing: border-box;
}
.header {
  width: 120px;
  color: #474747;
  height: 30px;
  font-size: 13px;
  font-weight: bold;
  transform: rotate(90deg) translateY(170%) translateX(20%);
}
.arrow {
  position: absolute;
  top: 120%;
  left: 24%;
  transform: translate(-50%, -50%);
  transform: rotate(-90deg);
  cursor: pointer;
}

.arrow span {
  display: block;
  width: 1.1vw;
  height: 1.1vw;
  border-bottom: 4px solid #fcfcfca5;
  border-right: 4px solid #fcfcfca5;
  transform: rotate(45deg);
  margin: -10px;
  animation: animate 2s infinite;
}

.arrow span:nth-child(2) {
  animation-delay: -0.2s;
}

.arrow span:nth-child(3) {
  animation-delay: -0.4s;
}

@keyframes animate {
  0% {
    opacity: 0;
    transform: rotate(45deg) translate(-20px, -20px);
  }
  50% {
    opacity: 1;
  }
  100% {
    opacity: 0;
    transform: rotate(45deg) translate(20px, 20px);
  }
}
</style>
