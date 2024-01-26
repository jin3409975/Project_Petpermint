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

}