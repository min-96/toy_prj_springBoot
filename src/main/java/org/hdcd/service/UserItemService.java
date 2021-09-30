package org.hdcd.service;

import org.hdcd.domain.Item;
import org.hdcd.domain.Member;
import org.hdcd.domain.UserItem;

import java.util.List;

public interface UserItemService {
    void register(Member member, Item item);

    public List<UserItem> list(Long userNo)throws  Exception;

    public UserItem read(Long userItemNo) throws Exception;
}
