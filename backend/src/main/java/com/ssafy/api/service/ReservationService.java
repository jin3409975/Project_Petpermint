package com.ssafy.api.service;

import com.ssafy.api.request.ReservationRegisterPostReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.Reservation;
import com.ssafy.db.entity.User;

import java.util.List;

/**
 *	예약 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface ReservationService {
	// 상담 예약 생성
	boolean createConsultReservation(ReservationRegisterPostReq reservationRegisterPostReq);
	// 상담 예약 전체 조회
	List<Reservation> getAllConsultReservations();

	// 병원 예약 생성
	boolean createHospitalReservation(ReservationRegisterPostReq reservationRegisterPostReq);
	// 병원 예약 전체 조회
	List<Reservation> getAllHospitalReservations();
}



