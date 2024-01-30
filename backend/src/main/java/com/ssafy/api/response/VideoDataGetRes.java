package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.UserPost;
import com.ssafy.db.entity.VideoRoom;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 방 조회 API ([GET] /video/data) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("VideoDataGetResponse")
public class VideoDataGetRes extends BaseResponseBody {
	@ApiModelProperty(name="Room ID")
	int roomId;
	String userId;
	String startTime;
	String roomName;
	String note;

	public static VideoDataGetRes of(Integer statusCode, String message, VideoRoom data) {
		VideoDataGetRes res = new VideoDataGetRes();
		res.setRoomId(data.getRoomId());
		res.setStatusCode(statusCode);
		res.setMessage(message);
		res.setUserId(data.getUserId());
		res.setStartTime(data.getStartTime());
		res.setRoomName(data.getRoomName());
		res.setNote(data.getNote());
		return res;
	}
}
