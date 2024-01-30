package com.ssafy.api.service;

import com.ssafy.api.request.AnimalReq;
import com.ssafy.api.request.ExpertUserRegisterPostReq;
import com.ssafy.api.request.NormalUserRegisterPostReq;
import com.ssafy.db.entity.Animal;
import com.ssafy.db.entity.ExpertUser;
import com.ssafy.db.entity.User;
import com.ssafy.db.entity.Verification;

import java.util.List;
import java.util.Map;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface AnimalService {
	boolean animalCreate(AnimalReq animalReq,String url);
	List<Animal> findAllAnimalByUserId(String userId);
	Animal findAnimalByAnimalId(int animalId);

	boolean animalUpdate(AnimalReq animalReq, String url);

	boolean animalDelete(int animalId);
}
