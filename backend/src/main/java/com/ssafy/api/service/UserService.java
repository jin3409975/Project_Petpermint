package com.ssafy.api.service;

import com.ssafy.api.request.ExpertUserRegisterPostReq;
import com.ssafy.api.request.ExpertUserUpdatePutReq;
import com.ssafy.api.request.NormalUserRegisterPostReq;
import com.ssafy.api.request.NormalUserUpdatePutReq;
import com.ssafy.db.entity.ExpertUser;
import com.ssafy.db.entity.User;
import com.ssafy.db.entity.Verification;

import java.util.Map;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserService {
	Boolean createNormalUser(NormalUserRegisterPostReq userRegisterInfo, String url);
	Boolean createExpertUser(ExpertUserRegisterPostReq userRegisterInfo, String url, String license);
	User getUserByUserId(String userId);
	ExpertUser getExpertUserByUserId(String userId);
	Boolean createVerification(String userId);
	Boolean emailValidate(Verification verification);
	String recoverId(String userName, String phone);
	boolean recoverPassword(String userId, String userName, String password);
	boolean userUpdateNormal(NormalUserUpdatePutReq updatePutReq, String url);
	boolean userUpdateExpert(ExpertUserUpdatePutReq updatePutReq, String url);
	boolean deleteUser(String userId);
}
