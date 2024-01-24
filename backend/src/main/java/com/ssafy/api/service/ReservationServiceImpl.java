package com.ssafy.api.service;

import com.ssafy.api.request.ReservationRegisterPostReq;
import com.ssafy.db.entity.Reservation;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.ReservationRepository;
import com.ssafy.db.repository.UserRepository;
import com.ssafy.db.repository.UserRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

		Reservation result = reservationRepository.save(reservation);

        return result != null;
	}

//	상담 예약 전체 조회
	public List<Reservation> getAllConsultReservations() {
		return reservationRepository.findByType(1); // 1은 상담 예약을 나타내는 타입.
	}


//	병원 예약 생성
	public boolean createHospitalReservation(ReservationRegisterPostReq reservationRegisterPostReq) {
		Reservation reservation = new Reservation();
		reservation.setTime(reservationRegisterPostReq.getTime());
		reservation.setType(2);
		reservation.setUserId(reservationRegisterPostReq.getUserId());
		reservation.setHospitalNo(reservationRegisterPostReq.getHospitalNo());

		Reservation result = reservationRepository.save(reservation);

		return result != null;
	}

	// 병원 예약 전체 조회
	public List<Reservation> getAllHospitalReservations() {
		return reservationRepository.findByType(2); // 2는 병원 예약을 나타내는 타입.
	}
}
