package com.ssafy.api.service;

import com.ssafy.api.request.ConsultReservationUpdatePutReq;
import com.ssafy.api.request.HospitalReservationUpdatePutReq;
import com.ssafy.api.request.ReservationRegisterPostReq;
import com.ssafy.db.entity.Reservation;

import java.util.List;

/**
 *	예약 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface ReservationService {
	// 상담 예약 생성
	boolean createConsultReservation(ReservationRegisterPostReq reservationRegisterPostReq);
	// 특정 사용자의 상담 예약 전체 조회
	List<Reservation> getAllConsultReservations(String userId);
	// 특정 사용자의 상담 예약 개별 조회
	Reservation getConsultReservation(int appointId);
	// 상담 예약 수정
	boolean updateConsultReservation(ConsultReservationUpdatePutReq consultReservationUpdatePutReq);




	// 병원 예약 생성
	boolean createHospitalReservation(ReservationRegisterPostReq reservationRegisterPostReq);
	// 특정 사용자의 병원 예약 전체 조회
	List<Reservation> getAllHospitalReservations(String userId);
	// 특정 사용자의 병원 예약 개별 조회
	Reservation getHospitalReservation(int appointId);
	// 병원 예약 수정
	boolean updateHospitalReservation(HospitalReservationUpdatePutReq hospitalReservationUpdatePutReq);
}



