<script setup>
import axios from 'axios'
import { onMounted, ref, computed } from 'vue'
const { VITE_APP_SERVER_URI } = import.meta.env

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
    url: VITE_APP_SERVER_URI + '/venue/init'
  }).then((r) => {
    console.log(r)
    sidoItems.value = r.data.result
  })
  console.log(sidoItems.value)
}

function getGungu() {
  axios({
    method: 'get',
    url: VITE_APP_SERVER_URI + '/venue/gungu',
    params: {
      sido: sidoValue.value
    }
  }).then((r) => (gunguItems.value = r.data.result))
}

function getMyundong() {
  axios({
    method: 'get',
    url: VITE_APP_SERVER_URI + '/venue/myundong',
    params: {
      sido: sidoValue.value,
      gungu: gunguValue.value
    }
  }).then((r) => (myundongItems.value = r.data.result))
}

var searchList = ref([])

const search = () => {
  markers.value = []
  positions.value = []
  var category = []
  categoryValue.value.forEach((v) => {
    category.push(v)
  })
  var data = category.join()
  axios({
    method: 'get',
    url: VITE_APP_SERVER_URI + '/venue/data',
    params: {
      sido: sidoValue.value,
      gungu: gunguValue.value,
      myundong: myundongValue.value,
      category: data,
      venName: venName.value
    }
  }).then((r) => {
    console.log(r)
    searchList.value = r.data.result
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
  data.value.forEach((area) => {
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

function highlightMarker(index) {
  console.log(searchList.value[index])
  // 현재 페이지에 따라 인덱스 조정
  const adjustedIndex = (currentPage.value - 1) * itemsPerPage + index

  for (let i = 0; i < markers.value.length; i++) {
    if (i !== adjustedIndex) {
      markers.value[i].setMap(null)
    } else {
      markers.value[i].setMap(map)
      map.setCenter(markers.value[i].getPosition())
      map.setLevel(1)
    }
  }
}
// 카드 페이지네이션
const currentPage = ref(1)
const itemsPerPage = 4

const paginatedItems = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage
  const end = start + itemsPerPage
  return searchList.value.slice(start, end)
})

const totalPages = computed(() => {
  return Math.ceil(searchList.value.length / itemsPerPage)
})
</script>

<template>
  <div>
    <h1 style="text-align: center; padding-top: 110px">반려동물 시설 및 센터 찾기</h1>
    <v-row style="padding-top: 30px; padding-left: 150px; padding-right: 150px">
      <v-col cols="12" md="2">
        <v-select
          v-model="sidoValue"
          :items="sidoItems"
          label="시/도"
          density="compact"
          variant="outlined"
          clearable
          base-color="#3E6263"
          color="#9DBC98"
          @update:model-value="getGungu"
        ></v-select>
      </v-col>
      <v-col cols="12" md="2">
        <v-select
          v-model="gunguValue"
          :items="gunguItems"
          label="군/구"
          density="compact"
          variant="outlined"
          base-color="#3E6263"
          color="#9DBC98"
          clearable
          @update:model-value="getMyundong"
        ></v-select>
      </v-col>
      <v-col cols="12" md="2">
        <v-select
          v-model="myundongValue"
          :items="myundongItems"
          label="동/읍/면"
          density="compact"
          base-color="#3E6263"
          color="#9DBC98"
          variant="outlined"
          clearable
        ></v-select>
      </v-col>

      <v-col cols="12" md="5">
        <v-text-field
          label="시설명 검색"
          v-model="venName"
          density="compact"
          base-color="#3E6263"
          color="#9DBC98"
          variant="outlined"
        ></v-text-field>
      </v-col>
      <v-col cols="12" md="1" class="text-right">
        <v-btn color="#638889" elevation="0" @click="search()">검색</v-btn>
      </v-col>

      <v-col cols="12" md="12">
        <v-select
          v-model="categoryValue"
          :items="categoryItems"
          chips
          label="카테고리"
          density="compact"
          base-color="#3E6263"
          color="#9DBC98"
          variant="outlined"
          multiple
          clearable
        ></v-select>
      </v-col>
    </v-row>

    <v-row
      style="padding-left: 150px; padding-right: 150px; padding-bottom: 60px; margin-top: 25px"
    >
      <v-col cols="12" md="8" style="width: 80%; height: 80%; margin-bottom: 50px">
        <div
          ref="mapContainer"
          style="width: 100%; height: 600px; border: 2px solid #638889; border-radius: 5px"
        ></div>
      </v-col>
      <v-col cols="12" md="4">
        <v-card
          variant="tonal"
          color="#638889"
          v-for="(data, index) in paginatedItems"
          :key="data.dataNo"
          style="margin-bottom: 10px"
        >
          <v-card-text
            @click="highlightMarker(index)"
            style="
              cursor: pointer;
              padding-bottom: 27px;
              border: 2px solid #638889;
              border-radius: 4px;
            "
          >
            <strong>{{ data.venName }}</strong>
            <br /><br />
            {{ data.roadAddr }}
            <br />
            {{ data.lotAddr }}
          </v-card-text>
        </v-card>
        <v-row>
          <v-pagination
            :total-visible="4"
            v-model="currentPage"
            :length="totalPages"
            class="my-4 my-page"
            v-if="searchList.length > 0"
            color="#8D6A14"
          ></v-pagination>
          <div v-if="searchList.length == 0" class="center-content4">
            <p style="color: rgb(114, 114, 114)">해당 지역에 반려동물 시설 및 센터가 없습니다.</p>
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
              <p><strong>장소명 :</strong> {{ searchList[listIndex].venName }}</p>
              <p><strong>카테고리 :</strong> {{ searchList[listIndex].category }}</p>
              <p><strong>도로 주소 :</strong> {{ searchList[listIndex].roadAddr }}</p>
              <p><strong>지번 주소 :</strong> {{ searchList[listIndex].lotAddr }}</p>
              <p><strong>전화번호 :</strong> {{ searchList[listIndex].phone }}</p>
              <p><strong>영업시간 :</strong> {{ searchList[listIndex].workTime }}</p>
              <p><strong>휴무일 :</strong> {{ searchList[listIndex].offTime }}</p>
              <p><strong>주차 :</strong> {{ searchList[listIndex].parkAvail }}</p>
            </div>
          </v-card-text>
          <v-card-actions>
            <v-btn color="primary" block @click="dialog = false">Close</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
  </div>
</template>

<style>
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
</style>
