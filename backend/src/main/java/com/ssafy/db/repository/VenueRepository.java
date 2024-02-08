package com.ssafy.db.repository;

import com.ssafy.db.entity.VenueData;
import com.ssafy.db.entity.VideoRoom;
import org.kurento.client.internal.server.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


/**
 * 편의시설 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface VenueRepository extends JpaRepository<VenueData, Long> {
    @Query(value = "select distinct sido from VenueData order by sido", nativeQuery = true )
    List<String> findSido();

    @Query(value = "select distinct myundong from VenueData where sido = :sido and gungu = :gungu order by myundong", nativeQuery = true )
    List<String> findMyundong(String sido, String gungu);

    @Query(value = "select distinct gungu from VenueData where sido = :sido order by gungu", nativeQuery = true )
    List<String> findGungu(String sido);

    @Query(value = "select * from VenueData where category = '동물병원' and " +
            "lat between :latStart and :latEnd and lon between :lonStart and :lonEnd", nativeQuery = true)
    List<VenueData> findEmergencyList(double latStart, double latEnd, double lonStart, double lonEnd);
}