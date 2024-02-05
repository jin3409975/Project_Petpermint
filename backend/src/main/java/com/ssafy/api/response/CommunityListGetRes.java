package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.join.PostUrlList;
import io.swagger.annotations.ApiModel;
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
	List<PostUrlList>postList;

	public static CommunityListGetRes of(Integer statusCode, String message, List<PostUrlList> postList) {
		CommunityListGetRes res = new CommunityListGetRes();
		res.postList=postList;
		res.setStatusCode(statusCode);
		res.setMessage(message);
		return res;
	}
}
