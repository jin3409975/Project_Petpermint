package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * 게시물 첨부파일 모델 정의.
 */
@Entity
@Getter
@Setter
public class PostFiles extends BaseEntity{

    @Id
    String url;
    String fileName;
    int postId;

}
