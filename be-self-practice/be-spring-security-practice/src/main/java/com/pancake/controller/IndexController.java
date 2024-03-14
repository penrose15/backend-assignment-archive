package com.pancake.controller;

import com.pancake.model.Member;
import com.pancake.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/")
    public @ResponseBody String index() {
        return "index";
    }

    @GetMapping("/user")
    public @ResponseBody String user() {
        return "user";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin() {
        return "admin";
    }
    @GetMapping("/manager")
    public @ResponseBody String manager() {
        return "manager";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @PostMapping("/join")
    public @ResponseBody String join(Member member) {
        member.setRole("ROLE_USER");
        String rawPassword = member.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword);
        //password.encode()로 password 암호화
        member.setPassword(encPassword);

        memberRepository.save(member);
        //endcoding된 패스워드가 저장된다.

        return "redirect:/login";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/info")
    public @ResponseBody String info() {
        return "SecurityConfig에 .antMatchers('/info/**')"
                + ".access('hasRole('ROLE_ADMIN')')코드를 추가하는 것과 같은 동작을 한다,";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/data")
    public @ResponseBody String data() {
        return "SecurityConfig에 .antMatchers('/data/**')"
        + ".access('hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')')코드를 추가하는 것과 같은 동작을 한다,";
    }

    //preAuthorize 는 1개 이상의 권한을 주고 싶을 때 사용
    //postAuthrize메서드는 실행되고 응답하기 직전에 권한을 검사하는데 사용됨됨
}
