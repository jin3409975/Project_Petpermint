package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 커뮤니티 글 삭제 API ([DELETE] /community/delete) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("CommunityDeletePostRequest")
public class CommunityDeleteDeleteReq {
    @ApiModelProperty(name="글 번호", example="1")
    int postId;
}
