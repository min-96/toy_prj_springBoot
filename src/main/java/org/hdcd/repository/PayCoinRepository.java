package org.hdcd.repository;

import org.hdcd.domain.PayCoinHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PayCoinRepository extends JpaRepository<PayCoinHistory,Long> {


//    @Query("SELECT ")
//    public List<Object[]> listPayHistory(Long userNo);


}
