package com.template.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Valid
public class MemberPatchDto {

    @NotBlank
    @Pattern(regexp = "/^[ㄱ-ㅎ|가-힣]+$/", message = "공백 없이 한글이름으로 작성해주세요")
    private String name;
    @Email(message = "이메일 형식으로 작성해주세요")
    private String email;
}
