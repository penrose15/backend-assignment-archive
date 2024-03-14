package com.codestates.member.dto;

import com.codestates.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class MemberResponseDto {
    private long memberId;
    private String email;
    private String name;
    private String phone;
    private Member.MemberStatus memberStatus;

    public String getMemberStatus() {
        return memberStatus.getDescription();
    }
}
