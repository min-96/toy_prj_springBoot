package org.hdcd.service;

import org.hdcd.domain.Item;

import java.util.List;

public interface ItemService {
    void register(Item item);

   public List<Item> list();


    public Item read(Long itemId)throws Exception;
    //미리보기 이미지 표시
    String getPreview(Long itemId) throws Exception;
}
