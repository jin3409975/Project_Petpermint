package com.ssafy.db.join;

import org.hibernate.annotations.Type;

import javax.persistence.Column;

public interface PostUrlList {

    int postId();
    int hits();
    String content();
    int likes();
    String registTime();
    String userId();
    String url();
}
