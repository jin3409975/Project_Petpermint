package com.ssafy.db.entity;

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
public class Animal extends BaseEntity{

    @Id
    int animalId;
    String name;
    String picture;
    String specie;
    String age;
    String note;
    String weight;
    String gender;
    String userId;
    int isDelete;
}
