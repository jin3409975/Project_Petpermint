package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 커뮤니티 글 조회 API ([GET] /community/data) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("CommunityDataGetRequest")
public class CommunityListPostReq {
    @ApiModelProperty(name="글 번호", example="1")
    int page;
    @ApiModelProperty(name="내용", example="내용...")
    List<Integer> postIds;
}
