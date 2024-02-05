<script setup>
import axios from 'axios'
import { onMounted, ref } from 'vue'

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
  axios
    .get(areaUrl)
    .then((response) => {
      makeOption(response.data)
    })
    .catch((error) => {
      console.log(error)
    })
}

function makeOption(data) {
  console.log(data)
  let areas = data.response.body.items.item
  console.log(areas)

  let sel = document.querySelector('#search-area')
  areas.forEach((area) => {
    let opt = document.createElement('option')
    opt.setAttribute('value', area.code)
    opt.appendChild(document.createTextNode(area.name))

    sel.appendChild(opt)
  })
}

const Search = () => {
  markers.value.forEach((marker) => {
    marker.setMap(null)
  })
  positions = ref([])
  let baseUrl = '/api/enjoyboardlist?'

  let areaCode = document.getElementById('search-area')?.value
  let contentTypeId = document.getElementById('search-content-id')?.value
  let keyword = document.getElementById('search-keyword').value

  console.log(areaCode, contentTypeId, keyword)

  if (parseInt(areaCode)) baseUrl += '&sido=' + areaCode
  if (parseInt(contentTypeId)) baseUrl += '&content=' + contentTypeId
  if (keyword) {
    baseUrl += '&word=' + keyword
  }

  console.log(baseUrl)

  fetch(baseUrl)
    .then((response) => response.json())
    .then((data) => makeList(data))
}
var markers = ref([])
var positions = ref([]) // marker 배열.

function makeList(data) {
  console.log('지도 출력')
  console.log(data)

  let tripList = ''
  positions = []
  data.resdata.forEach((area) => {
    if (document.getElementById('search-keyword').value == '') {
      if (data.resdata.length >= 50) {
        if (data.resdata.length <= 300) {
          if (Math.random() > 0.3) return
        } else if (data.resdata.length <= 500) {
          if (Math.random() > 0.2) return
        } else {
          if (Math.random() > 0.1) return
        }
      }
    }

    //console.log(area);
    tripList +=
      "<li class='carousel-single-wrap' onclick='moveCenter(" +
      area.latitude +
      ',' +
      area.longitude +
      ")'>" +
      "<p><img src=' " +
      area.first_image +
      "' height='150px' style='border-radius: 10px'></p>" +
      '<p>' +
      area.title +
      '</p>' +
      '<p>' +
      area.addr1 +
      ' ' +
      area.addr2 +
      '</p>' +
      '<p>' +
      area.latitude +
      '</p>' +
      '<p>' +
      area.longitude +
      '</p>' +
      '</li>'

    let markerInfo = {
      title: area.title,
      latlng: new kakao.maps.LatLng(area.latitude, area.longitude)
    }
    positions.push(markerInfo)
  })

  console.log(positions)
  document.querySelector('.carousel-flex').innerHTML = tripList
  displayMarker()
}

function displayMarker() {
  // 마커 이미지의 이미지 주소입니다
  var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png'

  for (var i = 0; i < positions.length; i++) {
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
  }

  // 첫번째 검색 정보를 이용하여 지도 중심을 이동 시킵니다
  map.setCenter(positions[0].latlng)
  map.setLevel(7)
}

function moveCenter(lat, lng) {
  map.setCenter(new kakao.maps.LatLng(lat, lng))
}

const items = [
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
]
const value = ref([])
const test = function () {
  console.log(value.value)
}
</script>

<template>
  <div>
    <div style="margin: 10px">
      <v-select
        v-model="value"
        :items="items"
        chips
        label="카테고리"
        multiple
        variant="underlined"
        inline
        clearable
      ></v-select>
      <v-btn style="margin-top: 10px" @click="test">test</v-btn>
    </div>
    <div>
      <select class="map-select" name="search-area" id="search-area">
        <option value="">시도선택</option>
      </select>

      <input
        class="map-select"
        name="search-keyword"
        id="search-keyword"
        placeholder="검색어를 입력해주세요."
      />
      <div style="width: 80%; height: 80%">
        <div ref="mapContainer" style="width: 100%; height: 700px"></div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
