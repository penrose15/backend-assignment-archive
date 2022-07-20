package com.email.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class MemberPostDto {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;
}
