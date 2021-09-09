package org.hdcd.service;

import lombok.RequiredArgsConstructor;
import org.hdcd.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
}
