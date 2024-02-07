package com.ssafy.api.controller;

import com.ssafy.api.request.VenueDataGetReq;
import com.ssafy.api.request.VideoCreatePostReq;
import com.ssafy.api.request.VideoDataDeleteReq;
import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.response.VenueDataGetRes;
import com.ssafy.api.response.VenueStringDataRes;
import com.ssafy.api.response.VideoDataGetRes;
import com.ssafy.api.service.LiveService;
import com.ssafy.api.service.VenueService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.VenueData;
import com.ssafy.db.entity.VideoRoom;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 편의시설 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "편의시설 API", tags = {"Venue"})
@RestController
@RequestMapping("/venue")
public class VenueController {
	@Autowired
	VenueService venueService;

	@GetMapping("/data")
	@ApiOperation(value = "편의시설 조회", notes = "편의시설에 대한 정보를 응답한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<? extends BaseResponseBody> get(VenueDataGetReq dataInfo) {
		List<VenueData> dataList = venueService.dataVenue(dataInfo);
		System.out.println(dataInfo);
		return ResponseEntity.status(200).body(VenueDataGetRes.of(200,"Success",dataList));
	}

	@GetMapping("/init")
	@ApiOperation(value = "시도 조회", notes = "편의시설에 대한 시도 정보를 응답한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<? extends BaseResponseBody> getSido() {
		List<String> sido = venueService.getSidoData();
		return ResponseEntity.status(200).body(VenueStringDataRes.of(200,"Success",sido));
	}

	@GetMapping("/gungu")
	@ApiOperation(value = "군구 조회", notes = "편의시설에 대한 군구 정보를 응답한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<? extends BaseResponseBody> getGungu(String sido) {
		List<String> gungu = venueService.getGunguData(sido);
		return ResponseEntity.status(200).body(VenueStringDataRes.of(200,"Success",gungu));
	}

	@GetMapping("/myundong")
	@ApiOperation(value = "동읍면 조회", notes = "편의시설에 대한 동읍면 정보를 응답한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<? extends BaseResponseBody> getMyundong(String sido, String gungu) {
		List<String> myundong = venueService.getMyundongData(sido,gungu);
		return ResponseEntity.status(200).body(VenueStringDataRes.of(200,"Success",myundong));
	}
}
