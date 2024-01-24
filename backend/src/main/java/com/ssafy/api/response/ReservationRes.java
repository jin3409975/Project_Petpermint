package com.ssafy.api.response;

import com.ssafy.db.entity.Reservation;
import com.ssafy.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 예약 정보 전체 조회 API ([GET] /api/v1/reserve/consult/data) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ToString
@ApiModel("ReservationResponse")
public class ReservationRes {
	@ApiModelProperty(name="Appoint ID")
	int appointId;
    String time;
    String diagnosis;
    String note;
    int type;
    int isDelete;
    String licenseNumber;
    int hospitalNo;
    String userId;

	public static ReservationRes of(Reservation reservation) {
		ReservationRes res = new ReservationRes();

		res.setAppointId(reservation.getAppointId());
        res.setTime(reservation.getTime());
        res.setDiagnosis(reservation.getDiagnosis());
        res.setNote(reservation.getNote());
        res.setType(reservation.getType());
        res.setIsDelete(reservation.getIsDelete());
        res.setLicenseNumber(reservation.getLicenseNumber());
        res.setHospitalNo(reservation.getHospitalNo());
        res.setUserId(reservation.getUserId());

		return res;
	}

	public static List<ReservationRes> listOf(List<Reservation> reservations) {
		return reservations.stream().map(ReservationRes::of).collect(Collectors.toList());
	}
}
