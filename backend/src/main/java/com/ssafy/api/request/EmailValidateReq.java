package com.ssafy.api.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
@Getter
@Setter
@ToString
public class EmailValidateReq {
    @Email
    @NotEmpty(message = "이메일을 입력해 주세요")
    private String userId;
}
