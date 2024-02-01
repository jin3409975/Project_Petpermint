package com.ssafy.api.service;

import com.ssafy.api.request.EmergencyReservationRegisterPostReq;
import com.ssafy.db.entity.Reservation;
import com.ssafy.db.repository.EmergencyReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *	응급 예약 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("EmregencyReservationService")
public class EmergencyReservationServiceImpl implements EmergencyReservationService {

	@Autowired
	EmergencyReservationRepository emergencyReservationRepository;
	@Override
//	응급 예약 생성
	public boolean createEmergencyReservation(EmergencyReservationRegisterPostReq emergencyReservationRegisterPostReq) {
		Reservation reservation = new Reservation();
		reservation.setType(3);    // 3은 응급 예약.
		reservation.setUserId(emergencyReservationRegisterPostReq.getUserId());
		reservation.setHospitalNo(emergencyReservationRegisterPostReq.getHospitalNo());
		reservation.setTime("0");
		Reservation result = emergencyReservationRepository.save(reservation);

		return result != null;
	}

	// 특정 사용자의 병원 예약 전체 조회
	public List<Reservation> getAllEmergencyReservations(String userId) {
		return emergencyReservationRepository.findByTypeAndAndUserId(3,userId); // 3은 응급 예약을 나타내는 타입.
	}

	// 특정 사용자의 병원 예약 개별 조회
	public Reservation getEmergencyReservation(int appointId) {
		return emergencyReservationRepository.findByAppointId(appointId);
	}

}
