package com.ssafy.api.controller;

import com.ssafy.api.request.*;
import com.ssafy.api.response.*;
import com.ssafy.api.service.AnimalService;
import com.ssafy.api.service.EmailValidateService;
import com.ssafy.api.service.S3UpDownloadService;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.db.entity.Animal;
import com.ssafy.db.entity.ExpertUser;
import com.ssafy.db.entity.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "유저 API", tags = {"User"})
@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;

	@Autowired
	AnimalService animalService;

	@Autowired
	S3UpDownloadService s3service;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	EmailValidateService emailValidateService;

	@PostMapping("/signup/normal")
	@ApiOperation(value = "일반 사용자 회원 가입", notes = "<strong>아이디, 패스워드, 이름, 주소, 전화번호</strong>를 통해 회원가입 한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> signupNormal(
			@ModelAttribute @ApiParam(value="회원가입 정보", required = true) NormalUserRegisterPostReq registerInfo) throws IOException {
		System.out.println(registerInfo);
		String url = "0";
		if(registerInfo.getPicture() != null) {
			url=s3service.saveProfile(registerInfo.getPicture(),registerInfo.getUserId());
		}

		if(userService.createNormalUser(registerInfo,url)) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		} else {
			return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
		}

	}
	
	@PostMapping("/signup/expert")
	@ApiOperation(value = "수의사 회원 가입", notes = "<strong>아이디, 패스워드, 이름, 면허번호, 소개, 진료 가능 시간</strong>을 통해 회원가입 한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 400, message = "실패")
    })
	public ResponseEntity<? extends BaseResponseBody> signupExpert(
			@ModelAttribute @ApiParam(value="회원가입 정보", required = true) ExpertUserRegisterPostReq registerInfo) throws IOException {
		System.out.println(registerInfo);
		String url = "0";
		if(registerInfo.getPicture() != null) {
			url=s3service.saveProfile(registerInfo.getPicture(),registerInfo.getUserId());
		}

		String license = "0";
		if(registerInfo.getLicensePicture() != null) {
			license = s3service.saveLicenseFile(registerInfo.getLicensePicture(), registerInfo.getLicenseNumber());
		}

		if(userService.createExpertUser(registerInfo, url, license)) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		} else {
			return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
		}
	}

	@GetMapping("/me/normal")
	@ApiOperation(value = "일반 사용자 정보 조회", notes = "로그인 되어 있는 일반 사용자의 아이디를 기반으로 정보를 조회한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 400, message = "실패")
	})
	public ResponseEntity<? extends BaseResponseBody> meNormal(
			@ApiParam(value="현재 로그인 되어 있는 아이디", required = true) String userId) {
		User user = userService.getUserByUserId(userId);
		if(user != null) {
			return ResponseEntity.status(200).body(UserRes.of(200, "Success", user));
		} else {
			return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
		}
	}

	@GetMapping("/me/expert")
	@ApiOperation(value = "수의사 사용자 정보 조회", notes = "로그인 되어 있는 수의사 사용자의 아이디를 기반으로 정보를 조회한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 400, message = "실패")
	})
	public ResponseEntity<? extends BaseResponseBody> meExpert(
			@ApiParam(value="현재 로그인 되어 있는 아이디", required = true) String userId) {
		User user = userService.getUserByUserId(userId);
		ExpertUser expertUser = userService.getExpertUserByUserId(userId);
		if(user != null) {
			return ResponseEntity.status(200).body(ExpertUserRes.of(200, "Success", user, expertUser));
		} else {
			return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
		}
	}

	@PostMapping("/email/request")
	@ApiOperation(value = "이메일 인증 신청", notes = "사용자가 작성한 이메일 주소로 랜덤한 6자리 숫자를 보내는 이메일 인증을 신청한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 400, message = "실패")
	})
	public ResponseEntity<? extends BaseResponseBody> emailRequest(
			@RequestBody @ApiParam(value="이메일 인증에 사용 할 이메일 주소", required = true) EmailValidateReq emailValidateReq) {
		System.out.println(emailValidateReq.getUserId());
		String result = emailValidateService.emailContent(emailValidateReq.getUserId());
		System.out.println("이메일 인증 이메일 : "+emailValidateReq.getUserId());
		System.out.println("이메일 인증 번호 : "+result);
		if(result != null) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		} else {
			return ResponseEntity.status(200).body(BaseResponseBody.of(400, "Fail"));
		}
	}

	@PostMapping("/email/validate")
	@ApiOperation(value = "이메일 인증 검증", notes = "사용자가 요청한 이메일 인증을 검증한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 400, message = "실패")
	})
	public ResponseEntity<? extends BaseResponseBody> emailValidate(
			@RequestBody @ApiParam(value="회원가입 정보", required = true) Verification verification) {
		if(emailValidateService.validate(verification.getUserId(),verification.getVerificationCode())) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		} else {
			return ResponseEntity.status(200).body(BaseResponseBody.of(400, "Fail"));
		}
	}

	@PostMapping("/login")
	@ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
	})
	public ResponseEntity<UserLoginPostRes> login(@RequestBody @ApiParam(value="로그인 정보", required = true) UserLoginPostReq loginInfo) {
		String userId = loginInfo.getUserId();
		String password = loginInfo.getPassword();

		User user = userService.getUserByUserId(userId);
		// 로그인 요청한 유저로부터 입력된 패스워드 와 디비에 저장된 유저의 암호화된 패스워드가 같은지 확인.(유효한 패스워드인지 여부 확인)
		if(passwordEncoder.matches(password, user.getPassword())) {
			// 유효한 패스워드가 맞는 경우, 로그인 성공으로 응답.(액세스 토큰을 포함하여 응답값 전달)
			if(user.getType() == 1 || user.getType() == 3) {
				return ResponseEntity.ok(UserLoginPostRes.of(200, "Success", JwtTokenUtil.getToken(userId),userId,user.getUserName(),user.getType(), null));
			} else if(user.getType() == 2) {
				ExpertUser expertUser = userService.getExpertUserByUserId(user.getUserId());
				return ResponseEntity.ok(UserLoginPostRes.of(200, "Success", JwtTokenUtil.getToken(userId),userId,user.getUserName(),user.getType(),expertUser.getLicenseNumber()));
			}

		}
		// 유효하지 않는 패스워드인 경우, 로그인 실패로 응답.
		return ResponseEntity.status(401).body(UserLoginPostRes.of(401, "Invalid Login", null, null, null, 0, null));
	}

	@PostMapping("/recover/id")
	@ApiOperation(value = "아이디 찾기", notes = "<strong>이름과 전화번호</strong>를 통해 아이디를 찾는다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 400, message = "실패")
	})
	public ResponseEntity<RecoverRes> recoverId(@RequestBody @ApiParam(value="로그인 정보", required = true) RecoverReq recoverReq) {
		String userName = recoverReq.getUserName();
		String phone = recoverReq.getPhone();

		String userId = userService.recoverId(userName, phone);

		if(userId != null) {
			return ResponseEntity.ok(RecoverRes.of(200, "Success", userId));
		} else {
			return ResponseEntity.ok(RecoverRes.of(400, "Fail", null));
		}
	}


	@PostMapping("/recover/password")
	@ApiOperation(value = "비밀번호 초기화", notes = "<strong>아이디와 이름, 이메일 인증</strong>를 통해 패스워드를 변경한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 400, message = "실패")
	})
	public ResponseEntity<BaseResponseBody> recoverPassword(@RequestBody @ApiParam(value="로그인 정보", required = true) RecoverReq recoverReq) {
		String userId = recoverReq.getUserId();
		String userName = recoverReq.getUserName();
		String password = recoverReq.getPassword();

		if(userService.recoverPassword(userId, userName, password)) {
			return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
		} else {
			return ResponseEntity.ok(BaseResponseBody.of(400, "Fail"));
		}
	}

	@PutMapping("/update/normal")
	@ApiOperation(value = "일반 사용자 정보 수정", notes = "현재 로그인 한 유저의 아이디를 통해 해당 유저의 비밀번호, 이름, 프로필 사진을 수정한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 400, message = "실패")
	})
	public ResponseEntity<? extends BaseResponseBody> updateNormal(
			 @ApiParam(value="수정할 회원 정보") @ModelAttribute NormalUserUpdatePutReq updatePutReq) throws IOException {
		String url=s3service.saveProfile(updatePutReq.getPicture(), updatePutReq.getUserId());
		if(userService.userUpdateNormal(updatePutReq,url)) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		} else {
			return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
		}
	}

	@PutMapping("/update/expert")
	@ApiOperation(value = "일반 사용자 정보 수정", notes = "현재 로그인 한 유저의 아이디를 통해 해당 유저의 비밀번호, 이름, 프로필 사진을 수정한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 400, message = "실패")
	})
	public ResponseEntity<? extends BaseResponseBody> updateExpert(
			@ApiParam(value="수정할 회원 정보") @ModelAttribute ExpertUserUpdatePutReq updatePutReq) throws IOException {
		String url=s3service.saveProfile(updatePutReq.getPicture(), updatePutReq.getUserId());
		if(userService.userUpdateExpert(updatePutReq,url)) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		} else {
			return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
		}
	}

	@DeleteMapping("/delete")
	@ApiOperation(value = "사용자 삭제", notes = "현재 로그인 한 유저의 아이디를 통해 해당 유저의 정보를 삭제한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 400, message = "실패")
	})
	public ResponseEntity<? extends BaseResponseBody> deleteUser(
			@ApiParam(value="수정할 회원 정보") String userId) {
		if(userService.deleteUser(userId)) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		} else {
			return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
		}
	}

	@PostMapping("/pet/create")
	@ApiOperation(value = "반려동물 정보 추가", notes = "<strong>이름, 사진, 종, 나이, 정보, 무게, 성별, 사용자 아이디</strong>을 통해 반려동물 정보를 추가한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 400, message = "실패")
	})
	public ResponseEntity<? extends BaseResponseBody> petCreate(
			@ModelAttribute @ApiParam(value="반려동물 정보", required = true) AnimalReq animalReq) throws IOException {
		System.out.println(animalReq.getPicture().toString());
		String url=s3service.savePetProfile(animalReq.getPicture(), animalReq);
		if(animalService.animalCreate(animalReq,url)) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		} else {
			return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
		}
	}

	@GetMapping("/pet/data")
	@ApiOperation(value = "반려동물 정보 조회", notes = "사용자 아이디를 입력받아 해당 사용자의 반려동물 정보를 모두 출력한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 400, message = "실패")
	})
	public ResponseEntity<? extends BaseResponseBody> petData(
			@ApiParam(value="반려동물 소유 유저 아이디", required = true) String userId) {
		List<Animal> result = animalService.findAllAnimalByUserId(userId);
		if(result != null) {
			return ResponseEntity.status(200).body(AnimalListRes.of(200,"Success", result));
		} else {
			return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
		}
	}

	@GetMapping("/pet/detail")
	@ApiOperation(value = "반려동물 상세 정보", notes = "반려동물 고유 아이디를 통해 반려동물 상세 정보를 조회한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 400, message = "실패")
	})
	public ResponseEntity<? extends BaseResponseBody> petDetail(
			@ApiParam(value="반려동물 고유 아이디", required = true) int animalId) {
		Animal animal = animalService.findAnimalByAnimalId(animalId);
		if(animal != null) {
			return ResponseEntity.status(200).body(AnimalRes.of(200,"Success", animal));
		} else {
			return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
		}
	}

	@PutMapping("/pet/update")
	@ApiOperation(value = "반려동물 정보 수정", notes = "반려동물의 정보를 수정한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 400, message = "실패")
	})
	public ResponseEntity<? extends BaseResponseBody> petUpdate(
			@ApiParam(value="수정할 반려동물 정보") @RequestBody AnimalReq animalReq) throws IOException {
		String url=s3service.savePetProfile(animalReq.getPicture(),animalReq);
		if(animalService.animalUpdate(animalReq,url)) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		} else {
			return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
		}
	}

	@DeleteMapping("/pet/delete")
	@ApiOperation(value = "반려동물 정보 삭제", notes = "반려동물 고유 아이디를 통해 해당 반려동물의 정보를 삭제한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 400, message = "실패")
	})
	public ResponseEntity<? extends BaseResponseBody> petDelete(
			@ApiParam(value="삭제할 반려동물 고유 아이디") int animalId) {
		if(animalService.animalDelete(animalId)) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		} else {
			return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
		}
	}
}
