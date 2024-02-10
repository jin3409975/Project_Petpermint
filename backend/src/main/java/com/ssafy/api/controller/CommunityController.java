package com.ssafy.api.controller;

import com.ssafy.api.request.*;
import com.ssafy.api.response.*;
import com.ssafy.api.service.CommunityService;
import com.ssafy.api.service.S3UpDownloadService;
import com.ssafy.common.model.response.BaseResponseBody;

import com.ssafy.db.entity.PostComment;
import com.ssafy.db.entity.PostLikes;
import com.ssafy.db.entity.UserPost;
import com.ssafy.db.entity.VideoRoom;
import com.ssafy.db.join.PostUrlList;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 커뮤니티 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "커뮤니티 API", tags = {"Community"})
@RestController
@RequestMapping("/community")
public class CommunityController {
	@Autowired
	CommunityService communityService;
	@Autowired
	private S3UpDownloadService s3service;

	@PostMapping("/write")
	@ApiOperation(value = "글 작성", notes = "<strong>내용과 이미지</strong>를 사용하여 게시물을 작성한다")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<BaseResponseBody> write(@RequestParam @ApiParam(value="사용자 아이디", required = true) String userId, @RequestParam @ApiParam(value="내용 정보", required = true) String content, @RequestParam(required = false) @ApiParam(value="이미지") List<MultipartFile> images) throws IOException {
		CommunityWritePostReq writeInfo=new CommunityWritePostReq();
		//System.out.println("IMAGES: "+images.get(0));
		writeInfo.setUserId(userId);
		writeInfo.setContent(content);
		List<String> urls=new ArrayList<String>();
		List<String> fileNames=new ArrayList<String>();
		int postId = communityService.findPostCount();
		UserPost userPost = communityService.writePost(writeInfo);
		if(images!=null) {
			for (int i = 0; i < images.size(); i++) {

				String fileName=i+"_"+images.get(i).getOriginalFilename();
				String url = s3service.saveImage(images.get(i), fileName,postId);
				urls.add(url);
				fileNames.add(fileName);
			}
			int uploads = communityService.writeUrl(postId, fileNames, urls);
		}

		// 정상적으로 등록되었을 때
		if(userPost !=null)
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		else
			return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
	}

	@GetMapping("/data")
	@ApiOperation(value = "글 정보 조회", notes = "글에 대한 정보를 응답한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<? extends BaseResponseBody> get(int postId) {

		UserPost userPost = communityService.dataPost(postId);
		List<String> urls = communityService.getUrl(postId);

		if(userPost!=null)
			return ResponseEntity.status(200).body(CommunityDataGetRes.of(200,"200",userPost,urls));
		else
			return ResponseEntity.status(400).body(BaseResponseBody.of(200,"fail"));
	}

	@PatchMapping("/list")
	@ApiOperation(value = "글 목록 조회", notes = "글 목록을 응답한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<? extends BaseResponseBody> list(@RequestBody CommunityListPostReq listPostReq) {
		int page= listPostReq.getPage();
		List<Integer>postIds=listPostReq.getPostIds();

		List<PostUrlList> result= communityService.findPostUrlJoin(page,postIds);
		for(PostUrlList list: result)
			System.out.println(list.getContent());

		if(result!=null)
			return ResponseEntity.status(200).body(CommunityListGetRes.of(200, "200", result));
		else
			return ResponseEntity.status(400).body(BaseResponseBody.of(200,"fail"));
	}

	@PutMapping("/update")
	@ApiOperation(value = "글 수정", notes = "<strong>내용과 이미지</strong>를 사용하여 게시물을 수정한다")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<BaseResponseBody> update(@RequestParam @ApiParam(value="사용자 아이디", required = true) int postId, @RequestParam @ApiParam(value="내용 정보", required = true) String content, @RequestParam(required = false) @ApiParam(value="이미지") List<MultipartFile> images) throws IOException {
		CommunityUpdatePutReq updateInfo=new CommunityUpdatePutReq();
		updateInfo.setPostId(postId);
		updateInfo.setContent(content);
		Long result = communityService.updatePost(updateInfo);
		List<String> del_urls=communityService.getUrl(postId);
		if(images!=null) {


			List<String> urls = new ArrayList<>();
			List<String> fileNames = new ArrayList<>();
			int delete_result = communityService.deleteUrl(postId);
			System.out.println("del: "+delete_result);
			for (String url : del_urls) {
				s3service.deleteFile(url);
			}
			int i=0;
			for (MultipartFile image : images) {
				System.out.println("image: "+image);
				String fileName=i+"_"+image.getOriginalFilename();
				String url = s3service.saveImage(image, fileName,postId);
				urls.add(url);
				fileNames.add(fileName);
				i++;
			}

			communityService.writeUrl(updateInfo.getPostId(), fileNames, urls);
		}

		// 정상적으로 수정되었을 때
		if(result>0)
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		else
			return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
	}

	@DeleteMapping("/delete")
	@ApiOperation(value = "글 삭제", notes = "<strong>게시물 번호</strong>를 사용하여 게시물을 삭제한다")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<BaseResponseBody> delete(@RequestBody @ApiParam(value="글 삭제 정보", required = true) CommunityDeleteDeleteReq deletePostReq) {
		int postId=deletePostReq.getPostId();
		Long result = communityService.isDeletePost(postId);

		// 정상적으로 삭제되었을 때
		if(result>0)
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		else
			return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
	}

	@DeleteMapping("/delete/admin")
	@ApiOperation(value = "어드민 글 삭제", notes = "<strong>게시물 번호</strong>를 사용하여 게시물을 영구 삭제한다")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<BaseResponseBody> adminDelete(@RequestBody @ApiParam(value="글 삭제 정보", required = true) CommunityDeleteDeleteReq deletePostReq) {
		int postId=deletePostReq.getPostId();
		Long result = communityService.deletePost(postId);

		// 정상적으로 삭제되었을 때
		if(result>0)
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		else
			return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
	}

	@PostMapping("/comment/create")
	@ApiOperation(value = "댓글 작성", notes = "<strong>내용</strong>을 사용하여 댓글을 작성한다")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<BaseResponseBody> createComment(@RequestBody @ApiParam(value="글 작성 정보", required = true) CommentCreatePostReq createInfo) {

		PostComment postComment = communityService.createComment(createInfo);
		// 정상적으로 등록되었을 때
		if(postComment!=null)
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		else
			return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
	}

	@GetMapping("/comment/data")
	@ApiOperation(value = "댓글 조회", notes = "댓글을 응답한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<? extends BaseResponseBody> getComment(@RequestParam Integer postId) {
		List<PostComment> postComment = communityService.dataComment(postId);
		if(postComment!=null)
			return ResponseEntity.status(200).body(CommentDataGetRes.of(200,"Success",postComment));
		else
			return ResponseEntity.status(400).body(BaseResponseBody.of(200,"Fail"));
	}

	@DeleteMapping("/comment/delete")
	@ApiOperation(value = "댓글 삭제", notes = "<strong>삭제 정보</strong>를 사용하여 댓글을 삭제한다")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<BaseResponseBody> deleteComment(@RequestBody @ApiParam(value="댓글 삭제 정보", required = true)  CommentDeleteDeleteReq deleteCommentReq) {
		Long result = communityService.isDeleteComment(deleteCommentReq);

		// 정상적으로 삭제되었을 때
		if(result>0)
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		else
			return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
	}

	@DeleteMapping("/comment/delete/admin")
	@ApiOperation(value = "어드민 댓글 삭제", notes = "<strong>삭제 정보</strong>를 사용하여 댓글을 영구삭제한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<BaseResponseBody> adminDeleteComment(@RequestBody @ApiParam(value="댓글 삭제 정보", required = true)  CommentDeleteDeleteReq deleteCommentReq) {
		Long result = communityService.deleteComment(deleteCommentReq);

		// 정상적으로 삭제되었을 때
		if(result>0)
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		else
			return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
	}

	@PutMapping("/like")
	@ApiOperation(value = "좋아요", notes = "게시물의 좋아요 수를 증가한다")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<BaseResponseBody> like(@RequestBody @ApiParam(value="좋아요 정보", required = true) CommunityLikeHitPutReq updateInfo) {

		Long result = communityService.increaseLike(updateInfo.getPostId());
		PostLikes liked=communityService.insertIntoLikeTable(updateInfo.getPostId(),updateInfo.getUserId());

		// 정상적으로 수정되었을 때
		if(result>0 || liked!=null)
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		else
			return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
	}

	@GetMapping("/likecheck")
	@ApiOperation(value = "좋아요 확인", notes = "게시물의 좋아요 수를 확인한다")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<BaseResponseBody> likecheck(int postId, String userId) {

		PostLikes liked=communityService.findPostLikesByPostIdUserId(postId,userId);

		// 정상적으로 수정되었을 때
		if(liked==null)
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		else
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Fail"));
	}

	@PutMapping("/hit")
	@ApiOperation(value = "방문", notes = "게시물의 방문 수를 증가한다")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<BaseResponseBody> hit(@RequestBody @ApiParam(value="방문 정보", required = true) CommunityLikeHitPutReq updateInfo) {

		Long result = communityService.increaseHit(updateInfo.getPostId());

		// 정상적으로 수정되었을 때
		if(result>0)
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		else
			return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
	}

	@GetMapping("/video/list")
	@ApiOperation(value = "방송 목록 조회", notes = "방송 목록을 응답한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 400, message = "데이터 유효성 검사 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class),
			@ApiResponse(code = 502, message = "DB 연결 실패", response = BaseResponseBody.class)
	})
	public ResponseEntity<? extends BaseResponseBody> videoList() {
		List<VideoRoom>videos=communityService.videoList();


		if(videos!=null)
			return ResponseEntity.status(200).body(VideoListGetRes.of(200, "200", videos));
		else
			return ResponseEntity.status(400).body(BaseResponseBody.of(200,"fail"));
	}
}
