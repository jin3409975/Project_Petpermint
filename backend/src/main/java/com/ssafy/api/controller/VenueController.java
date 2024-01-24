package com.ssafy.api.controller;

import com.ssafy.api.request.VenueDataGetReq;
import com.ssafy.api.request.VideoCreatePostReq;
import com.ssafy.api.request.VideoDataDeleteReq;
import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.response.VenueDataGetRes;
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
	public ResponseEntity<List<VenueDataGetRes>> get(VenueDataGetReq dataInfo) {
		List<VenueData> dataList = venueService.dataVenue(dataInfo);

		return ResponseEntity.status(200).body(VenueDataGetRes.of(dataList));
	}


}
