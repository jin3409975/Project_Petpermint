package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 게시물 댓글 모델 정의.
 */
@Entity
@Getter
@Setter
@DynamicInsert
public class PostComment extends BaseEntity{

    @Id
    int commentNo;
    String content;
    @Column(columnDefinition = "DATETIME")
    @Type(type = "org.hibernate.type.StringType")
    String registTime;
    int postId;
    String userId;
    @Column(columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.BooleanType")
    boolean isDelete;

}
