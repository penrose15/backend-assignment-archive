package com.codestates.member.dto;

import com.codestates.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
public class MemberPatchDto {
    private long memberId;
    @NotBlank
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String phone;

    private Member.MemberStatus memberStatus;

    public void setMemberId(long memberId) {this.memberId = memberId;}
}
