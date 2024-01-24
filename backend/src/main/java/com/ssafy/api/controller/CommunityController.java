package com.ssafy.api.controller;

import com.ssafy.api.request.*;
import com.ssafy.api.response.CommentDataGetRes;
import com.ssafy.api.response.CommunityDataGetRes;
import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.service.CommunityService;
import com.ssafy.common.model.response.BaseResponseBody;

import com.ssafy.db.entity.PostComment;
import com.ssafy.db.entity.UserPost;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 커뮤니티 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "커뮤니티 API", tags = {"Community"})
@RestController
@RequestMapping("/community")
public class CommunityController {
	@Autowired
	CommunityService communityService;
	
	@PostMapping("/write")
	@ApiOperation(value = "글 작성", notes = "<strong>내용과 이미지</strong>를 사용하여 게시물을 작성한다")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
        @ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
        @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
		@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
    })
	public ResponseEntity<UserLoginPostRes> write(@RequestBody @ApiParam(value="글 작성 정보", required = true) CommunityWritePostReq writeInfo) {

		UserPost userPost = communityService.writePost(writeInfo);

		// 정상적으로 등록되었을 때
		return ResponseEntity.status(200).body(UserLoginPostRes.of(200, "Successfully written", null));
	}

	@GetMapping("/data")
	@ApiOperation(value = "글 정보 조회", notes = "글에 대한 정보를 응답한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<CommunityDataGetRes> get(int postId) {

		UserPost userPost = communityService.dataPost(postId);

		return ResponseEntity.status(200).body(CommunityDataGetRes.of(userPost));
	}

	@PutMapping("/update")
	@ApiOperation(value = "글 수정", notes = "<strong>내용과 이미지</strong>를 사용하여 게시물을 수정한다")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<UserLoginPostRes> update(@RequestBody @ApiParam(value="글 수정 정보", required = true) CommunityUpdatePutReq updateInfo) {

		Long result = communityService.updatePost(updateInfo);

		// 정상적으로 수정되었을 때
		return ResponseEntity.status(200).body(UserLoginPostRes.of(200, "Successfully updated", null));
	}

	@DeleteMapping("/delete")
	@ApiOperation(value = "글 삭제", notes = "<strong>게시물 번호</strong>를 사용하여 게시물을 삭제한다")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<UserLoginPostRes> delete(@RequestBody @ApiParam(value="글 삭제 정보", required = true) CommunityDeleteDeleteReq deletePostReq) {
		int postId=deletePostReq.getPostId();
		Long result = communityService.isDeletePost(postId);

		// 정상적으로 삭제되었을 때
		return ResponseEntity.status(200).body(UserLoginPostRes.of(200, "Successfully deleted", null));
	}

	@DeleteMapping("/delete/admin")
	@ApiOperation(value = "어드민 글 삭제", notes = "<strong>게시물 번호</strong>를 사용하여 게시물을 영구 삭제한다")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<UserLoginPostRes> adminDelete(@RequestBody @ApiParam(value="글 삭제 정보", required = true) CommunityDeleteDeleteReq deletePostReq) {
		int postId=deletePostReq.getPostId();
		Long result = communityService.deletePost(postId);

		// 정상적으로 삭제되었을 때
		return ResponseEntity.status(200).body(UserLoginPostRes.of(200, "Successfully deleted", null));
	}

	@PostMapping("/comment/create")
	@ApiOperation(value = "댓글 작성", notes = "<strong>내용</strong>을 사용하여 댓글을 작성한다")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<UserLoginPostRes> createComment(@RequestBody @ApiParam(value="글 작성 정보", required = true) CommentCreatePostReq createInfo) {

		PostComment postComment = communityService.createComment(createInfo);
		// 정상적으로 등록되었을 때
		return ResponseEntity.status(200).body(UserLoginPostRes.of(200, "Successfully written", null));
	}

	@GetMapping("/comment/data")
	@ApiOperation(value = "댓글 조회", notes = "댓글을 응답한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<CommentDataGetRes> getComment(int postId) {

		PostComment postComment = communityService.dataComment(postId);

		return ResponseEntity.status(200).body(CommentDataGetRes.of(postComment));
	}

	@DeleteMapping("/comment/delete")
	@ApiOperation(value = "댓글 삭제", notes = "<strong>삭제 정보</strong>를 사용하여 댓글을 삭제한다")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<UserLoginPostRes> deleteComment(@RequestBody @ApiParam(value="댓글 삭제 정보", required = true)  CommentDeleteDeleteReq deleteCommentReq) {
		Long result = communityService.isDeleteComment(deleteCommentReq);

		// 정상적으로 삭제되었을 때
		return ResponseEntity.status(200).body(UserLoginPostRes.of(200, "Successfully deleted", null));
	}

	@DeleteMapping("/comment/delete/admin")
	@ApiOperation(value = "어드민 댓글 삭제", notes = "<strong>삭제 정보</strong>를 사용하여 댓글을 영구삭제한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<UserLoginPostRes> adminDeleteComment(@RequestBody @ApiParam(value="댓글 삭제 정보", required = true)  CommentDeleteDeleteReq deleteCommentReq) {
		Long result = communityService.deleteComment(deleteCommentReq);

		// 정상적으로 삭제되었을 때
		return ResponseEntity.status(200).body(UserLoginPostRes.of(200, "Successfully deleted", null));
	}

	@PutMapping("/like")
	@ApiOperation(value = "좋아요", notes = "게시물의 좋아요 수를 증가한다")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<UserLoginPostRes> like(@RequestBody @ApiParam(value="좋아요 정보", required = true) CommunityLikeHitPutReq updateInfo) {

		Long result = communityService.increaseLike(updateInfo.getPostId());

		// 정상적으로 수정되었을 때
		return ResponseEntity.status(200).body(UserLoginPostRes.of(200, "Successfully updated", null));
	}

	@PutMapping("/hit")
	@ApiOperation(value = "방문", notes = "게시물의 방문 수를 증가한다")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<UserLoginPostRes> hit(@RequestBody @ApiParam(value="방문 정보", required = true) CommunityLikeHitPutReq updateInfo) {

		Long result = communityService.increaseHit(updateInfo.getPostId());

		// 정상적으로 수정되었을 때
		return ResponseEntity.status(200).body(UserLoginPostRes.of(200, "Successfully updated", null));
	}
}
