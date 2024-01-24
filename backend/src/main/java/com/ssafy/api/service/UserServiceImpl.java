package com.ssafy.api.service;

import com.ssafy.api.request.ExpertUserRegisterPostReq;
import com.ssafy.db.entity.ExpertUser;
import com.ssafy.db.entity.Verification;
import com.ssafy.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.NormalUserRegisterPostReq;
import com.ssafy.db.entity.User;

import java.util.Map;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserRepositorySupport userRepositorySupport;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	ExpertUserRepository expertUserRepository;

	@Autowired
	VerificationRepository verificationRepository;

	@Override
	public Boolean createNormalUser(NormalUserRegisterPostReq userRegisterInfo) {
		User user = new User();
		user.setUserId(userRegisterInfo.getUserId());
		user.setAddress(userRegisterInfo.getAddress());
		user.setUserName(userRegisterInfo.getUserName());
		user.setPhone(userRegisterInfo.getPhone());
		user.setType(1);
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));

		try {
			userRepository.save(user);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	public Boolean createExpertUser(ExpertUserRegisterPostReq userRegisterInfo) {
		User user = new User();
		user.setUserId(userRegisterInfo.getUserId());
		user.setAddress(userRegisterInfo.getAddress());
		user.setUserName(userRegisterInfo.getUserName());
		user.setPhone(userRegisterInfo.getPhone());
		user.setType(2);
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));

		ExpertUser expertUser = new ExpertUser();
		expertUser.setUserId(userRegisterInfo.getUserId());
		expertUser.setLicenseNumber(userRegisterInfo.getLicenseNumber());
		expertUser.setNote(userRegisterInfo.getNote());
		expertUser.setStartTime(userRegisterInfo.getStartTime());
		expertUser.setEndTime(userRegisterInfo.getEndTime());
		expertUser.setHospitalNo(userRegisterInfo.getHospitalNo());

		try {
			userRepository.save(user);
			expertUserRepository.save(expertUser);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public User getUserByUserId(String userId) {
		// 디비에 유저 정보 조회 (userId 를 통한 조회).
		User user = userRepository.findByUserIdAndIsDelete(userId,0);
		return user;
	}

	@Override
	public ExpertUser getExpertUserByUserId(String userId) {
		// 디비에 유저 정보 조회 (userId 를 통한 조회).
		ExpertUser expertUser = expertUserRepository.findByUserId(userId);
		return expertUser;
	}

	@Override
	public Boolean createVerification(String userId) {
		Verification verification = new Verification();
		Long check = userRepository.countByUserId(userId);
		System.out.println("Check = " + check);
		if(check == 1) {
			verification.setUserId(userId);
			verification.setVerificationCode(String.valueOf((int)(Math.random() * 899999)+100000));
			verificationRepository.save(verification);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean emailValidate(Verification verification) {
		Long result = verificationRepository.countByUserIdAndVerificationCode(verification.getUserId(), verification.getVerificationCode());
		return result == 1;
	}

	@Override
	public String recoverId(String userName, String phone) {
		User user = userRepository.findByUserNameAndPhone(userName, phone);

		if(user != null) {
			return user.getUserId();
		} else {
			return null;
		}
	}

	@Override
	public boolean recoverPassword(String userId, String userName, String password) {
		Long result = userRepository.countByUserIdAndUserName(userId, userName);
		if(result == 1) {
			userRepository.updatePassword(userId,userName,passwordEncoder.encode(password));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean userUpdateNormal(Map<String, Object> params) {
		if(userRepository.countByUserId((String)params.get("userId")) == 1) {
			userRepository.userUpdateNormal((String)params.get("userId"),(String)params.get("userName"),passwordEncoder.encode((String)params.get("password")),(String)params.get("picture"));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean userUpdateExpert(Map<String, Object> params) {
		if(userRepository.countByUserId((String)params.get("userId")) == 1) {
			userRepository.userUpdateNormal((String)params.get("userId"),(String)params.get("userName"),passwordEncoder.encode((String)params.get("password")),(String)params.get("picture"));
			userRepository.userUpdateExpert((String)params.get("userId"),(String)params.get("note"),(String)params.get("startTime"),(String)params.get("endTime"),(String)params.get("hospitalNo"));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteUser(String userId) {
		if(userRepository.countByUserId(userId) == 1) {
			userRepository.deleteUser(userId);
			return true;
		}
		return false;
	}

}
