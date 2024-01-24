package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 댓글 삭제 API ([DELETE] /community/comment/delete) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("CommentDeleteDeleteRequest")
public class CommentDeleteDeleteReq {
    @ApiModelProperty(name="댓글 번호", example="1")
    int commentNo;
    @ApiModelProperty(name="글 번호", example="1")
    int postId;
}
