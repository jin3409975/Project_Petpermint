package com.ssafy.api.controller;

import com.ssafy.api.request.EmergencyReservationRegisterPostReq;
import com.ssafy.api.response.EmergencyReservationRes;
import com.ssafy.api.service.EmergencyReservationService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Reservation;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 응급 예약 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "응급 예약 관리 API", tags = {"Emergency"})
@RestController
@RequestMapping("/emergency")
public class EmergencyController {

	@Autowired
	EmergencyReservationService emergencyReservationService;

	@PostMapping("/reserve/create")
	@ApiOperation(value = "응급 예약 생성", notes = "응급 예약 생성")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "응급 예약 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> emergencyRegister(

			@RequestBody @ApiParam(value = "응급 예약 정보", required = true) EmergencyReservationRegisterPostReq emergencyRegisterPostReq) {
		// System.out.println(emergencyRegisterPostReq); 잘못된 부분이 어디인지 찾아보자
		if (emergencyReservationService.createEmergencyReservation(emergencyRegisterPostReq)) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		} else {
			return ResponseEntity.status(200).body(BaseResponseBody.of(400, "Fail"));
		}
	}


	@GetMapping("/reserve/data")
	@ApiOperation(value = "특정 사용자의 응급 예약 전체 조회", notes = "특정 사용자의 응급 예약 전체 조회")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "응급 예약 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<EmergencyReservationRes> getAllHospitalReservationInfo(@ApiIgnore String userId) {
		List<Reservation> reservations = emergencyReservationService.getAllEmergencyReservations(userId);
		System.out.println(reservations.toString());
		return ResponseEntity.status(200).body(EmergencyReservationRes.ofHospital(200,"Success",reservations));
	}


	@GetMapping("/reserve/detail/{appointId}")
	@ApiOperation(value = "특정 사용자의 응급 예약 개별 조회", notes = "특정 사용자의 응급 예약 개별 조회")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "응급 예약 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<EmergencyReservationRes> getHospitalReservationInfo(@PathVariable int appointId) {
		Reservation reservation = emergencyReservationService.getEmergencyReservation(appointId);
		if (reservation == null) {
			return ResponseEntity.status(404).body(EmergencyReservationRes.ofHospital(400,"Fail",reservation)); // 예약이 없을 경우 404 응답.
		}

		return ResponseEntity.status(200).body(EmergencyReservationRes.ofHospital(200,"Success",reservation));
	}

}