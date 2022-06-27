package com.codestates.member;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor//기본 생성자
@AllArgsConstructor//클래스의 모든 멤버 변수를 파라미터로 갖는 생성자
public class Member {
    //서비스 계층에서 데이터 액서스 계층과 연동하면서 비즈니스 로직을
    //처리하기 위해 필요한 데이터를 담는 역할을 하는 클래스를
    //도메인 엔티티 클래스라고 한다
    private long memberId;
    private String email;
    private String name;
    private String phone;
}
