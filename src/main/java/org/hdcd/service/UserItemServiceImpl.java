package org.hdcd.service;


import lombok.RequiredArgsConstructor;
import org.hdcd.domain.Item;
import org.hdcd.domain.Member;
import org.hdcd.domain.PayCoinHistory;
import org.hdcd.domain.UserItem;
import org.hdcd.exception.NotEnoughCoinException;
import org.hdcd.repository.MemberRepository;
import org.hdcd.repository.PayCoinRepository;
import org.hdcd.repository.UserItemRepository;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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


        if(coin<price){
            throw new NotEnoughCoinException("there is Not Enough Coin");
        }

        memberEntity.setCoin(coin-amount);
        memberRepository.save(memberEntity);
        payCoinRepository.save(payCoin);
        userItemRepository.save(userItem);

    }

    @Override
    public List<UserItem> list(Long userNo) throws Exception {

        List<Object[]> valueArrays = userItemRepository.listUserItem(userNo);

        List<UserItem> userItemList = new ArrayList<>();
        for(Object[]valueArray : valueArrays){
            UserItem userItem = new UserItem();

            userItem.setUserItemNo((Long)valueArray[0]);
            userItem.setUserNo((Long)valueArray[1]);
            userItem.setItemId((Long)valueArray[2]);
            userItem.setRegDate((LocalDateTime) valueArray[3]);
            userItem.setItemName((String)valueArray[4]);
            userItem.setPrice((int)valueArray[5]);
            userItem.setDescription((String)valueArray[6]);
            userItem.setPictureUrl((String)valueArray[7]);

            userItemList.add(userItem);


        }
        return userItemList;
    }

    @Override
    public UserItem read(Long userItemNo) throws Exception {
        List<Object[]> valueArrays = userItemRepository.readUserItem(userItemNo);

        Object[] valueArray = valueArrays.get(0);
        UserItem userItem = new UserItem();
        userItem.setUserItemNo((Long)valueArray[0]);
        userItem.setUserNo((Long)valueArray[1]);
        userItem.setItemId((Long)valueArray[2]);
        userItem.setRegDate((LocalDateTime)valueArray[3]);
        userItem.setItemName((String)valueArray[4]);
        userItem.setPrice((int)valueArray[5]);
        userItem.setDescription((String)valueArray[6]);
        userItem.setPictureUrl((String)valueArray[7]);

        return userItem;
    }
}
