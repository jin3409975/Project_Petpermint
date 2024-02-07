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
public class CommunityPictureGetRes extends BaseResponseBody{
	@ApiModelProperty(name="Post ID")

	List<String> urls;

	public static CommunityPictureGetRes of(Integer statusCode, String message,List<String>urls) {
		CommunityPictureGetRes res = new CommunityPictureGetRes();
		res.setStatusCode(statusCode);
		res.setMessage(message);
		res.setUrls(urls);
		return res;
	}
}
