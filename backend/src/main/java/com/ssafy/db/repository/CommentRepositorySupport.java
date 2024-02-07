package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.request.CommentDeleteDeleteReq;
import com.ssafy.db.entity.PostComment;
import com.ssafy.db.entity.QPostComment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * 커뮤니티 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
@Transactional
public class CommentRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QPostComment qpostComment= QPostComment.postComment;

    public Optional<List<PostComment>> findCommentByPostId(int postId) {
        List<PostComment> postComment= jpaQueryFactory.select(qpostComment).from(qpostComment)
                .where(qpostComment.postId.eq(postId)).fetch();
        if(postComment == null) return Optional.empty();
        return Optional.ofNullable(postComment);
    }

    public Optional<Long> isDeleteCommentByDeleteInfo(CommentDeleteDeleteReq deleteinfo) {
        int postId=deleteinfo.getPostId();
        int commentNo=deleteinfo.getCommentNo();
        Long result = jpaQueryFactory.update(qpostComment).set(qpostComment.isDelete,true)
                .where(qpostComment.postId.eq(postId)
                    .and(qpostComment.commentNo.eq(commentNo)))
                .execute();
        if(result == 0) return Optional.empty();
        return Optional.ofNullable(result);
    }

    public Optional<Long> deleteCommentByDeleteInfo(CommentDeleteDeleteReq deleteinfo) {
        int postId=deleteinfo.getPostId();
        int commentNo=deleteinfo.getCommentNo();
        Long result = jpaQueryFactory.delete(qpostComment)
                .where(qpostComment.postId.eq(postId)
                    .and(qpostComment.commentNo.eq(commentNo)))
            .execute();
        if (result == 0) return Optional.empty();
        return Optional.ofNullable(result);
    }
}
