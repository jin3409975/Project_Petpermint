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
public class PostLikes extends BaseEntity{

    @Id
    int postId;
    String userId;

}
