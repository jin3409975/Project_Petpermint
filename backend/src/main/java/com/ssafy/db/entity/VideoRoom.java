package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
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
public class VideoRoom extends BaseEntity{
    @Id
    int roomId;
    @Column(columnDefinition = "DATETIME")
    @Type(type = "org.hibernate.type.StringType")
    String startTime;
    String roomName;
    String note;
    @Column(columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.BooleanType")
    boolean isDelete;
    String userId;
    String sessionId;
}
