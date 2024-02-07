package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.PostComment;
import com.ssafy.db.entity.UserPost;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 댓글 조회 API ([GET] /community/comment/data) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("CommentDataGetResponse")
public class CommentDataGetRes extends BaseResponseBody {
	@ApiModelProperty(name="Comment ID")
//	int postId;
//	String content;
//	String registTime;
//	String userId;
	List<PostComment> postCommentList;

	public static CommentDataGetRes of(Integer statusCode, String message, List<PostComment> data) {
		CommentDataGetRes res = new CommentDataGetRes();
		res.setStatusCode(statusCode);
		res.setMessage(message);
		res.setPostCommentList(data);
		return res;
	}
}
