package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * 유저 회원가입 API ([POST] /api/v1/users) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("NormalUserRegisterPostRequest")
public class NormalUserUpdatePutReq {
	@ApiModelProperty(name="이메일 형식 유저 ID", example="ssafy_web@gmail.com")
	String userId;
	@ApiModelProperty(name="유저 Password", example="your_password")
	String password;
	@ApiModelProperty(name="유저 이름", example="ssafy")
	String userName;
	@ApiModelProperty(name="프로필 사진", example="img")
	MultipartFile picture;
}
