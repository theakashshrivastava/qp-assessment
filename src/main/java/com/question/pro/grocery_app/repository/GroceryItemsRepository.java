package com.question.pro.grocery_app.repository;

import com.question.pro.grocery_app.entity.GroceryItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroceryItemsRepository extends JpaRepository<GroceryItems, Long> {
    Optional<GroceryItems> findByName(String name);
}
