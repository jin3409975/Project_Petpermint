package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.VenueData;
import com.ssafy.db.entity.VideoRoom;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 편의시설 조회 API ([GET] /venue/data) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("VenueDataGetResponse")
public class VenueDataGetRes extends BaseResponseBody {
//	@ApiModelProperty(name="Venue ID")
//	int dataNo;
//	String venName;
//	String category;
//	String sido;
//	String gungu;
//	String myundong;
//	String lat;
//	String lon;
//	int posNum;
//	String roadAddr;
//	String lotAddr;
//	String phone;
//	String homepage;
//	String offTime;
//	String workTime;
//	String parkAvail;
//	String entrAvail;
//	String limit;
	List<VenueData> result;


	public static VenueDataGetRes of(Integer statusCode, String message, List<VenueData> data) {
		VenueDataGetRes res = new VenueDataGetRes();
		res.setStatusCode(statusCode);
		res.setMessage(message);
		res.setResult(data);
		return res;
	}
}
