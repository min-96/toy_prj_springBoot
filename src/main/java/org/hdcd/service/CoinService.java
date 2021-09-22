package org.hdcd.service;

import org.hdcd.domain.ChargeCoin;

import java.util.List;

public interface CoinService {
    void charge(ChargeCoin chargeCoin);

    List<ChargeCoin> list(Long userNo);
}
