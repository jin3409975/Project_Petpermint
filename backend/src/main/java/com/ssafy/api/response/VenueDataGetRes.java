package com.ssafy.api.response;

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
public class VenueDataGetRes {
	@ApiModelProperty(name="Venue ID")
	int dataNo;
	String venName;
	String category;
	String sido;
	String gungu;
	String myundong;
	double lat;
	double lon;
	int posNum;
	String roadAddr;
	String lotAddr;
	String phone;
	String homepage;
	String offTime;
	String workTime;
	String parkAvail;
	String entrAvail;
	String limit;


	public static List<VenueDataGetRes> of(List<VenueData> datas) {
		List<VenueDataGetRes> resList=new ArrayList<VenueDataGetRes>();
		for (VenueData data: datas) {
			VenueDataGetRes res = new VenueDataGetRes();
			res.setDataNo(data.getDataNo());
			res.setCategory(data.getCategory());
			res.setSido(data.getSido());
			res.setGungu(data.getGungu());
			res.setMyundong(data.getMyundong());
			res.setLat(data.getLat());
			res.setLon(data.getLon());
			res.setRoadAddr(data.getRoadAddr());
			res.setHomepage(data.getHomepage());
			res.setPhone(data.getPhone());
			res.setParkAvail(data.getParkAvail());
			res.setEntrAvail(data.getEntrAvail());
			res.setWorkTime(data.getWorkTime());
			res.setOffTime(data.getOffTime());
			res.setLimit(data.getPhone());
			res.setPosNum(data.getPosNum());
			resList.add(res);
		}
		return resList;
	}
}
