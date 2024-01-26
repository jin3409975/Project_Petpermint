package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 유저 회원가입 API ([POST] /api/v1/users) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ToString
@ApiModel("AnimalPostRequest")
public class AnimalReq {
	@ApiModelProperty(name="반려동물 고유 ID", example="123")
	int animalId;
	@ApiModelProperty(name="반려동물 이름", example="나비")
	String name;
	@ApiModelProperty(name="반려동물 사진", example="image_url")
	String picture;
	@ApiModelProperty(name="반려동물 종", example="고양이")
	String specie;
	@ApiModelProperty(name="반려동물 나이", example="1살 or 12개월")
	String age;
	@ApiModelProperty(name="반려동물 정보", example="밥을 잘 먹어요")
	String note;
	@ApiModelProperty(name="반려동물 무게", example="3.5kg")
	String weight;
	@ApiModelProperty(name="반려동물 성별", example="암컷 or 수컷")
	String gender;
	@ApiModelProperty(name="반려동물 소유 유저 아이디", example="ssafy@gmail.com")
	String userId;

}
