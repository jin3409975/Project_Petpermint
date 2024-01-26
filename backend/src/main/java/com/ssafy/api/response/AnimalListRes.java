package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Animal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 회원 본인 정보 조회 API ([GET] /api/v1/users/me) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("AnimalListResponse")
public class AnimalListRes extends BaseResponseBody{
	List<Animal> result;
	public static AnimalListRes of(Integer statusCode, String message, List<Animal> animal) {
		AnimalListRes res = new AnimalListRes();
		res.setMessage(message);
		res.setStatusCode(statusCode);

		res.result = animal;

		return res;
	}
}
