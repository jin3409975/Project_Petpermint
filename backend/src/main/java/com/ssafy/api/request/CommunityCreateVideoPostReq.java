package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 댓글 작성 API ([POST] /community/comment/create) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("CommentCreatePostRequest")
public class CommunityCreateVideoPostReq {
    @ApiModelProperty(name="유저 ID", example="ssafy@ssafy.edu")
    String userId;
    @ApiModelProperty(name="내용", example="내용...")
    String note;
    @ApiModelProperty(name="시작 시간", example="2024-10-11 10:00:00")
    String startTIme;
    @ApiModelProperty(name="방 제목", example="제목")
    String roomName;
}

