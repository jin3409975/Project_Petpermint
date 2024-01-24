package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 화상 채팅방 생성 API ([POST] /live/create) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("VideoCreatePostRequest")
public class VideoCreatePostReq {
	@ApiModelProperty(name="이메일 형식 유저 ID", example="ssafy_web@gmail.com")
	String userId;
	@ApiModelProperty(name="시작 시간", example="2024-01-08 11:30:00")
	String startTime;
	@ApiModelProperty(name="방 이름", example="our video room")
	String roomName;
}
