package org.hdcd.service;

import org.hdcd.domain.ChargeCoin;
import org.hdcd.domain.PayCoinHistory;

import java.util.List;

public interface CoinService {
    void charge(ChargeCoin chargeCoin);

    List<ChargeCoin> list(Long userNo);

    public List<PayCoinHistory> listPayHistory(Long userNo);
}
