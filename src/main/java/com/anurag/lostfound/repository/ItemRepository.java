package com.anurag.lostfound.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.anurag.lostfound.entity.Item;


import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByItemNameContainingIgnoreCase(String itemName);

}