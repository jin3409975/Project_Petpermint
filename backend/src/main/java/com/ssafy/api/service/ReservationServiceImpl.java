package com.ssafy.api.service;

import com.ssafy.api.request.ConsultReservationUpdatePutReq;
import com.ssafy.api.request.HospitalReservationUpdatePutReq;
import com.ssafy.api.request.ReservationRegisterPostReq;
import com.ssafy.db.entity.Reservation;
import com.ssafy.db.join.ReservationExpertUserList;
import com.ssafy.db.join.ReservationHospitalList;
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
		reservation.setAnimalId(reservationRegisterPostReq.getAnimalId());
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
	public boolean updateConsultReservation(ConsultReservationUpdatePutReq consultReservationUpdatePutReq) {
		Reservation reservation = reservationRepository.findByAppointId(consultReservationUpdatePutReq.getAppointId());

		if (reservation == null) {
			return false; // 해당 예약이 존재하지 않으면 수정 실패
		}

		// 예약 정보 업데이트
		reservation.setTime(consultReservationUpdatePutReq.getTime());
		reservation.setLicenseNumber(consultReservationUpdatePutReq.getLicenseNumber());
		reservation.setNote(consultReservationUpdatePutReq.getNote());

		// Repository를 통해 업데이트
		reservationRepository.save(reservation);

		return true;
	}

//	상담 예약 삭제
	public boolean deleteConsultReservation(int appointId) {
		Reservation reservation = reservationRepository.findByAppointId(appointId);

		if (reservation == null) {
			return false; // 해당 예약이 존재하지 않으면 삭제 실패
		}

		// 삭제
		reservation.setIsDelete(1);
//		reservation.setLicenseNumber(consultReservationDeleteDeleteReq.getLicenseNumber());
//		reservation.setNote(consultReservationDeleteDeleteReq.getNote());

		// Repository를 통해 업데이트
		reservationRepository.save(reservation);

		return true;
}




//	병원 예약 생성
	public boolean createHospitalReservation(ReservationRegisterPostReq reservationRegisterPostReq) {
		Reservation reservation = new Reservation();
		reservation.setTime(reservationRegisterPostReq.getTime());
		reservation.setType(2);
		reservation.setUserId(reservationRegisterPostReq.getUserId());
		reservation.setHospitalNo(reservationRegisterPostReq.getHospitalNo());
		reservation.setNote(reservationRegisterPostReq.getNote());
		reservation.setAnimalId(reservationRegisterPostReq.getAnimalId());
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

	// 병원 예약 수정
	public boolean updateHospitalReservation(HospitalReservationUpdatePutReq hospitalReservationUpdatePutReq) {
		Reservation reservation = reservationRepository.findByAppointId(hospitalReservationUpdatePutReq.getAppointId());

		if (reservation == null) {
			return false; // 해당 예약이 존재하지 않으면 수정 실패
		}

		// 예약 정보 업데이트
		reservation.setTime(hospitalReservationUpdatePutReq.getTime());
		reservation.setHospitalNo(hospitalReservationUpdatePutReq.getHospitalNo());
		reservation.setNote(hospitalReservationUpdatePutReq.getNote());

		// Repository를 통해 업데이트
		reservationRepository.save(reservation);

		return true;
	}

	@Override
	public List<ReservationExpertUserList> findAllAvailableExpert(String startTime, String endTime, String time) {
		List<ReservationExpertUserList> result = reservationRepository.findAllAvailableExpert(startTime, endTime, time);
		return result;
	}

	@Override
	public List<ReservationHospitalList> findAllAvailableHospital(String time) {
		List<ReservationHospitalList> result = reservationRepository.findAllAvailableHospital(time);
		return result;
	//	상담 예약 삭제
	public boolean deleteHospitalReservation(int appointId) {
		Reservation reservation = reservationRepository.findByAppointId(appointId);

		if (reservation == null) {
			return false; // 해당 예약이 존재하지 않으면 삭제 실패
		}

		// 삭제
		reservation.setIsDelete(1);
//		reservation.setLicenseNumber(consultReservationDeleteDeleteReq.getLicenseNumber());
//		reservation.setNote(consultReservationDeleteDeleteReq.getNote());

		// Repository를 통해 업데이트
		reservationRepository.save(reservation);

		return true;
	}
}
