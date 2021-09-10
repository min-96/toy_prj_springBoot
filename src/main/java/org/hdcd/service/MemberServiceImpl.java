package org.hdcd.service;

import lombok.RequiredArgsConstructor;
import org.hdcd.domain.Member;
import org.hdcd.domain.MemberAuth;
import org.hdcd.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void register(Member member) throws Exception {
  //      Member memberEntity = new Member();
//        memberEntity.getUserId()
        MemberAuth memberAuth = new MemberAuth();
        memberAuth.setAuth("ROLE_MEMBER");
        member.addAuth(memberAuth);

        memberRepository.save(member);
    }

    @Override
    public List<Member> list() throws Exception {
        List<Object[]> valueArrays = memberRepository.listAllMember();

        List<Member> memberList = new ArrayList<>();

        for(Object[] valueArray : valueArrays){
            Member member = new Member();
            member.setUserNo((Long)valueArray[0]);
            member.setUserId((String) valueArray[1]);
            member.setUserPwd((String) valueArray[2]);
            member.setUserName((String) valueArray[3]);
            member.setFamily((String) valueArray[4]);
            member.setCoin((int)valueArray[5]);
            member.setRegDate((LocalDateTime) valueArray[6]);

            memberList.add(member);
        }

        return memberList;
    }

    @Override
    public Member read(Long userNo) {
        return memberRepository.getOne(userNo);
    }


}
