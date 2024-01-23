package com.ssafy.api.controller;

import com.ssafy.api.request.ReservationRegisterPostReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.response.UserRes;
import com.ssafy.api.service.ReservationService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Reservation;
import com.ssafy.db.entity.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> register(

			@RequestBody @ApiParam(value="예약 정보", required = true) ReservationRegisterPostReq reservationRegisterPostReq) {
		System.out.println(reservationRegisterPostReq);
		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		if(reservationService.createConsultReservation(reservationRegisterPostReq)) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		} else {
			return ResponseEntity.status(200).body(BaseResponseBody.of(400, "Fail"));
		}
	}
	
//	@GetMapping("/me")
//	@ApiOperation(value = "회원 본인 정보 조회", notes = "로그인한 회원 본인의 정보를 응답한다.")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "성공"),
//        @ApiResponse(code = 401, message = "인증 실패"),
//        @ApiResponse(code = 404, message = "사용자 없음"),
//        @ApiResponse(code = 500, message = "서버 오류")
//    })
//	public ResponseEntity<UserRes> getReservationInfo(@ApiIgnore Authentication authentication) {
//		/**
//		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
//		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
//		 */
//		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
//		String userId = userDetails.getUsername();
//		User user = reservationService.getUserByUserId(userId);
//
//		return ResponseEntity.status(200).body(UserRes.of(user));
//	}
}
