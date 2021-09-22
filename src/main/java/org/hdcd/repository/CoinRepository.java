package org.hdcd.repository;

import org.hdcd.domain.ChargeCoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CoinRepository extends JpaRepository<ChargeCoin,Long> {

    @Query("SELECT cc FROM ChargeCoin cc WHERE cc.userNo=?1")
    List<ChargeCoin> getList(Long userNo);
}
