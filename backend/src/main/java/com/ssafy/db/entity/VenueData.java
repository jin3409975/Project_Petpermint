package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 화상 채팅 방 모델 정의.
 */
@Entity
@Getter
@Setter
@DynamicInsert
@ToString
public class VenueData extends BaseEntity{
    @Id
    int dataNo;
    String sido;
    String venName;
    String category;
    String gungu;
    String myundong;
    String lat;
    String lon;
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
}
