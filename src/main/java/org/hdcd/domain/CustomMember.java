package org.hdcd.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomMember extends Member{
    //객체를 저장하거나 불러올때 이 시리얼벌전UID사용
    private static final long serialVersionUID=1L;

    private Member member;
    // Collection<? extends GrantedAuthority> ->계정이 갖고있는 권한
    public CustomMember(String username, String password, Collection<? extends GrantedAuthority> authorities){
            super(username,password,authorities);
    }
    //사용자가 정의한 Member타입을 스프링 시큐리티로 userDetail타입으로 변환.
    //자바스트림이용
    public CustomMember(Member member){
        super(member.getUserId(),member.getUserPwd(),member.getAuthList().stream()
                .map(auth->new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList()));
        this.member = member;
    }

    public Member getMember(){
        return member;
    }
}
