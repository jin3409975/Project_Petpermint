package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Animal;
import com.ssafy.db.join.ReservationExpertUserList;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 회원 본인 정보 조회 API ([GET] /api/v1/users/me) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("ReservationConsultExpertListResponse")
public class ReservationConsultExpertListRes extends BaseResponseBody{
	List<ReservationExpertUserList> result;
	public static ReservationConsultExpertListRes of(Integer statusCode, String message, List<ReservationExpertUserList> result) {
		ReservationConsultExpertListRes res = new ReservationConsultExpertListRes();
		res.setMessage(message);
		res.setStatusCode(statusCode);

		res.result = result;

		return res;
	}
}
