package org.hdcd.service;

import lombok.RequiredArgsConstructor;
import org.hdcd.repository.CoinRepository;
import org.hdcd.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoinServiceImpl implements CoinService{

    private final CoinRepository coinRepository;
    private final MemberRepository memberRepository;
}
