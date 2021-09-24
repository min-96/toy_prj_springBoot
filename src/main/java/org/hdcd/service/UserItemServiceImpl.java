package org.hdcd.service;


import lombok.RequiredArgsConstructor;
import org.hdcd.domain.Item;
import org.hdcd.domain.Member;
import org.hdcd.domain.PayCoinHistory;
import org.hdcd.domain.UserItem;
import org.hdcd.repository.MemberRepository;
import org.hdcd.repository.PayCoinRepository;
import org.hdcd.repository.UserItemRepository;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;

@Service
@RequiredArgsConstructor
public class UserItemServiceImpl implements UserItemService{


    private final UserItemRepository userItemRepository;

    private final PayCoinRepository payCoinRepository;

    private final MemberRepository memberRepository;

    @Transient
    @Override
    public void register(Member member, Item item) {

        Long userNo = member.getUserNo();
        Long itemId = item.getItemId();
        int price = item.getPrice();

        UserItem userItem = new UserItem();
        userItem.setUserNo(userNo);
        userItem.setItemId(itemId);
        userItem.setPrice(price);


        PayCoinHistory payCoin = new PayCoinHistory();
        payCoin.setUserNo(userNo);
        payCoin.setItemId(itemId);
        payCoin.setAmount(price);

        Member memberEntity = memberRepository.getOne(userNo);

        int coin = memberEntity.getCoin();

        int amount = payCoin.getAmount();

        memberEntity.setCoin(coin-amount);
        memberRepository.save(memberEntity);
        payCoinRepository.save(payCoin);
        userItemRepository.save(userItem);

    }
}
