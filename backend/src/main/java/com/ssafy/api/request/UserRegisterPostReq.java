package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 회원가입 API ([POST] /api/v1/users) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserRegisterPostRequest")
public class UserRegisterPostReq {
	@ApiModelProperty(name="이메일 형식 유저 ID", example="ssafy_web@gmail.com")
	String userId;
	@ApiModelProperty(name="유저 Password", example="your_password")
	String password;
	@ApiModelProperty(name="유저 이름", example="ssafy")
	String userName;
	@ApiModelProperty(name="유저 주소", example="대전광역시 유성구 계룡로 84")
	String address;
	@ApiModelProperty(name="유저 전화 번호", example="010-1234-5678")
	String phone;
	@ApiModelProperty(name="유저 타입", example="1 = 일반 사용자, 2 = 수의사 사용자, 3 = 관리자")
	int type;
}
