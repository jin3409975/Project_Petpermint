package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 유저 회원가입 API ([POST] /api/v1/users) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("RecoverPostRequest")
public class RecoverReq {
	@ApiModelProperty(name="이메일 형식 유저 ID", example="ssafy_web@gmail.com")
	String userId;
	@ApiModelProperty(name="새로 설정할 Password", example="your_password")
	String password;
	@ApiModelProperty(name="유저 이름", example="ssafy")
	String userName;
	@ApiModelProperty(name="유저 전화번호", example="010-1234-5678")
	String phone;

}
