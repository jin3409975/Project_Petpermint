package com.ssafy.db.repository;

import com.ssafy.db.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 예약 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // 아래와 같이, Query Method 인터페이스(반환값, 메소드명, 인자) 정의를 하면 자동으로 Query Method 구현됨.

    // 특정 사용자의 상담 & 병원 예약 전체 조회
    List<Reservation> findByTypeAndAndUserId(int type, String userId);

    // 특정 사용자의 상담 & 병원 예약 개별 조회
    Reservation findByAppointId(int appointId);

}