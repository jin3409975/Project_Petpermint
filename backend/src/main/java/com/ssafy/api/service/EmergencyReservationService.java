package com.ssafy.api.service;

import com.ssafy.api.request.EmergencyReservationRegisterPostReq;
import com.ssafy.db.entity.Reservation;

import java.util.List;

/**
 *	응급 예약 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface EmergencyReservationService {
	// 응급 예약 생성
	boolean createEmergencyReservation(EmergencyReservationRegisterPostReq emergencyReservationRegisterPostReq);
	// 특정 사용자의 응급 예약 전체 조회
	List<Reservation> getAllEmergencyReservations(String userId);
	// 특정 사용자의 응급 예약 개별 조회
	Reservation getEmergencyReservation(int appointId);

}



