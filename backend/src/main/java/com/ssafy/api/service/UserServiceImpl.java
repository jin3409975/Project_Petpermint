package com.ssafy.api.service;

import
		com.ssafy.api.request.ExpertUserRegisterPostReq;
import com.ssafy.api.request.ExpertUserUpdatePutReq;
import com.ssafy.api.request.NormalUserUpdatePutReq;
import com.ssafy.db.entity.ExpertUser;
import com.ssafy.db.entity.LicensePicture;
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

	@Autowired
	LicensePictureRepository licensePictureRepository;

	@Override
	public Boolean createNormalUser(NormalUserRegisterPostReq userRegisterInfo,String url) {
		User user = new User();
		user.setUserId(userRegisterInfo.getUserId());
		user.setAddress(userRegisterInfo.getAddress());
		user.setUserName(userRegisterInfo.getUserName());
		user.setPhone(userRegisterInfo.getPhone());
		user.setPicture(url);
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
	public Boolean createExpertUser(ExpertUserRegisterPostReq userRegisterInfo, String url, String license) {
		User user = new User();
		user.setUserId(userRegisterInfo.getUserId());
		user.setAddress(userRegisterInfo.getAddress());
		user.setUserName(userRegisterInfo.getUserName());
		user.setPhone(userRegisterInfo.getPhone());
		user.setPicture(url);
		user.setType(2);
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));

		ExpertUser expertUser = new ExpertUser();
		expertUser.setUserId(userRegisterInfo.getUserId());
		expertUser.setLicenseNumber(userRegisterInfo.getLicenseNumber());
		expertUser.setNote(userRegisterInfo.getNote());
		expertUser.setStartTime(userRegisterInfo.getStartTime());
		expertUser.setEndTime(userRegisterInfo.getEndTime());
		expertUser.setHospitalName(userRegisterInfo.getHospitalName());

		LicensePicture licensePicture = new LicensePicture();
		licensePicture.setLicenseNumber(userRegisterInfo.getLicenseNumber());
		licensePicture.setPicture(license);
		try {
			userRepository.save(user);
			expertUserRepository.save(expertUser);
			licensePictureRepository.save(licensePicture);

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
	public boolean userUpdateNormal(NormalUserUpdatePutReq updatePutReq, String url) {
		if(userRepository.countByUserId(updatePutReq.getUserId()) == 1) {
			userRepository.userUpdateNormal(updatePutReq.getUserId(),updatePutReq.getUserName(),url,updatePutReq.getAddress(), updatePutReq.getPhone());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean userUpdateExpert(ExpertUserUpdatePutReq updatePutReq, String url) {
		if(userRepository.countByUserId(updatePutReq.getUserId()) == 1) {
			userRepository.userUpdateNormal(updatePutReq.getUserId(),updatePutReq.getUserName(),url, updatePutReq.getAddress(), updatePutReq.getPhone());
			userRepository.userUpdateExpert(updatePutReq.getUserId(),updatePutReq.getNote(),updatePutReq.getStartTime(),updatePutReq.getEndTime());
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

	@Override
	public String findPicture(String userId) {
		String result = userRepository.findPicture(userId);
		return result;
	}


}
