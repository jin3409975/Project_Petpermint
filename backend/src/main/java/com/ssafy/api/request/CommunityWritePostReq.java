package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 커뮤니티 글 작성 API ([POST] /community/write) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("CommunityWritePostRequest")
public class CommunityWritePostReq {
    @ApiModelProperty(name="유저 ID", example="ssafy@ssafy.edu")
    String userId;
    @ApiModelProperty(name="내용", example="내용...")
    String content;
    @ApiModelProperty(name="이미지", example="image.url")
    List<MultipartFile> images;
}
