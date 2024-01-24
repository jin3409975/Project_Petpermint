package com.ssafy.api.service;

import com.ssafy.api.request.VideoCreatePostReq;
import com.ssafy.api.request.VideoDataDeleteReq;
import com.ssafy.db.entity.VideoRoom;

/**
 *	화상 채팅 방 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface LiveService {


    VideoRoom createRoom(VideoCreatePostReq createInfo);
    VideoRoom dataRoom(int roomId);
    Long deleteRoom(int roomId);
}

