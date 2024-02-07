package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 조회,좋아요 증가 API ([PUT] /community/hit, /community/like) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("CommunityLikeHitPutRequest")
public class CommunityLikeHitPutReq {
    @ApiModelProperty(name = "글 번호", example = "1")
    int postId;
    String userId;
}
