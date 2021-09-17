package org.hdcd.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hdcd.domain.Item;
import org.hdcd.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    @Override
    public void register(Item item) {
        itemRepository.save(item);
    }
}
