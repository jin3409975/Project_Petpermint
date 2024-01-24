package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 화상 채팅방 조회/삭제 API ([GET] /live/data m [DELETE] /live/delete) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("VideoCreatePostRequest")
public class VideoDataDeleteReq {
	@ApiModelProperty(name="방 ID", example="1")
	int roomId;
}
