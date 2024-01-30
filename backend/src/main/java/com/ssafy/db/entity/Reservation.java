package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 예약 모델 정의.
 */
@Entity
@Getter
@Setter
@ToString
public class Reservation extends BaseEntity{

    @Id
    int appointId;
    String time;
    String diagnosis;
    String note;
    int type;
    int isDelete;
    String licenseNumber;
    int hospitalNo;
    String userId;
    int animalId;
}
