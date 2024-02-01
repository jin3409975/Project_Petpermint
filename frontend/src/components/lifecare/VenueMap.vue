<script setup>
import axios from 'axios';
import { onMounted, ref } from 'vue';

const serviceKey = 'YmLOCrrmdh8DGzVmGbT0Xu9jfrsXGgdJ6GsHZ%2FDGzKW82R5KrnlJU0dar8fqQ0nYXpb9fjuYjDQx1QuSigbTkw%3D%3D';
const areaUrl =
    'https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=' +
    serviceKey +
    '&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json';

const src =
    '//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=4fd0aaf2d6c6b86333a1b8f0115bac76&libraries=services,clusterer,drawing';
var map = ref(null);
const mapContainer = ref(null);
onMounted(() => {
    sidoCall();
    setMap();
});

function setMap() {
    // 카카오 맵 스크립트를 동적으로 로드합니다.
    const script = document.createElement('script');
    script.onload = () => initializeMap(); // 스크립트 로드가 완료되면 지도를 초기화합니다.
    script.src = src;
    document.head.appendChild(script);
}

function initializeMap() {
    // 카카오 맵 API가 로드된 후 호출될 함수입니다.
    kakao.maps.load(() => {
        const options = {
            center: new kakao.maps.LatLng(37.5665, 126.978), // 지도의 중심좌표
            level: 3, // 지도의 확대 레벨
        };
        // 지도를 생성합니다.
        map = new kakao.maps.Map(mapContainer.value, options);
    });
}

function sidoCall() {
    axios
        .get(areaUrl)
        .then((response) => {
            makeOption(response.data);
        })
        .catch((error) => {
            console.log(error);
        });
}

function makeOption(data) {
    console.log(data);
    let areas = data.response.body.items.item;
    console.log(areas);

    let sel = document.querySelector('#search-area');
    areas.forEach((area) => {
        let opt = document.createElement('option');
        opt.setAttribute('value', area.code);
        opt.appendChild(document.createTextNode(area.name));

        sel.appendChild(opt);
    });
}

const Search = () => {
  markers.value.forEach((marker) => {
    marker.setMap(null);
  });
        positions = ref([]);
        let baseUrl = "/api/enjoyboardlist?";
        
        let areaCode = document.getElementById("search-area")?.value;
        let contentTypeId = document.getElementById("search-content-id")?.value;
        let keyword = document.getElementById("search-keyword").value;

        console.log(areaCode, contentTypeId, keyword);

        if (parseInt(areaCode)) baseUrl += "&sido=" + areaCode;
        if (parseInt(contentTypeId))
        	baseUrl += "&content=" + contentTypeId;
        if (keyword) {
        	baseUrl += "&word=" + keyword;
        }
		
        console.log(baseUrl);
        
        fetch(baseUrl)
          .then((response) => response.json())
          .then((data) => makeList(data));

};
      var markers = ref([]);
      var positions = ref([]); // marker 배열.
      
      function makeList(data) {
    	  console.log("지도 출력");
        console.log(data);
        
        let tripList = "";
        positions = [];
        data.resdata.forEach((area) => {
          if (document.getElementById("search-keyword").value == '') {
            if (data.resdata.length >= 50) {
              if (data.resdata.length <= 300) {
                if (Math.random() > 0.3) return;
              } else if (data.resdata.length <= 500) {
                if (Math.random() > 0.2) return;
              } else {
                if (Math.random() > 0.1) return;
              }
            } 
          }
          
        	//console.log(area);
          tripList += "<li class='carousel-single-wrap' onclick='moveCenter(" + area.latitude + "," + area.longitude + ")'>"+
              "<p><img src=' "+ area.first_image +"' height='150px' style='border-radius: 10px'></p>"+
              "<p>" + area.title + "</p>"+
              "<p>" + area.addr1 + " " + area.addr2 + "</p>"+
              "<p>" + area.latitude + "</p>"+
              "<p>" + area.longitude + "</p>"+
            "</li>";

          let markerInfo = {
            title: area.title,
            latlng: new kakao.maps.LatLng(area.latitude, area.longitude),
          };
          positions.push(markerInfo);
        });

        console.log(positions);
        document.querySelector(".carousel-flex").innerHTML = tripList;
        displayMarker();
      }

      function displayMarker() {
        // 마커 이미지의 이미지 주소입니다
        var imageSrc =
          "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

        for (var i = 0; i < positions.length; i++) {
          // 마커 이미지의 이미지 크기 입니다
          var imageSize = new kakao.maps.Size(24, 35);

          // 마커 이미지를 생성합니다
          var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

          // 마커를 생성합니다
          markers.value.push(new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커를 표시할 위치
            title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            image: markerImage, // 마커 이미지
          }));
        }

        // 첫번째 검색 정보를 이용하여 지도 중심을 이동 시킵니다
        map.setCenter(positions[0].latlng);
        map.setLevel(7);
      }

      function moveCenter(lat, lng) {
        map.setCenter(new kakao.maps.LatLng(lat, lng));
      }

</script>

<template>
    <main>
        <!-- title -->
        <h1 class="map-title">우리동네 구석구석</h1>

        <!-- address options -->
        <div class="map-select-list">
            <select class="map-select" name="search-area" id="search-area">
                <option value="">시도선택</option>
            </select>
            <select class="map-select" name="search-content-id" id="search-content-id">
                <option value="0" selected>관광지 유형</option>
                <option value="12">관광지</option>
                <option value="14">문화시설</option>
                <option value="15">축제공연행사</option>
                <option value="25">여행코스</option>
                <option value="28">레포츠</option>
                <option value="32">숙박</option>
                <option value="38">쇼핑</option>
                <option value="39">음식점</option>
            </select>
            <input class="map-select" name="search-keyword" id="search-keyword" placeholder="검색어를 입력해주세요." />
            <button id="btn-search" style="font-size: 1.5rem; cursor: pointer" @click="Search">검색</button>
        </div>

        <!-- result filter options -->
        <form class="map-form" @on:change="console.log('123')">
            <input type="radio" id="all-select" @on:change="console.log('123')"/>
            <label for="all-select">모두선택</label>
            <input type="checkbox" id="tour-spot" />
            <label for="tour-spot">관광지</label>
            <input type="checkbox" id="cultural" />
            <label for="cultural">문화시설</label>
            <input type="checkbox" id="enjoy" />
            <label for="enjoy">행사 / 공연 / 축제</label>
            <input type="checkbox" id="travel-course" />
            <label for="travel-course">여행코스</label>
            <input type="checkbox" id="leisure" />
            <label for="leisure">레포츠</label>
            <input type="checkbox" id="domitory" />
            <label for="domitory">숙박</label>
            <input type="checkbox" id="shopping" />
            <label for="shopping">쇼핑</label>
            <input type="checkbox" id="resturant" />
            <label for="resturant">음식점</label>
        </form>

        <!-- kakao map -->
        <div class="container">
            <!-- kakao map start -->
            <div ref="mapContainer" style="width: 100%; height: 700px"></div>
            <!-- kakao map end -->
        </div>

        <!-- carousel wrap -->
        <div class="carousel-wrap">
            <!-- carousel left button -->
            <button class="carousel-button carousel-button-left">&lt;</button>
            <!-- carousel images -->
            <ul class="carousel-flex"></ul>
            <!-- carousel right button -->
            <button class="carousel-button carousel-button-right">&gt;</button>
        </div>
    </main>
</template>

<style scoped></style>
