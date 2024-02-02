package com.ssafy.db.repository;

import com.ssafy.db.entity.UserPost;
import com.ssafy.db.join.PostUrlList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 커뮤니티 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface CommunityRepository extends JpaRepository<UserPost, Long> {
    List<UserPost> findByIsDelete(boolean i);

    @Query(value = "select u.postId, u.hits, u.content, u.likes, u.registTime, u.userId, p.url " +
            "from UserPost u " +
            "join PostFiles p " +
            "on u.postId = p.postId " +
            "where u.isDelete = 0 ", nativeQuery = true)
    List<PostUrlList> findPostUrlJoin();
}