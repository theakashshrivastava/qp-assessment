package com.question.pro.grocery_app.controller;

import com.question.pro.grocery_app.entity.GroceryItems;
import com.question.pro.grocery_app.service.GroceryItemsService;
import org.aspectj.bridge.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private GroceryItemsService groceryItemsService;

    /**Tested**/
    @PostMapping("/grocery-items")
    @ResponseStatus(HttpStatus.CREATED)
    public String addGroceryItem(@RequestBody GroceryItems groceryItems){
        groceryItemsService.addGroceryItem(groceryItems);
        return "Grocery Item added successfully";
    }

    /**Tested**/
    @GetMapping("/grocery-items")
    @ResponseStatus(HttpStatus.OK)
    public List<GroceryItems> getGroceryItems(){
        MessageUtil log = null;
        log.info("Fetching all grocery items");
        return groceryItemsService.getAllGroceryItems();
    }

    /**Tested**/
    @DeleteMapping("/grocery-items/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteGroceryItem(@PathVariable Long id){
        groceryItemsService.deleteGroceryItem(id);
        return "Grocery Item deleted successfully";
    }

    /**Tested**/
    @PutMapping("/grocery-items/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateGroceryItem(@PathVariable Long id, @RequestBody GroceryItems groceryItems){
        groceryItemsService.updateGroceryItem(id, groceryItems);
        return "Grocery Item updated successfully";
    }

    /**Tested**/
    @PatchMapping("/grocery-items/{id}/stock")
    @ResponseStatus(HttpStatus.OK)
    public String updateStock(@PathVariable Long id, @RequestBody Map<String,Integer> stock){
        if (!stock.containsKey("stockQuantity")) {
            throw new IllegalArgumentException("Missing stockQuantity in request parameters.");
        }
        int stockQuantity = stock.get("stockQuantity");
        groceryItemsService.updateStock(id, stockQuantity);
        return "Stock updated successfully";
    }
}
