package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * 유저 회원가입 API ([POST] /api/v1/users) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("ExpertUserRegisterPostRequest")
public class ExpertUserRegisterPostReq {
	@ApiModelProperty(name="이메일 형식 수의사 유저 ID", example="ssafy_web@gmail.com")
	String userId;
	@ApiModelProperty(name="수의사 유저 Password", example="your_password")
	String password;
	@ApiModelProperty(name="수의사 유저 이름", example="ssafy")
	String userName;
	@ApiModelProperty(name="수의사 유저 주소", example="대전광역시 유성구 계룡로 84")
	String address;
	@ApiModelProperty(name="수의사 유저 전화 번호", example="010-1234-5678")
	String phone;
	@ApiModelProperty(name="수의사 유저 타입", example="1 = 일반 사용자, 2 = 수의사 사용자, 3 = 관리자")
	int type;
	@ApiModelProperty(name="수의사 유저 면허 번호", example="12345")
	String licenseNumber;
	@ApiModelProperty(name="수의사 유저 소개 및 특이 사항", example="배고파요")
	String note;
	@ApiModelProperty(name="수의사 유저 진료 시작 시간", example="09:00")
	String startTime;
	@ApiModelProperty(name="수의사 유저 진료 종료 시간", example="18:00")
	String endTime;
	@ApiModelProperty(name="수의사 유저 병원 아름", example="사랑동물병원")
	String hospitalName;
	@ApiModelProperty(name="프로필 사진", example="img")
	MultipartFile picture;
	@ApiModelProperty(name="면허증 사진", example="img")
	MultipartFile licensePicture;
}
