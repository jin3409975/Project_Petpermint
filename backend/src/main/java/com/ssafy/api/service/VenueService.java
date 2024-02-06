package com.ssafy.api.service;


import com.ssafy.api.request.VenueDataGetReq;
import com.ssafy.db.entity.VenueData;

import java.util.List;

/**
 *	편의시설 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface VenueService {
    List<VenueData> dataVenue(VenueDataGetReq dataInfo);

    List<String> getSidoData();

    List<String> getMyundongData(String sido, String gungu);

    List<String> getGunguData(String sido);
}

