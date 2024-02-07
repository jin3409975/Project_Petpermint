<script setup>
import axios from 'axios'
import { onMounted, ref } from 'vue'
const { VITE_SERVER_URI } = import.meta.env

const serviceKey =
  'YmLOCrrmdh8DGzVmGbT0Xu9jfrsXGgdJ6GsHZ%2FDGzKW82R5KrnlJU0dar8fqQ0nYXpb9fjuYjDQx1QuSigbTkw%3D%3D'
const areaUrl =
  'https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=' +
  serviceKey +
  '&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json'

const src =
  '//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=4fd0aaf2d6c6b86333a1b8f0115bac76&libraries=services,clusterer,drawing'
var map = ref(null)
const mapContainer = ref(null)
onMounted(() => {
  sidoCall()
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
  })
}

function sidoCall() {
  axios({
    method: 'get',
    url: VITE_SERVER_URI + '/venue/init'
  }).then((r) => (sidoItems.value = r.data.result))
}

function getGungu() {
  axios({
    method: 'get',
    url: VITE_SERVER_URI + '/venue/gungu',
    params: {
      sido: sidoValue.value
    }
  }).then((r) => (gunguItems.value = r.data.result))
}

function getMyundong() {
  axios({
    method: 'get',
    url: VITE_SERVER_URI + '/venue/myundong',
    params: {
      sido: sidoValue.value,
      gungu: gunguValue.value
    }
  }).then((r) => (myundongItems.value = r.data.result))
}

var searchList = []
const search = () => {
  var category = []
  categoryValue.value.forEach((v) => {
    category.push(v)
  })
  var data = category.join()
  axios({
    method: 'get',
    url: 'http://localhost:8080/venue/data',
    params: {
      sido: sidoValue.value,
      gungu: gunguValue.value,
      myundong: myundongValue.value,
      category: data,
      venName: venName.value
    }
  }).then((r) => {
    console.log(r)
    searchList = r.data.result
    makeList(searchList)
  })
  // markers.value.forEach((marker) => {
  //   marker.setMap(null)
  // })
  // positions = ref([])
  // let baseUrl = '/api/enjoyboardlist?'
  // let areaCode = document.getElementById('search-area')?.value
  // let contentTypeId = document.getElementById('search-content-id')?.value
  // let keyword = document.getElementById('search-keyword').value
  // console.log(areaCode, contentTypeId, keyword)
  // if (parseInt(areaCode)) baseUrl += '&sido=' + areaCode
  // if (parseInt(contentTypeId)) baseUrl += '&content=' + contentTypeId
  // if (keyword) {
  //   baseUrl += '&word=' + keyword
  // }
  // console.log(baseUrl)
  // fetch(baseUrl)
  //   .then((response) => response.json())
  //   .then((data) => makeList(data))
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
  var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png'

  for (var i = 0; i < positions.length; i++) {
    //console.log(positions[i])
    // 마커 이미지의 이미지 크기 입니다
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
        console.log(searchList[i])
        dialog.value = true
        listIndex.value = i
      })
    }
  }

  // 첫번째 검색 정보를 이용하여 지도 중심을 이동 시킵니다
  map.setCenter(positions[0].latlng)
  map.setLevel(7)
}

function moveCenter(lat, lng) {
  map.setCenter(new kakao.maps.LatLng(lat, lng))
}

const sidoItems = ref([])
const sidoValue = ref()

const gunguItems = ref([])
const gunguValue = ref()

const myundongItems = ref([])
const myundongValue = ref()

const categoryItems = ref([
  '호텔',
  '동물약국',
  '미용',
  '미술관',
  '반려동물용품',
  '문예회관',
  '여행지',
  '동물병원',
  '위탁관리',
  '카페',
  '식당',
  '박물관',
  '펜션'
])
const categoryValue = ref([])
const venName = ref('')
const dialog = ref(false)
const listIndex = ref()
</script>

<template>
  <div>
    <div>
      <v-select
        v-model="sidoValue"
        :items="sidoItems"
        label="시도"
        variant="underlined"
        clearable
        @update:model-value="getGungu"
      ></v-select>
    </div>
    <div>
      <v-select
        v-model="gunguValue"
        :items="gunguItems"
        label="군구"
        variant="underlined"
        clearable
        @update:model-value="getMyundong"
      ></v-select>
    </div>
    <div>
      <v-select
        v-model="myundongValue"
        :items="myundongItems"
        label="동읍면"
        variant="underlined"
        clearable
      ></v-select>
    </div>
    <div>
      <v-select
        v-model="categoryValue"
        :items="categoryItems"
        chips
        label="카테고리"
        variant="underlined"
        multiple
        clearable
      ></v-select>
    </div>
    <div>
      <v-text-field label="이름 검색" v-model="venName"></v-text-field>
    </div>
    <div>
      <v-btn @click="search()">검색</v-btn>
    </div>
    <div style="margin: 10px">
      <div style="width: 80%; height: 80%">
        <div ref="mapContainer" style="width: 100%; height: 700px"></div>
      </div>
    </div>
    <div>
      <v-dialog v-model="dialog" width="auto">
        <v-card>
          <v-card-text>
            {{ searchList[listIndex] }}
          </v-card-text>
          <v-card-actions>
            <v-btn color="primary" block @click="dialog = false">Close Dialog</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
  </div>
</template>

<style scoped></style>
