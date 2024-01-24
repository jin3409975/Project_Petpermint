package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Animal;
import com.ssafy.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 회원 본인 정보 조회 API ([GET] /api/v1/users/me) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("AnimalResponse")
public class AnimalRes extends BaseResponseBody{
	@ApiModelProperty(name="Animal ID")
	int animalId;
	String name;
	String picture;
	String specie;
	String age;
	String note;
	String weight;
	String gender;

	public static AnimalRes of(Integer statusCode, String message, Animal animal) {
		AnimalRes res = new AnimalRes();
		res.setMessage(message);
		res.setStatusCode(statusCode);

		res.setAnimalId(animal.getAnimalId());
		res.setPicture(animal.getPicture());
		res.setNote(animal.getNote());
		res.setSpecie(animal.getSpecie());
		res.setAge(animal.getAge());
		res.setName(animal.getName());
		res.setWeight(animal.getWeight());
		res.setGender(animal.getGender());
		return res;
	}

}
