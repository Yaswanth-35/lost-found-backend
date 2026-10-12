
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
                        new RuntimeException("Item not found with ID: " + id));
    }

    // Search items by name
    public List<Item> searchItems(String name) {
        return itemRepository.findByItemNameContainingIgnoreCase(name);
    }

    // Add a new item
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    // Update an existing item
    public Item updateItem(Long id, Item updatedItem) {
        Item existingItem = itemRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Item not found with ID: " + id));

        existingItem.setItemName(updatedItem.getItemName());
        existingItem.setDescription(updatedItem.getDescription());
        existingItem.setLocation(updatedItem.getLocation());
        existingItem.setDate(updatedItem.getDate());
        existingItem.setType(updatedItem.getType());
        existingItem.setContact(updatedItem.getContact());

        return itemRepository.save(existingItem);
    }

    // Delete an item
    public void deleteItem(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new RuntimeException("Item not found with ID: " + id);
        }

        itemRepository.deleteById(id);
    }
}
