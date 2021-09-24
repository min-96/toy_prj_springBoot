package org.hdcd.service;

import org.hdcd.domain.Item;
import org.hdcd.domain.Member;

public interface UserItemService {
    void register(Member member, Item item);
}
