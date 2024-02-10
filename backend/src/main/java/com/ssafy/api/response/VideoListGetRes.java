package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.VideoRoom;
import com.ssafy.db.join.PostUrlList;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 비디오룸 목록 조회 API ([GET] /community/video/list) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("VideoListGetResponse")
public class VideoListGetRes extends BaseResponseBody{
	List<VideoRoom>videoList;

	public static VideoListGetRes of(Integer statusCode, String message, List<VideoRoom> videoList) {
		VideoListGetRes res = new VideoListGetRes();
		res.videoList=videoList;
		res.setStatusCode(statusCode);
		res.setMessage(message);
		return res;
	}
}
