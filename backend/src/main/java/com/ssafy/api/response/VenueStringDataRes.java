package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.VenueData;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 편의시설 조회 API ([GET] /venue/data) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("VenueStringDataResponse")
public class VenueStringDataRes extends BaseResponseBody {

	List<String> result;

	public static VenueStringDataRes of(Integer statusCode, String message, List<String> data) {
		VenueStringDataRes res = new VenueStringDataRes();
		res.setStatusCode(statusCode);
		res.setMessage(message);
		res.setResult(data);
		return res;
	}
}
