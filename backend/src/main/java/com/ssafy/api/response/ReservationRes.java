package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Reservation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * 예약 정보 전체 조회 API ([GET] /api/v1/reserve/consult/data) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ToString
@ApiModel("BasicReservationResponse")
public class ReservationRes extends BaseResponseBody{
    @ApiModelProperty(name = "Appoint ID")
    int appointId;
    String time;
    String diagnosis;
    String note;
    int type;
//    int isDelete;
    String licenseNumber;
    int hospitalNo;
    String userId;
    String doctorName;
    String animalName;

    public static ReservationRes ofConsult(Integer statusCode, String message, Map<String, Object> data) {
        ReservationRes res = new ReservationRes();
        Reservation reservation = (Reservation) data.get("result");
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setAppointId(reservation.getAppointId());
        res.setTime(reservation.getTime());
        res.setLicenseNumber(reservation.getLicenseNumber());
        res.setNote(reservation.getNote());
        res.setDiagnosis(reservation.getDiagnosis());
        res.setType(reservation.getType());
        res.setHospitalNo(reservation.getHospitalNo());
        res.setType(reservation.getType());
        res.setUserId(reservation.getUserId());
        res.setAnimalName((String)data.get("animalName"));
        res.setDoctorName((String)data.get("doctorName"));
        return res;
    }
}