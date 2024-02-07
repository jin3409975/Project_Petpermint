package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 댓글 작성 API ([POST] /community/comment/create) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("CommentCreatePostRequest")
public class CommentCreatePostReq {
    @ApiModelProperty(name="유저 ID", example="ssafy@ssafy.edu")
    String userId;
    @ApiModelProperty(name="내용", example="내용...")
    String content;
    @ApiModelProperty(name="글 번호", example="1")
    int postId;
}

