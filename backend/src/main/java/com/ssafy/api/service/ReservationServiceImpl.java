package com.ssafy.api.service;

import com.ssafy.api.request.ReservationRegisterPostReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.Reservation;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.ReservationRepository;
import com.ssafy.db.repository.UserRepository;
import com.ssafy.db.repository.UserRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("ReservationService")
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationRepository reservationRepository;
	@Override
	public boolean createConsultReservation(ReservationRegisterPostReq reservationRegisterPostReq) {
		Reservation reservation = new Reservation();
		reservation.setTime(reservationRegisterPostReq.getTime());
		reservation.setType(1);
		reservation.setUserId(reservationRegisterPostReq.getUserId());

		Reservation result = reservationRepository.save(reservation);

        return result != null;
	}
}
