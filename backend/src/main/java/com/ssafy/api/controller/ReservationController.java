package com.ssafy.api.controller;

import com.ssafy.api.request.ReservationRegisterPostReq;
import com.ssafy.api.response.ConsultReservationRes;
import com.ssafy.api.response.HospitalReservationRes;
import com.ssafy.api.service.ReservationService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Reservation;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
	public ResponseEntity<List<ConsultReservationRes>> getAllConsultReservationInfo(@ApiIgnore String userId) {
		List<Reservation> reservations = reservationService.getAllConsultReservations(userId);
		List<ConsultReservationRes> reservationResList = ConsultReservationRes.listOfConsult(reservations);
		return ResponseEntity.status(200).body(reservationResList);
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
			return ResponseEntity.status(404).body(null); // 예약이 없을 경우 404 응답.
		}

		return ResponseEntity.status(200).body(ConsultReservationRes.ofConsult(reservation));
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
	public ResponseEntity<List<HospitalReservationRes>> getAllHospitalReservationInfo(@ApiIgnore String userId) {
		List<Reservation> reservations = reservationService.getAllHospitalReservations(userId);
		List<HospitalReservationRes> reservationResList = HospitalReservationRes.listOfHospital(reservations);
		System.out.println(reservations.toString());
		System.out.println(reservationResList.toString());
		return ResponseEntity.status(200).body(reservationResList);
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
			return ResponseEntity.status(404).body(null); // 예약이 없을 경우 404 응답.
		}

		return ResponseEntity.status(200).body(HospitalReservationRes.ofHospital(reservation));
	}

}