package com.codestates.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.Map;

@Validated
@RestController //해당 클래스가 REST API의 리소스를 처리하기 위한 API엔드포인트로 동작함을 정의(+스프링 빈으로 등록)
@RequestMapping(value = "/v1/members") //클라이언트 요청과 클라이언트 요청을 처리하는 핸들러 메서드를 매핌해줌
//produces : 응답 데이터를 어떤 미디어 타입으로 클라이언트한테 전송할지 설정
public class MemberDTOController {

    @PostMapping// 클라이언트 요청 데이터(request body)를 서버에 생성할 때 사용하는 애너테이션
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDTO memberPostDTO) {

        return new ResponseEntity<>(memberPostDTO, HttpStatus.CREATED);
        //new 로 ResponseEntity객체를 생성하면서 생성자 파라미터로
        // 응답 데이터와 HTTP응답 상태를 전달하고 있음
        //사실 Map객체를 리턴해도 JSON 형식으로 응답을 받을 수 있으나
        //응답 상태를 함께 전달하기 위해 ResponseEntity로 감쌈

        //return response
        //클라이언트 쪽에서 JSON 형식의 데이터를 전달 받아야 되서 응답 문자열을 JSON형식으로 작성

    }
    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Min(2) long memberID,
                                       @Valid @RequestBody MemberPatchDto memberPatchDto) {
                                    //@RequestBody는 JSON 형식의 Request Body를 MemberPatchDto형식으로 바꾸어줌
        memberPatchDto.setMemberId(memberID);
        //memberPatchDto.setName("홍길동");

        return new ResponseEntity<>(memberPatchDto, HttpStatus.OK);
        //@ResponseBody는 JSON형식의 Response Body를 클라이언트에게 전달하기 위해 DTO클래스의 객체를 Response body로 변환
        //리턴값이 ResponseEntity일 경우 내부적으로 HttpMessageConverter가 동작하게 되어 응답 객체를 JSON형식으로 바꿔줌
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id")long memberId) {
        //@PathVariable 괄호 안에 입력된 문자열 값은 GetMapping괄호 안의 문자열과 동일해야함
        //안그러면 MissingPathVariableException 발생

        //not implement
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers() {
        System.out.println("# get Member");
        //not implement
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") long memberID) {
        return new ResponseEntity(HttpStatus.OK);
    }

}
