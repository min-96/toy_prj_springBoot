package org.hdcd.repository;

import org.hdcd.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("SELECT m.userNo,m.userId,m.userPwd,m.userName,cd.codeName, m.coin,m.regDate FROM Member m inner join CodeDetail cd On cd.codeValue=m.job inner join CodeGroup cg on cg.groupCode =cd.groupCode WHERE cg.groupCode='A01' order by m.regDate DESC ")
    public List<Object[]> listAllMember();
}
