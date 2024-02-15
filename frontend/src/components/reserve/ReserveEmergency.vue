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
      kakao.maps.event.addListener(markers.value[i], 'click', () => {
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

async function doReserve() {
  console.log(searchList.value[listIndex.value].dataNo)
  dialog.value = false
  await store.emergencyCreate(searchList.value[listIndex.value].dataNo)
}
</script>

<template>
  <div>
    <v-row style="padding-left: 200px; padding-right: 170px">
      <v-col cols="12" md="12" class="d-flex justify-center" style="height: 16vh">
        <div
          style="
            font-size: 45px;
            font-weight: 600;
            display: flex;
            justify-content: center;
            align-items: center;
          "
        >
          응급 예약
          <v-btn
            density="compact"
            icon
            style="box-shadow: none; padding-bottom: 40px; margin-left: 20px"
          >
            <v-icon style="color: rgb(233, 0, 0); font-size: 45px">mdi-map-marker-question</v-icon>
            <v-tooltip activator="parent" location="right" color="#E5E5E5"
              >자택 주소 기반 가까운 병원 5곳입니다. <br />
              원하시는 병원 카드를 선택하고 해당 마커를 선택해서 예약하실 수 있습니다.</v-tooltip
            >
          </v-btn>
        </div>
      </v-col>
      <v-col cols="12" md="7" style="width: 100%; height: 100%">
        <div
          ref="mapContainer"
          style="
            max-width: 630px;
            height: 500px;
            border: 2px solid #228b22;
            border-radius: 5px;
            margin-left: 10px;
          "
        ></div>
      </v-col>
      <v-col cols="12" md="5">
        <v-card
          variant="text"
          v-for="(data, index) in searchList"
          :key="data.dataNo"
          height="98px"
          style="margin-bottom: 3px; background-color: #228b2227; width: 90%"
        >
          <v-card-text
            @click="highlightMarker(index)"
            style="
              cursor: pointer;
              border-radius: 4px;
              font-size: 15px;
              padding-left: 25px;
              padding-right: 25px;
            "
          >
            <p style="font-size: 17px; margin-bottom: 10px; margin-top: 4px; font-weight: bold">
              {{ data.venName }}
            </p>
            <p style="margin-top: 15px">{{ data.roadAddr }}</p>
          </v-card-text>
        </v-card>
        <v-row>
          <div v-if="searchList.length == 0" class="center-content4">
            <p style="color: rgb(114, 114, 114)">
              해당 지역에 응급 예약이 가능한 동물병원이 없습니다.
            </p>
          </div>
        </v-row>
      </v-col>
    </v-row>

    <div>
      <v-dialog v-model="dialog" width="auto">
        <v-card>
          <v-card-text>
            <!-- {{ searchList[listIndex] }} -->
            <div v-if="searchList[listIndex]">
              <p><strong>병원명 :</strong> {{ searchList[listIndex].venName }}</p>
              <p><strong>도로 주소 :</strong> {{ searchList[listIndex].roadAddr }}</p>
              <p><strong>지번 주소 :</strong> {{ searchList[listIndex].lotAddr }}</p>
              <p><strong>전화번호 :</strong> {{ searchList[listIndex].phone }}</p>
              <p><strong>주차 :</strong> {{ searchList[listIndex].parkAvail }}</p>
            </div>
          </v-card-text>

          <v-card-text>
            {{ new Date().toISOString().split('T')[0] }}<br />
            응급 예약을 하시겠습니까?
          </v-card-text>
          <v-card-actions style="justify-content: center">
            <v-btn color="primary" @click="doReserve">예약</v-btn>
            <v-btn color="danger" @click="dialog = false">취소</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
  </div>
</template>

<style scoped>
.my-page ul {
  padding-left: 0 !important;
}
.center-content4 {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 500px;
  width: 500px;
  text-align: center;
}

.v-card-text {
  padding-left: 15px;
  padding-right: 15px;
  padding-top: 15px;
  padding-bottom: 5px;
}
</style>
