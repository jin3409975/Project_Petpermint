package com.ssafy.api.service;

import com.ssafy.api.request.ExpertUserRegisterPostReq;
import com.ssafy.api.request.NormalUserRegisterPostReq;
import com.ssafy.api.request.VenueDataGetReq;
import com.ssafy.db.entity.*;
import com.ssafy.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("venueService")
public class VenueServiceImpl implements VenueService {
	@Autowired
	VenueRepository venueRepository;

	@Override
	public List<VenueData> dataVenue(VenueDataGetReq dataInfo) {
		String[] category=dataInfo.getCategory();
		String sido=dataInfo.getSido();
		String gungu= dataInfo.getGungu();
		String myungdong= dataInfo.getMyundong();
		String venName=dataInfo.getVenName();
		List<VenueData> dataList = venueRepository.getList(category,sido,gungu,myungdong,venName);
		return dataList;
	}
}
