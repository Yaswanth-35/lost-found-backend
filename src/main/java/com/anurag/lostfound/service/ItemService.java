package com.anurag.lostfound.service;

import com.anurag.lostfound.entity.Item;
import com.anurag.lostfound.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;


    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    // Get all items
    public List<Item> getAllItems() {

        return itemRepository.findAll();

    }


    // Get item by ID
    public Item getItem(Long id) {

        return itemRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Item not found")
                );

    }


    // Search items
    public List<Item> searchItems(String name) {

        return itemRepository
                .findByItemNameContainingIgnoreCase(name);

    }


    // Add item
    public Item addItem(Item item) {

        return itemRepository.save(item);

    }


    // Update item
    public Item updateItem(Long id, Item item) {

        Item existingItem = itemRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Item not found")
                );


        existingItem.setItemName(
                item.getItemName()
        );

        existingItem.setDescription(
                item.getDescription()
        );

        existingItem.setLocation(
                item.getLocation()
        );

        existingItem.setDate(
                item.getDate()
        );

        existingItem.setType(
                item.getType()
        );

        existingItem.setContact(
                item.getContact()
        );


        return itemRepository.save(existingItem);

    }


    // Delete item
    public void deleteItem(Long id) {

        if (!itemRepository.existsById(id)) {

            throw new RuntimeException(
                    "Item not found"
            );

        }

        itemRepository.deleteById(id);

    }

}