package org.hdcd.service;

import lombok.RequiredArgsConstructor;
import org.hdcd.domain.ChargeCoin;
import org.hdcd.domain.Member;
import org.hdcd.domain.PayCoinHistory;
import org.hdcd.repository.CoinRepository;
import org.hdcd.repository.MemberRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoinServiceImpl implements CoinService{

    private final CoinRepository coinRepository;
    private final MemberRepository memberRepository;

    @Override
    public void charge(ChargeCoin chargeCoin) {
        Member memberEntitiy = memberRepository.getOne(chargeCoin.getUserNo());

        int coin = memberEntitiy.getCoin();
        int amount = chargeCoin.getAmount();

        memberEntitiy.setCoin(coin+amount);

        memberRepository.save(memberEntitiy);
        coinRepository.save(chargeCoin);
    }

    @Override
    public List<ChargeCoin> list(Long userNo) {


      //  Optional<ChargeCoin> coinOptional = coinRepository.getOne(userNo);
       return coinRepository.getList(userNo);
     //  return coinRepository.findById(userNo);
    }

    @Override
    public List<PayCoinHistory> listPayHistory(Long userNo) {
        List<Object[]> valueArrays = coinRepository.listPayHistory(userNo);

        List<PayCoinHistory> payCoinHistoryList = new ArrayList<>();
        for(Object[] valueArray : valueArrays){
            PayCoinHistory paycoin = new PayCoinHistory();
            paycoin.setHistoryNo((Long)valueArray[0]);
            paycoin.setUserNo((Long)valueArray[1]);
            paycoin.setItemId((Long)valueArray[2]);
            paycoin.setItemName((String)valueArray[3]);
            paycoin.setAmount((int)valueArray[4]);
            paycoin.setRegDate((LocalDateTime) valueArray[5]);
            payCoinHistoryList.add(paycoin);
        }

        return payCoinHistoryList;
    }
}
