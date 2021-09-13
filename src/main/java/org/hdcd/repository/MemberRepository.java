package org.hdcd.repository;

import org.hdcd.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("SELECT m.userNo,m.userId,m.userPwd,m.userName,cd.codeName, m.coin,m.regDate FROM Member m inner join CodeDetail cd On cd.codeValue=m.family inner join CodeGroup cg on cg.groupCode =cd.groupCode WHERE cg.groupCode='A02' order by m.regDate ASC")
    public List<Object[]> listAllMember();

    //public List<Member> findByUserId(String userId);
    @Query("select userId,userPwd,authList FROM Member WHERE userId=?1 ")
    Map<Object, Object> findbyUserId(String userName);
}
