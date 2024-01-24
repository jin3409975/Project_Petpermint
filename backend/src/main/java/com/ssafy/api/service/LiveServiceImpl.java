package com.ssafy.api.service;

import com.ssafy.api.request.*;
import com.ssafy.db.entity.VideoRoom;

import com.ssafy.db.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("liveService")
public class LiveServiceImpl implements LiveService {
    @Autowired
    VideoRepository videoRepository;

    @Override
    public VideoRoom createRoom(VideoCreatePostReq createInfo) {
        return null;
    }

    @Override
    public VideoRoom dataRoom(int roomId) {
        return null;
    }

    @Override
    public Long deleteRoom(int roomId) {
        return null;
    }
}
