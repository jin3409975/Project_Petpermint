package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.PostFiles;
import com.ssafy.db.entity.UserPost;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 커뮤니티 목록 조회 API ([GET] /community/list) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("CommunityListGetResponse")
public class CommunityListGetRes extends BaseResponseBody{
	@ApiModelProperty(name="Post ID")
	List<UserPost>userPosts;
	List<PostFiles> urls;

	public static CommunityListGetRes of(Integer statusCode, String message, List<UserPost> data, List<PostFiles>urls) {
		CommunityListGetRes res = new CommunityListGetRes();
		res.setUserPosts(data);
		res.setStatusCode(statusCode);
		res.setMessage(message);
		res.setUrls(urls);
		return res;
	}
}
