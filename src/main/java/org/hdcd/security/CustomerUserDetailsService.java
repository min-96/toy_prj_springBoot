package org.hdcd.security;

import lombok.extern.slf4j.Slf4j;
import org.hdcd.domain.CustomMember;
import org.hdcd.domain.Member;
import org.hdcd.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
@Slf4j
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    MemberRepository repository;
    //사용자 정의 유저 상세 클래스 메서드
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
       //사용자 아이디
       log.info("userName: "+userName);

        Member member = (Member) repository.findbyUserId(userName).get(0);
        log.info("member : " +member);

        return member == null?null: (UserDetails) new CustomMember(member);

    }
}
