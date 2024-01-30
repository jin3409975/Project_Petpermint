package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.join.ReservationExpertUserList;
import com.ssafy.db.join.ReservationHospitalList;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 회원 본인 정보 조회 API ([GET] /api/v1/users/me) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("ReservationHospitalListResResponse")
public class ReservationHospitalListRes extends BaseResponseBody{
	List<ReservationHospitalList> result;
	public static ReservationHospitalListRes of(Integer statusCode, String message, List<ReservationHospitalList> result) {
		ReservationHospitalListRes res = new ReservationHospitalListRes();
		res.setMessage(message);
		res.setStatusCode(statusCode);

		res.result = result;

		return res;
	}
}
