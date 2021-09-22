package org.hdcd.repository;

import org.hdcd.domain.ChargeCoin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<ChargeCoin,Long> {

}
