package com.ssafy.api.service;

import com.ssafy.api.request.*;
import com.ssafy.db.entity.*;
import org.springframework.web.multipart.MultipartFile;

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
    PostComment dataComment(int postId);
    Long isDeleteComment(CommentDeleteDeleteReq deleteInfo);
    Long deleteComment(CommentDeleteDeleteReq deleteInfo);
    Long increaseLike(int postId);
    Long increaseHit(int postId);

    int findPostCount();

    int writeUrl(int postId, List<String>urls, List<String> strings);
    List<String> getUrl(int postId);
    int deleteUrl(int postId);
}

