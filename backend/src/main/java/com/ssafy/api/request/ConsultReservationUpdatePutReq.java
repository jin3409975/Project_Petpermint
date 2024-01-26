package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 예약 관리 API ([PUT]/api/v1/reserve) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ToString
@ApiModel("ReservationUpdatePutRequest")
public class ConsultReservationUpdatePutReq {
	@ApiModelProperty(name="이메일 형식 유저 ID", example="ssafy_web@gmail.com")
	int appointId;
	String time;
	String licenseNumber;
	String note;

}
