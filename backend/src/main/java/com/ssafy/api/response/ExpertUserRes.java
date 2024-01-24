package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.ExpertUser;
import com.ssafy.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 회원 본인 정보 조회 API ([GET] /api/v1/users/me) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("UserResponse")
public class ExpertUserRes extends BaseResponseBody{
	@ApiModelProperty(name="User ID")
	String userId;
	String userName;
	String address;
	String phone;
	String picture;
	String licenseNumber;
	String note;
	String startTime;
	String endTime;
	int hospitalNo;

	public static ExpertUserRes of(Integer statusCode, String message, User user, ExpertUser expertUser) {
		ExpertUserRes res = new ExpertUserRes();
		res.setMessage(message);
		res.setStatusCode(statusCode);
		res.setUserId(user.getUserId());
		res.setUserName(user.getUserName());
		res.setAddress(user.getAddress());
		res.setPhone(user.getPhone());
		res.setPicture(user.getPicture());
		res.setLicenseNumber(expertUser.getLicenseNumber());
		res.setNote(expertUser.getNote());
		res.setStartTime(expertUser.getStartTime());
		res.setEndTime(expertUser.getEndTime());
		res.setHospitalNo(expertUser.getHospitalNo());
		return res;
	}
}
