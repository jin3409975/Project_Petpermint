package com.ssafy.api.service;

import com.ssafy.api.request.EmergencyReservationRegisterPostReq;
import com.ssafy.db.entity.Reservation;
import com.ssafy.db.entity.VenueData;
import com.ssafy.db.repository.EmergencyReservationRepository;
import com.ssafy.db.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *	응급 예약 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("EmregencyReservationService")
public class EmergencyReservationServiceImpl implements EmergencyReservationService {

	@Autowired
	EmergencyReservationRepository emergencyReservationRepository;

	@Autowired
	VenueRepository venueRepository;

	@Override
//	응급 예약 생성
	public boolean createEmergencyReservation(EmergencyReservationRegisterPostReq emergencyReservationRegisterPostReq) {
		Reservation reservation = new Reservation();
		reservation.setType(3);    // 3은 응급 예약.
		reservation.setUserId(emergencyReservationRegisterPostReq.getUserId());
		reservation.setHospitalNo(emergencyReservationRegisterPostReq.getHospitalNo());
		reservation.setTime(emergencyReservationRegisterPostReq.getTime());
		reservation.setAnimalId(0);
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

	@Override
	public String getAddress(String userId) {
		return emergencyReservationRepository.findAddress(userId);
	}

	@Override
	public List<VenueData> getEmergencyList(double lat, double lon) {
		System.out.println("coords " + (lat-0.2) + " " + (lat+0.2) + " " + (lon-0.2) + " " + (lon+0.2) + " ");
		List<VenueData> result = venueRepository.findEmergencyList(lat-0.2,lat+0.2,lon-0.2,lon+0.2);
		System.out.println(result);

		Map<Integer,Double> dist = new HashMap<Integer, Double>();

		int index = 0;
		for (VenueData data : result) {
			double theta = lon - Double.parseDouble(data.getLon());
			double distance = 60 * 1.1515 * (180/Math.PI) * Math.acos(
					Math.sin(lat * (Math.PI/180)) * Math.sin(Double.parseDouble(data.getLat()) * (Math.PI/180)) +
							Math.cos(lat * (Math.PI/180)) * Math.cos(Double.parseDouble(data.getLat()) * (Math.PI/180)) * Math.cos(theta * (Math.PI/180))
			);
			dist.put(index,distance);
			index++;
		}

		List<Integer> sort = new ArrayList<>(dist.keySet());

		sort.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return dist.get(o1).compareTo(dist.get(o2));
			}
		});

		List<VenueData> data = new ArrayList<>();
		for (int i =0; i<sort.size(); i++) {
			data.add(result.get(sort.get(i)));
			if(i == 4) break;
		}

		System.out.println(data);
		return data;
	}

}
