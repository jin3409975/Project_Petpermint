package com.ssafy.db.repository;

import com.ssafy.db.entity.Reservation;
import com.ssafy.db.join.ReservationExpertUserList;
import com.ssafy.db.join.ReservationHospitalList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    @Query(value = "select u.userName, e.hospitalName, e.licenseNumber from User u join ExpertUser e on u.userId = e.userId " +
            "where u.isDelete = 0 and e.startTime <= :startTime and e.endTime >= :endTime and " +
            "licenseNumber not in (select licenseNumber from Reservation where time = :time and type = 1)", nativeQuery = true)
    List<ReservationExpertUserList> findAllAvailableExpert(String startTime, String endTime, String time);

    @Query(value = "select name, roadNumberAddress, hospitalNo from HospitalData " +
            "where hospitalNo != 0 and hospitalNo not in " +
            "(select hospitalNo from Reservation where time = :time and type = 2) limit 10", nativeQuery = true)
    List<ReservationHospitalList> findAllAvailableHospital(String time);
}