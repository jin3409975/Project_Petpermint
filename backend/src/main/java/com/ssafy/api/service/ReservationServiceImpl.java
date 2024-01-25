package com.ssafy.api.service;

import com.ssafy.api.request.ReservationRegisterPostReq;
import com.ssafy.db.entity.Reservation;
import com.ssafy.db.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *	예약 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("ReservationService")
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationRepository reservationRepository;
	@Override
//	상담 예약 생성
	public boolean createConsultReservation(ReservationRegisterPostReq reservationRegisterPostReq) {
		Reservation reservation = new Reservation();
		reservation.setTime(reservationRegisterPostReq.getTime());
		reservation.setType(1);
		reservation.setUserId(reservationRegisterPostReq.getUserId());
		reservation.setLicenseNumber(reservationRegisterPostReq.getLicenseNumber());
		reservation.setNote(reservationRegisterPostReq.getNote());

		Reservation result = reservationRepository.save(reservation);

        return result != null;
	}

//	특정 사용자의 상담 예약 전체 조회
	public List<Reservation> getAllConsultReservations(String userId) {
		return reservationRepository.findByTypeAndAndUserId(1, userId); // 1은 상담 예약을 나타내는 타입.
	}

//  특정 사용자의 상담 예약 개별 조회
	public Reservation getConsultReservation(int appointId) {
		return reservationRepository.findByAppointId(appointId);
	}

//	상담 예약 수정






//	병원 예약 생성
	public boolean createHospitalReservation(ReservationRegisterPostReq reservationRegisterPostReq) {
		Reservation reservation = new Reservation();
		reservation.setTime(reservationRegisterPostReq.getTime());
		reservation.setType(2);
		reservation.setUserId(reservationRegisterPostReq.getUserId());
		reservation.setHospitalNo(reservationRegisterPostReq.getHospitalNo());
		reservation.setNote(reservationRegisterPostReq.getNote());

		Reservation result = reservationRepository.save(reservation);

		return result != null;
	}

	// 특정 사용자의 병원 예약 전체 조회
	public List<Reservation> getAllHospitalReservations(String userId) {
		return reservationRepository.findByTypeAndAndUserId(2,userId); // 2는 병원 예약을 나타내는 타입.
	}

	// 특정 사용자의 병원 예약 개별 조회
	public Reservation getHospitalReservation(int appointId) {
		return reservationRepository.findByAppointId(appointId);
	}
}
