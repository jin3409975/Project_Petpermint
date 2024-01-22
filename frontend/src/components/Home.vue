<template>

    <div>
        로그인 전 Home component
    </div>
    <div class="carousel">
        <!-- 홍보 배너 캐로셀 -->

    </div>
    <button @click="goToFirstReserve">초기 상담 예약하기</button>
    <button  @click="goToReserve">병원 진료 예약하기</button>
    <button  @click="goToEmergency">응급 예약하기</button>

    <div>
        <p>라이브 방송 캐로셀</p>
    </div>

</template>

<script>
export default {
  methods: {
    goToFirstReserve() {
      // ReserveView로 이동하는 코드 작성
      // 초기상담과 병원 진료예약의 구분을 나타내는 변수 전송이 필요해보임...
      this.$router.push({ name: 'ReserveInitialView' }); 
  },
  goToReserve() {
      // ReserveView로 이동하는 코드 작성
      // 초기상담과 병원 진료예약의 구분을 나타내는 변수 전송이 필요해보임...
      this.$router.push({ name: 'ReserveHospitalView' }); 
  },
  goToEmergency() {
    this.$router.push({ name: 'Emergency' }); 
  }

}}
</script>

<style scoped>

</style>