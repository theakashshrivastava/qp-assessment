package com.question.pro.grocery_app.service;

import com.question.pro.grocery_app.entity.GroceryItems;
import com.question.pro.grocery_app.repository.GroceryItemsRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroceryItemsService {

    @Autowired
    private GroceryItemsRepository groceryItemsRepository;

    public List<GroceryItems> getAllGroceryItems() {
        return groceryItemsRepository.findAll();
    }

    public void deleteGroceryItem(Long id) {
        groceryItemsRepository.deleteById(id);
    }

    public GroceryItems updateGroceryItem(Long id, GroceryItems groceryItems) {
        GroceryItems existingGroceryItem = groceryItemsRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Grocery Item not found"));
        existingGroceryItem.setName(groceryItems.getName());
        existingGroceryItem.setPrice(groceryItems.getPrice());
        return groceryItemsRepository.save(existingGroceryItem);
    }

    public void updateStock(Long id, int stockQuantity) {
        GroceryItems existingGroceryItem = groceryItemsRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Grocery Item not found"));
        existingGroceryItem.setStockQuantity(stockQuantity);
        groceryItemsRepository.save(existingGroceryItem);
    }

    public void addGroceryItem(GroceryItems groceryItems) {
        groceryItemsRepository.save(groceryItems);
    }
}
