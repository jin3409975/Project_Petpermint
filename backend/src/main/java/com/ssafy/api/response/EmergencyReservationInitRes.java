package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원 본인 정보 조회 API ([GET] /api/v1/users/me) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("RecoverResponse")
public class RecoverRes extends BaseResponseBody{
	@ApiModelProperty(name="User ID")
	String userId;

	public static RecoverRes of(Integer statusCode, String message, String userId) {
		RecoverRes res = new RecoverRes();
		res.setMessage(message);
		res.setStatusCode(statusCode);
		res.setUserId(userId);
		return res;
	}
}
