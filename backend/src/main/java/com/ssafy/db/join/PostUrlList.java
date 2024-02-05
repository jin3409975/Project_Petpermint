package com.ssafy.db.join;

import org.hibernate.annotations.Type;

import javax.persistence.Column;


public interface PostUrlList {

    int getPostId();

    String getUserName();
    int getHits();
    String getContent();
    int getLikes();
    String getRegistTime();
    String getUserId();
    String getUrl();
}
