package org.hdcd.repository;

import org.hdcd.domain.ChargeCoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CoinRepository extends JpaRepository<ChargeCoin,Long> {

    @Query("SELECT cc FROM ChargeCoin cc WHERE cc.userNo=?1")
    List<ChargeCoin> getList(Long userNo);

    @Query("select a.historyNo, a.userNo, a.itemId,b.itemName,a.amount,a.regDate from PayCoinHistory a inner join Item b on a.itemId=b.itemId where a.historyNo>0 and a.userNo=?1 order by a.historyNo desc")
    List<Object[]> listPayHistory(Long userNo);
}
