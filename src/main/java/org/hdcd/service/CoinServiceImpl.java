package org.hdcd.service;

import lombok.RequiredArgsConstructor;
import org.hdcd.domain.ChargeCoin;
import org.hdcd.domain.Member;
import org.hdcd.repository.CoinRepository;
import org.hdcd.repository.MemberRepository;
import org.springframework.stereotype.Service;

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
}
