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
public class ReservationListRes extends BaseResponseBody{
    List<Reservation> result;
    List<String> userName;
    List<String> animalName;

    public static ReservationListRes ofConsult(Integer statusCode, String message, Map<String, Object> data) {
        ReservationListRes res = new ReservationListRes();

        res.setStatusCode(statusCode);
        res.setMessage(message);

        if(data != null) {
            res.setResult((List<Reservation>) data.get("result"));
            res.setUserName((List<String>) data.get("userName"));
            res.setAnimalName((List<String>) data.get("animalName"));
        }
        return res;
    }
}