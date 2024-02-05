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
    @Query(value = "select DISTINCT u.postId AS postId, s.userName as userName, u.hits AS hits, u.content AS content," +
            " u.likes AS likes, u.registTime AS registTime, u.userId AS userId, p.url AS url " +
            "from UserPost u " +
            "left join PostFiles p " +
            "ON u.postId = p.postId " +
            "left join User s " +
            "ON u.userId = s.userId " +
            "where u.isDelete = 0 "+
            "GROUP BY u.postId ORDER BY u.postId", nativeQuery = true)
    List<PostUrlList> findPostUrlJoin();
}