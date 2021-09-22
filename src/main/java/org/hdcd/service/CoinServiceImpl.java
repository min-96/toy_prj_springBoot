package org.hdcd.service;

import lombok.RequiredArgsConstructor;
import org.hdcd.domain.ChargeCoin;
import org.hdcd.domain.Member;
import org.hdcd.repository.CoinRepository;
import org.hdcd.repository.MemberRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
}
