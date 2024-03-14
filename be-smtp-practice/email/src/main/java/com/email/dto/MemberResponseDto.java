package com.email.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponseDto {

    private long memberId;

    private String name;

    private String email;
}
