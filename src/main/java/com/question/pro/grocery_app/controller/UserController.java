package com.question.pro.grocery_app.controller;

import com.question.pro.grocery_app.dto.GroceryRequest;
import com.question.pro.grocery_app.entity.GroceryItems;
import com.question.pro.grocery_app.service.GroceryItemsService;
import com.question.pro.grocery_app.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasRole('USER')")
public class UserController {

    @Autowired
    private GroceryItemsService groceryItemsService;

    @Autowired
    private OrdersService ordersService;

    /**Tested**/
    @GetMapping("/grocery-items")
    @ResponseStatus(HttpStatus.OK)
    public List<GroceryItems> getGroceryItems(){
        return groceryItemsService.getAllGroceryItems();
    }

    /**Tested**/
    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestParam Long userId,@RequestBody List<GroceryRequest> groceryRequest){
        ordersService.placeOrder(userId,groceryRequest);
        return "Order placed successfully";
    }
}
