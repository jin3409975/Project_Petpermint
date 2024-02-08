package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 응급 예약 관리 API ([POST]/api/v1/emergency) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ToString
@ApiModel("ConsultReservationRegisterPostRequest")
public class EmergencyReservationRegisterPostReq {
//	@ApiModelProperty(name="이메일 형식 유저 ID", example="ssafy_web@gmail.com")
//	int appointId;

//	String diagnosis;
//	String note;
//	int type;
//	int isDelete;
//	String licenseNumber;
	int hospitalNo;

	String userId;
	String time;
}
