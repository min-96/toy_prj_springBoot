package org.hdcd.service;

import org.hdcd.domain.Member;

import java.util.List;

public interface MemberService {
    void register(Member member) throws Exception;

   public List<Member> list()throws Exception;

    public Member read(Long userNo)throws Exception;

    void edit(Member member)throws Exception;
}
