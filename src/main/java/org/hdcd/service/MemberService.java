package org.hdcd.service;

import org.hdcd.domain.Member;
import org.hdcd.vo.PageRequestVO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MemberService {
    void register(Member member) throws Exception;

   public List<Member> Mlist()throws Exception;

    public Member read(Long userNo)throws Exception;

    void edit(Member member)throws Exception;

    void remove(Long userNo)throws Exception;

    public long countAll() throws Exception;

    void setupAdmin(Member member)throws Exception;

  //  Page<Member> list(PageRequestVO pageRequestVO);
}
