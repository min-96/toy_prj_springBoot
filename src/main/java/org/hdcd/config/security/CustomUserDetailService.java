package org.hdcd.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hdcd.domain.CustomUser;
import org.hdcd.domain.Member;
import org.hdcd.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Slf4j
public class CustomUserDetailService implements UserDetailsService {



   @Autowired
   MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Load User By UserName : "+username);
        Member member = memberRepository.findByUserId(username).get(0);

        log.info("member :" +member);


        return member == null?null : new CustomUser(member);
    }
}
