package com.ssafy.api.service;

import com.ssafy.api.request.VenueDataGetReq;
import com.ssafy.db.entity.*;
import com.ssafy.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *	편의시설 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("venueService")
public class VenueServiceImpl implements VenueService {
	@Autowired
	VenueRepositorySupport venueRepositorySupport;

	@Autowired
	VenueRepository venueRepository;

	@Override
	public List<VenueData> dataVenue(VenueDataGetReq dataInfo) {
		String[] category=dataInfo.getCategory();
		String sido=dataInfo.getSido();
		String gungu= dataInfo.getGungu();
		String myundong= dataInfo.getMyundong();
		String venName=dataInfo.getVenName();

		List<VenueData> dataList = venueRepositorySupport.findVenueDataByCategory(category,sido,gungu,myundong,venName);
		return dataList;
	}

	@Override
	public List<String> getSidoData() {
		List<String> sido = venueRepository.findSido();
		return sido;
	}

	@Override
	public List<String> getMyundongData(String sido, String gungu) {
		return venueRepository.findMyundong(sido, gungu);
	}

	@Override
	public List<String> getGunguData(String sido) {
		return venueRepository.findGungu(sido);
	}
}
