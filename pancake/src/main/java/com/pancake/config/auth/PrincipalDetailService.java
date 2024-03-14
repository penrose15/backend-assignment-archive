package com.pancake.config.auth;

import com.pancake.model.Member;
import com.pancake.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {
    //UserDetails는 사용자의 정보를 담을 인터페이스(UserDetails를 구현했으니 사용자의 정보를 담을 객채)
    //UserDetailsService는 DB에서 유저 정보를 직접 가져오는 인터페이스 구현

    @Autowired
    private MemberRepository memberRepository;

    //"/login"으로 요청이 오면 자동으로 UserDetailService타입으로 IOC된 loadUserByname()이 실행된다.

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //메서드 파라미터에 username이라 되어 있을 때 form을 통해 username을 가져올 때 name이랑 매치 되어야 함

        Member member = memberRepository.findByUser(username);
        System.out.println("username : "+ username);
        if(member != null) {
            return new PrincipalDetails(member); //만약 null이 아니면 PrincipalDefails를 이용해
            // Member객체를 Authentication타입으로 리턴해줌
        }
        return null;
    }
}
