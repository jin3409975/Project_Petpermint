package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 댓글 조회 API ([GET] /community/comment/data) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("CommentDataGetRequest")
public class VenueDataGetReq {
    @ApiModelProperty(name="카테고리", example="동물약국")
    String[] category;
    @ApiModelProperty(name="시도", example="경기도")
    String sido;
    @ApiModelProperty(name="군구", example="영등포구")
    String gungu;
    @ApiModelProperty(name="면동", example="동산동")
    String myundong;
    @ApiModelProperty(name="편의시설 이름", example="1004 약국")
    String venName;

}
