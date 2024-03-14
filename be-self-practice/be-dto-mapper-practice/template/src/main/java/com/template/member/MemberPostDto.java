package com.template.member;

import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Valid
public class MemberPostDto {

    @NotBlank
    private String name;
    @Email(message = "이메일 형식으로 작성해주세요")
    private String email;
}
