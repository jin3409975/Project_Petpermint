package com.ssafy.api.response;

import com.ssafy.db.entity.PostComment;
import com.ssafy.db.entity.UserPost;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 댓글 조회 API ([GET] /community/comment/data) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("CommentDataGetResponse")
public class CommentDataGetRes {
	@ApiModelProperty(name="Comment ID")
	int postId;
	String content;
	String registTime;
	String userId;

	public static CommentDataGetRes of(PostComment data) {
		CommentDataGetRes res = new CommentDataGetRes();
		res.setPostId(data.getPostId());
		res.setContent(data.getContent());
		res.setRegistTime(data.getRegistTime());
		res.setUserId(data.getUserId());
		return res;
	}
}
