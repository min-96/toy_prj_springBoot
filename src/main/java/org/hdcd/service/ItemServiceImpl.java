package org.hdcd.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hdcd.domain.Item;
import org.hdcd.repository.ItemRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    @Override
    public void register(Item item) {
        itemRepository.save(item);
    }

    @Override
    public List<Item> list() {
        return itemRepository.findAll(Sort.by(Sort.Direction.DESC,"itemId"));
    }

    @Override
    public Item read(Long itemId) throws Exception {
        return itemRepository.getOne(itemId);
    }

    @Override
    public String getPreview(Long itemId) {
        Item item = itemRepository.getOne(itemId);

        return item.getPreviewUrl();
    }
}
