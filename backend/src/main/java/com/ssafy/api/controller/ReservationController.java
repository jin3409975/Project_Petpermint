package com.ssafy.api.controller;

import com.ssafy.api.request.ConsultReservationUpdatePutReq;
import com.ssafy.api.request.HospitalReservationUpdatePutReq;
import com.ssafy.api.request.ReservationRegisterPostReq;
import com.ssafy.api.response.ConsultReservationRes;
import com.ssafy.api.response.EmergencyReservationRes;
import com.ssafy.api.response.HospitalReservationRes;
import com.ssafy.api.service.ReservationService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Reservation;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 예약 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "예약 관리 API", tags = {"Reservation"})
@RestController
@RequestMapping("/reserve")
public class ReservationController {

	@Autowired
	ReservationService reservationService;

	@PostMapping("/consult/create")
	@ApiOperation(value = "상담 예약 생성", notes = "상담 예약 생성")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "상담 예약 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> consultRegister(

			@RequestBody @ApiParam(value = "상담 예약 정보", required = true) ReservationRegisterPostReq reservationRegisterPostReq) {
		// System.out.println(reservationRegisterPostReq); 어디가 잘못인지 찾아보자
		if (reservationService.createConsultReservation(reservationRegisterPostReq)) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		} else {
			return ResponseEntity.status(200).body(BaseResponseBody.of(400, "Fail"));
		}
	}


	@GetMapping("/consult/data")
	@ApiOperation(value = "특정 사용자의 상담 예약 전체 조회", notes = "특정 사용자의 상담 예약 전체 조회")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "상담 예약 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<ConsultReservationRes> getAllConsultReservationInfo(@ApiIgnore String userId) {
		List<Reservation> reservations = reservationService.getAllConsultReservations(userId);

		return ResponseEntity.status(200).body(ConsultReservationRes.ofConsult(200,"Success",reservations));
	}


	@GetMapping("/consult/detail/{appointId}")
	@ApiOperation(value = "특정 사용자의 상담 예약 개별 조회", notes = "특정 사용자의 상담 예약 개별 조회")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "병원 예약 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<ConsultReservationRes> getConsultReservationInfo(@PathVariable int appointId) {
		Reservation reservation = reservationService.getHospitalReservation(appointId);
		if (reservation == null) {
			return ResponseEntity.status(404).body(ConsultReservationRes.ofConsult(400,"Fail",reservation)); // 예약이 없을 경우 404 응답.
		}

		return ResponseEntity.status(200).body(ConsultReservationRes.ofConsult(200,"Success",reservation));
	}


	@PutMapping("/consult/update")
	@ApiOperation(value = "상담 예약 수정", notes = "상담 예약 정보 수정")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 404, message = "상담 예약 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> updateConsultReservation(
			@RequestBody @ApiParam(value = "상담 예약 수정 정보", required = true) ConsultReservationUpdatePutReq consultReservationUpdatePutReq) {
		if (reservationService.updateConsultReservation(consultReservationUpdatePutReq)) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		} else {
			return ResponseEntity.status(404).body(BaseResponseBody.of(404, "상담 예약이 없습니다."));
		}
	}


	@DeleteMapping("/consult/delete")
	@ApiOperation(value = "상담 예약 삭제", notes = "상담 예약 삭제")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 400, message = "실패")
	})
	public ResponseEntity<? extends BaseResponseBody> deleteConsultReservation(
			@ApiParam(value="수정할 회원 정보") int appointId) {
		if(reservationService.deleteConsultReservation(appointId)) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		} else {
			return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
		}
	}





	@PostMapping("/hospital/create")
	@ApiOperation(value = "병원 예약 생성", notes = "병원 예약 생성")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "병원 예약 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> hospitalRegister(

			@RequestBody @ApiParam(value = "병원 예약 정보", required = true) ReservationRegisterPostReq reservationRegisterPostReq) {
		// System.out.println(reservationRegisterPostReq); 잘못된 부분이 어디인지 찾아보자
		if (reservationService.createHospitalReservation(reservationRegisterPostReq)) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		} else {
			return ResponseEntity.status(200).body(BaseResponseBody.of(400, "Fail"));
		}
	}


	@GetMapping("/hospital/data")
	@ApiOperation(value = "특정 사용자의 병원 예약 전체 조회", notes = "특정 사용자의 병원 예약 전체 조회")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "병원 예약 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<HospitalReservationRes> getAllHospitalReservationInfo(@ApiIgnore String userId) {
		List<Reservation> reservations = reservationService.getAllHospitalReservations(userId);

		return ResponseEntity.status(200).body(HospitalReservationRes.ofHospital(200,"Success",reservations));
	}


	@GetMapping("/hospital/detail/{appointId}")
	@ApiOperation(value = "특정 사용자의 병원 예약 개별 조회", notes = "특정 사용자의 병원 예약 개별 조회")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "병원 예약 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<HospitalReservationRes> getHospitalReservationInfo(@PathVariable int appointId) {
		Reservation reservation = reservationService.getHospitalReservation(appointId);
		if (reservation == null) {
			return ResponseEntity.status(404).body(HospitalReservationRes.ofHospital(400,"Fail",reservation)); // 예약이 없을 경우 404 응답.
		}

		return ResponseEntity.status(200).body(HospitalReservationRes.ofHospital(200,"Success",reservation));
	}

	@PutMapping("/hospital/update")
	@ApiOperation(value = "병원 예약 수정", notes = "병원 예약 정보 수정")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 404, message = "병원 예약 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> updateHospitalReservation(
			@RequestBody @ApiParam(value = "병원 예약 수정 정보", required = true) HospitalReservationUpdatePutReq hospitalReservationUpdatePutReq) {
		if (reservationService.updateHospitalReservation(hospitalReservationUpdatePutReq)) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		} else {
			return ResponseEntity.status(404).body(BaseResponseBody.of(404, "상담 예약이 없습니다."));
		}
	}

	@DeleteMapping("/hospital/delete")
	@ApiOperation(value = "병원 예약 삭제", notes = "병원 예약 삭제")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 400, message = "실패")
	})
	public ResponseEntity<? extends BaseResponseBody> deleteHospitalReservation(
			@ApiParam(value="수정할 회원 정보") int appointId) {
		if(reservationService.deleteHospitalReservation(appointId)) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		} else {
			return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
		}
	}

}