package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.ExpertUser;
import com.ssafy.db.entity.QExpertUser;
import com.ssafy.db.entity.QUser;
import com.ssafy.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class ExpertUserRepositorySupport{
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QExpertUser qExpertUser = QExpertUser.expertUser;


    public Optional<ExpertUser> findByUserId(String userId) {
        ExpertUser user = jpaQueryFactory.select(qExpertUser).from(qExpertUser)
                .where(qExpertUser.userId.eq(userId)).fetchOne();
        if(user == null) return Optional.empty();
        return Optional.ofNullable(user);
    }
}
