package com.ssafy.db.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.request.CommunityUpdatePutReq;
import com.ssafy.db.entity.QUserPost;
import com.ssafy.db.entity.QVenueData;
import com.ssafy.db.entity.UserPost;
import com.ssafy.db.entity.VenueData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * 편의시설 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
@Transactional
public class VenueRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QVenueData qVenueData= QVenueData.venueData;

    public List<VenueData> findVenueDataByCategory(String[] category, String sido, String gungu, String myundong, String venName) {

        JPAQuery<VenueData> query = jpaQueryFactory.selectFrom(qVenueData);
        if(category!=null)
            query.where(qVenueData.category.in(category));
        if(sido!=null)
            query.where(qVenueData.sido.eq(sido));
        if(gungu!=null)
            query.where(qVenueData.gungu.like("%"+gungu+"%"));
        if(myundong!=null)
            query.where(qVenueData.myundong.eq(myundong));
        if(venName!=null)
            query.where(qVenueData.venName.like("%"+venName+"%"));

        List<VenueData> venueData= query.fetch();

        if(venueData == null) return null;
        return venueData;
    }


}
