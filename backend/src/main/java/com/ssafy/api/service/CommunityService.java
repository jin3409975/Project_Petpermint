package com.ssafy.api.service;

import com.ssafy.api.request.*;
import com.ssafy.db.entity.*;
import com.ssafy.db.join.PostUrlList;

import java.util.List;

/**
 *	키뮤니티 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface CommunityService {


    UserPost writePost(CommunityWritePostReq writeInfo);
    UserPost dataPost(int postId);
    Long updatePost(CommunityUpdatePutReq updateInfo);
    Long isDeletePost(int postId);
    Long deletePost(int postId);
    PostComment createComment(CommentCreatePostReq createInfo);
    List<PostComment> dataComment(int postId);
    Long isDeleteComment(CommentDeleteDeleteReq deleteInfo);
    Long deleteComment(CommentDeleteDeleteReq deleteInfo);
    Long increaseLike(int postId);
    Long increaseHit(int postId);

    int findPostCount();

    int writeUrl(int postId, List<String>urls, List<String> strings);
    List<String> getUrl(int postId);
    int deleteUrl(int postId);

    List<UserPost> listPost();
    List<PostFiles> listUrl();


    List<PostUrlList> findPostUrlJoin(int page,List<Integer>postIds);

    PostLikes insertIntoLikeTable(int postId, String userId);

    PostLikes findPostLikesByPostIdUserId(int postId, String userId);
}

