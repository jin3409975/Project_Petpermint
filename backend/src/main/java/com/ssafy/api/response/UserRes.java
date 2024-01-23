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
@ApiModel("UserResponse")
public class UserRes extends BaseResponseBody{
	@ApiModelProperty(name="User ID")
	String userId;
	String userName;
	String address;
	String phone;
	String picture;

	public static UserRes of(Integer statusCode, String message,User user) {
		UserRes res = new UserRes();
		res.setMessage(message);
		res.setStatusCode(statusCode);
		res.setUserId(user.getUserId());
		res.setUserName(user.getUserName());
		res.setAddress(user.getAddress());
		res.setPhone(user.getPhone());
		res.setPicture(user.getPicture());
		return res;
	}
}
