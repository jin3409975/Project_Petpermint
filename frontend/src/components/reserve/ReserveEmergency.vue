<script setup>
import axios from 'axios'
import { onMounted, ref } from 'vue'
import { useReserveStore } from '@/stores/reserve'

const store = useReserveStore()
const { VITE_APP_SERVER_URI } = import.meta.env

const serviceKey =
  'YmLOCrrmdh8DGzVmGbT0Xu9jfrsXGgdJ6GsHZ%2FDGzKW82R5KrnlJU0dar8fqQ0nYXpb9fjuYjDQx1QuSigbTkw%3D%3D'
const areaUrl =
  'https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=' +
  serviceKey +
  '&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json'

const src =
  '//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=4fd0aaf2d6c6b86333a1b8f0115bac76&libraries=services,clusterer,drawing'

var geocoder
var map = ref(null)
const mapContainer = ref(null)
onMounted(() => {
  setMap()
})

function setMap() {
  // 카카오 맵 스크립트를 동적으로 로드합니다.
  const script = document.createElement('script')
  script.onload = () => initializeMap() // 스크립트 로드가 완료되면 지도를 초기화합니다.
  script.src = src
  document.head.appendChild(script)
}

function initializeMap() {
  // 카카오 맵 API가 로드된 후 호출될 함수입니다.
  kakao.maps.load(() => {
    const options = {
      center: new kakao.maps.LatLng(37.5665, 126.978), // 지도의 중심좌표
      level: 3 // 지도의 확대 레벨
    }
    // 지도를 생성합니다.
    map = new kakao.maps.Map(mapContainer.value, options)
    geocoder = new kakao.maps.services.Geocoder()
    search()
  })
}

var searchList = ref([])
var coords
const search = () => {
  markers.value = []
  positions.value = []
  axios({
    method: 'get',
    url: VITE_APP_SERVER_URI + '/reserve/emergency/init',
    params: {
      userId: localStorage.getItem('useremail')
    }
  }).then((r) => {
    console.log(r.data.address)
    geocoder.addressSearch(r.data.address, function (result, status) {
      console.log('asdasdasdtbhjtbnkbetn', status)
      if (status === kakao.maps.services.Status.OK) {
        coords = new kakao.maps.LatLng(result[0].y, result[0].x)
        axios({
          method: 'get',
          url: VITE_APP_SERVER_URI + '/reserve/emergency/list',
          params: {
            lat: coords.Ma,
            lon: coords.La
          }
        }).then((r) => {
          console.log(r)
          searchList.value = r.data.result
          makeList(r.data.result)
        })
      }
    })
  })
}
var markers = ref([])
var positions = [] // marker 배열.

function makeList(data) {
  console.log('지도 출력')
  console.log(data)

  positions = []
  data.forEach((area) => {
    let markerInfo = {
      title: area.venName,
      latlng: new kakao.maps.LatLng(area.lat, area.lon)
    }
    positions.push(markerInfo)
  })

  console.log('asdsadasd', positions)

  displayMarker()
}

function displayMarker() {
  // 마커 이미지의 이미지 주소입니다
  var imageSrc = 'http://t1.daumcdn.net/localimg/localimages/07/2018/pc/img/marker_spot.png'

  for (let i = 0; i < positions.length; i++) {
    //console.log(positions[i])
    // 마커 이미지의 이미지 크기 입니다
    console.log(i, positions[i])
    var imageSize = new kakao.maps.Size(24, 35)

    // 마커 이미지를 생성합니다
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize)

    // 마커를 생성합니다
    markers.value.push(
      new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: positions[i].latlng, // 마커를 표시할 위치
        title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        image: markerImage // 마커 이미지
      })
    )

    for (let i = 0; i < markers.value.length; i++) {
      kakao.maps.event.addListener(markers.value[i], 'click', function () {
        console.log(i, searchList[i])
        dialog.value = true
        listIndex.value = i
      })
    }
  }

  markers.value.push(
    new kakao.maps.Marker({
      map: map, // 마커를 표시할 지도
      position: coords, // 마커를 표시할 위치
      title: 'Home', // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
      image: new kakao.maps.MarkerImage(imageSrc, new kakao.maps.Size(48, 70)) // 마커 이미지
    })
  )

  // 첫번째 검색 정보를 이용하여 지도 중심을 이동 시킵니다
  map.setCenter(coords)
  map.setLevel(5)
}

function moveCenter(lat, lng) {
  map.setCenter(new kakao.maps.LatLng(lat, lng))
}

const venName = ref('')
const dialog = ref(false)
const listIndex = ref()

function highlightMarker(index) {
  console.log(searchList.value[index])
  for (let i = 0; i < markers.value.length; i++) {
    if (i != index) {
      markers.value[i].setMap(null)
    }
    if (i == index) {
      markers.value[i].setMap(map)
    }
  }
  map.setCenter(new kakao.maps.LatLng(searchList.value[index].lat, searchList.value[index].lon))
  map.setLevel(1)
}

function doReserve() {
  console.log(searchList.value[listIndex.value].dataNo)
  dialog.value = false
  store.emergencyCreate(searchList.value[listIndex.value].dataNo)
}
</script>

<template>
  <div>
    <v-row style="padding-left: 150px; padding-right: 150px; padding-bottom: 30px">
      <v-col cols="12" md="8" style="width: 80%; height: 80%">
        <div ref="mapContainer" style="width: 100%; height: 700px"></div>
      </v-col>
      <v-col>
        <v-card
          elevation="0"
          variant="outlined"
          v-for="(data, index) in searchList"
          :key="data.dataNo"
        >
          <v-card-text @click="highlightMarker(index)" style="cursor: pointer">
            {{ data.venName }}
            <br />
            <br />
            {{ data.roadAddr }}
            <br />
            {{ data.lotAddr }}
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>

    <div>
      <v-dialog v-model="dialog" width="auto">
        <v-card>
          <v-card-text>
            {{ searchList[listIndex] }}
          </v-card-text>
          <v-card-actions style="justify-content: center">
            <v-btn color="primary" @click="doReserve">예약 하기</v-btn>
            <v-btn color="danger" @click="dialog = false">취소</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
  </div>
</template>

<style scoped></style>
