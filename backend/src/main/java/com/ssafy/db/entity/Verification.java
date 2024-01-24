package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@Setter
@ToString
public class Verification{

    @Id
    String userId;
    String verificationCode;
}
