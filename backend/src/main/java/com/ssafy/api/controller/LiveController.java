package com.ssafy.api.controller;

import com.ssafy.api.request.*;
import com.ssafy.api.response.CommentDataGetRes;
import com.ssafy.api.response.CommunityDataGetRes;
import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.response.VideoDataGetRes;
import com.ssafy.api.service.CommunityService;
import com.ssafy.api.service.LiveService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.PostComment;
import com.ssafy.db.entity.UserPost;
import com.ssafy.db.entity.VideoRoom;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 라이브 방송 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "라이브 API", tags = {"Live"})
@RestController
@RequestMapping("/live")
public class LiveController {
	@Autowired
	LiveService liveService;
	
	@PostMapping("/create")
	@ApiOperation(value = "라이브 방 생성", notes = "라이브 방송 방을 연다")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
        @ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
        @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
		@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
    })
	public ResponseEntity<UserLoginPostRes> create(@RequestBody @ApiParam(value="라이브 방 정보", required = true) VideoCreatePostReq createInfo) {

		VideoRoom videoRoom = liveService.createRoom(createInfo);

		// 정상적으로 등록되었을 때
		return ResponseEntity.status(200).body(UserLoginPostRes.of(200, "Successfully created", null));
	}

	@GetMapping("/data")
	@ApiOperation(value = "라이브 방 조회", notes = "방송에 대한 정보를 응답한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<VideoDataGetRes> get(int postId) {

		VideoRoom videoRoom = liveService.dataRoom(postId);

		return ResponseEntity.status(200).body(VideoDataGetRes.of(videoRoom));
	}

	@PutMapping("/update")
	@ApiOperation(value = "라이브 방 수정", notes = "방송에 대한 정보를 수정한다")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<UserLoginPostRes> update(@RequestBody @ApiParam(value="글 수정 정보", required = true) VideoDataDeleteReq updateRoomReq) {
		int roomId=updateRoomReq.getRoomId();
		//Long result= liveService.updateRoom(roomId);

		// 정상적으로 수정되었을 때
		return ResponseEntity.status(200).body(UserLoginPostRes.of(200, "Successfully updated", null));
	}

	@DeleteMapping("/delete")
	@ApiOperation(value = "글 삭제", notes = "<strong>방 번호</strong>를 사용하여 방을 삭제한다")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<UserLoginPostRes> delete(@RequestBody @ApiParam(value="글 삭제 정보", required = true) VideoDataDeleteReq deleteRoomReq) {
		int roomId=deleteRoomReq.getRoomId();
		Long result = liveService.deleteRoom(roomId);

		// 정상적으로 삭제되었을 때
		return ResponseEntity.status(200).body(UserLoginPostRes.of(200, "Successfully deleted", null));
	}


}
