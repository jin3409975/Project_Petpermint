package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Reservation;
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
public class ConsultReservationRes extends BaseResponseBody{
    @ApiModelProperty(name = "Appoint ID")
    int appointId;
    String time;
    String diagnosis;
    String note;
    int type;
//    int isDelete;
    String licenseNumber;
    int hospitalNo;
//    String userId;
    List<Reservation> result;

    public static ConsultReservationRes ofConsult(Integer statusCode, String message, Reservation reservation) {
        ConsultReservationRes res = new ConsultReservationRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setAppointId(reservation.getAppointId());
        res.setTime(reservation.getTime());
        res.setLicenseNumber(reservation.getLicenseNumber());
        res.setNote(reservation.getNote());
        res.setDiagnosis(reservation.getDiagnosis());
        res.setType(reservation.getType());
        res.setHospitalNo(reservation.getHospitalNo());
        // 반환하지 않아도 되는 데이터
//        res.setType(reservation.getType());
//        res.setIsDelete(reservation.getIsDelete());
//        res.setHospitalNo(reservation.getHospitalNo());
//        res.setUserId(reservation.getUserId());

        return res;
    }

    public static ConsultReservationRes listofConsult(Integer statusCode, String message, List<Reservation> reservation) {
        ConsultReservationRes res = new ConsultReservationRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.result = reservation;
        return res;
    }


//    public static List<ConsultReservationRes> listOfConsult(List<Reservation> reservations) {
//        return reservations.stream().map(ConsultReservationRes::ofConsult).collect(Collectors.toList());
//    }
}