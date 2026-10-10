package com.anurag.lostfound.controller;

import com.anurag.lostfound.entity.Item;
import com.anurag.lostfound.service.ItemService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@CrossOrigin(
        origins = {
                "http://localhost:5173",
                "http://localhost:5174"
        }
)
public class ItemController {

    private final ItemService itemService;


    public ItemController(ItemService itemService) {

        this.itemService = itemService;

    }


    // GET ALL ITEMS

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {

        return ResponseEntity.ok(
                itemService.getAllItems()
        );

    }


    // GET ITEM BY ID

    @GetMapping("/{id}")
    public ResponseEntity<?> getItem(
            @PathVariable Long id
    ) {

        try {

            return ResponseEntity.ok(
                    itemService.getItem(id)
            );

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());

        }

    }


    // SEARCH

    @GetMapping("/search")
    public ResponseEntity<List<Item>> searchItems(
            @RequestParam String name
    ) {

        return ResponseEntity.ok(
                itemService.searchItems(name)
        );

    }


    // ADD ITEM

    @PostMapping
    public ResponseEntity<Item> addItem(
            @RequestBody Item item
    ) {

        return ResponseEntity.ok(
                itemService.addItem(item)
        );

    }


    // UPDATE ITEM

    @PutMapping("/{id}")
    public ResponseEntity<?> updateItem(
            @PathVariable Long id,
            @RequestBody Item item
    ) {

        try {

            return ResponseEntity.ok(
                    itemService.updateItem(id, item)
            );

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());

        }

    }


    // DELETE ITEM

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(
            @PathVariable Long id
    ) {

        try {

            itemService.deleteItem(id);

            return ResponseEntity.ok(
                    "Item deleted successfully"
            );

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());

        }

    }

}