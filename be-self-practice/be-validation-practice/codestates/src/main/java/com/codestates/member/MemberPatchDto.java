package com.codestates.member;

import com.codestates.NotSpace;

import javax.validation.constraints.Pattern;

public class MemberPatchDto {
    private long memberId; //Request body에 들어가는 데이터가 아니므로 유효성 검증이 필요 없습니다.
    @NotSpace(message ="회원 이름은 공백이 아니어야 합니다.")
    private String name;
    @NotSpace(message = "전화번호는 공백이 아니어야 합니다.")
    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$", message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다")
    private String phone;
    //여기서 사용된 DTO 클래스의 유효성 검증을 위해 사용된 애너테이션은 Jakarta Bean Validation이라는 ㄴ내장 애너테이션이다
    //즉 API가 아닌 스펙이다(기능 명세의 일종)
    //이 스펙을 구현한 구현체가 바로 Hibernate Validator


    private String email;

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
