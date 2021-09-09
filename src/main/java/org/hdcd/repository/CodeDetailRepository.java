package org.hdcd.repository;

import org.hdcd.domain.CodeDetail;
import org.hdcd.domain.CodeDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CodeDetailRepository extends JpaRepository<CodeDetail, CodeDetailId> {
        //테이블에 존재하는 최댓값 가져오기.
    @Query("SELECT max(cd.sortSeq) FROM CodeDetail cd WHERE cd.groupCode=?1")
    public List<Object[]> getMaxSortSeq(String groupCode);

//    @Query("")
//    List<CodeDetail> findAllById(String classCode);
    @Query("select cd from CodeDetail cd WHERE cd.groupCode=?1")
    public List<CodeDetail> findById(String classCode);
}
