package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.UserPost;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 커뮤니티 조회 API ([GET] /community/data) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("CommunityDataGetResponse")
public class CommunityDataGetRes{
	@ApiModelProperty(name="Post ID")
	int postId;
	int hits;
	String content;
	int likes;
	String registTime;
	String userId;

	public static CommunityDataGetRes of(UserPost data) {
		CommunityDataGetRes res = new CommunityDataGetRes();
		res.setPostId(data.getPostId());
		res.setHits(data.getHits());
		res.setContent(data.getContent());
		res.setLikes(data.getLikes());
		res.setRegistTime(data.getRegistTime());
		res.setUserId(data.getUserId());
		return res;
	}
}
