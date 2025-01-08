package com.question.pro.grocery_app.service;

import com.question.pro.grocery_app.dto.GroceryRequest;
import com.question.pro.grocery_app.entity.GroceryItems;
import com.question.pro.grocery_app.entity.OrderItems;
import com.question.pro.grocery_app.entity.Orders;
import com.question.pro.grocery_app.repository.GroceryItemsRepository;
import com.question.pro.grocery_app.repository.OrdersRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private GroceryItemsRepository groceryItemsRepository;

    public void placeOrder(Long userId, List<GroceryRequest> groceryRequests) {
        Orders order = new Orders();
        order.setOrderDate(LocalDateTime.now());
        /**Set the user id for the order**/
        order.setUserId(userId);

        List<OrderItems> orderItemsList = new ArrayList<>();
        /**Initialize the total order price to 0**/
        BigDecimal totalOrderPrice = BigDecimal.ZERO;

        for (GroceryRequest requestedItem : groceryRequests) {
            GroceryItems isItemInInventory = groceryItemsRepository.findByName(requestedItem.getName())
                    .orElseThrow(() -> new RuntimeException("Grocery Item not found: " + requestedItem.getName()));

            if (isItemInInventory.getStockQuantity() < requestedItem.getStockQuantity()) {
                throw new RuntimeException(
                        "Requested quantity is greater than the available quantity." +
                                " We only have this much left in our stock for "
                                + isItemInInventory.getName()
                                + ":" + isItemInInventory.getStockQuantity());
            }

            /**Update stock quantity**/
            isItemInInventory.setStockQuantity(isItemInInventory.getStockQuantity() - requestedItem.getStockQuantity());
            groceryItemsRepository.save(isItemInInventory);

            /**Calculate item total price**/
            BigDecimal itemTotalPrice = isItemInInventory.getPrice().multiply(BigDecimal.valueOf(requestedItem.getStockQuantity()));

            /**Create a new OrderItems object for each grocery item**/
            OrderItems orderItem = new OrderItems();
            orderItem.setOrder(order);
            orderItem.setGroceryItems(isItemInInventory);
            orderItem.setQuantity(requestedItem.getStockQuantity());
            orderItem.setPrice_per_item(isItemInInventory.getPrice());
            orderItem.setTotalPrice(itemTotalPrice);

            /**Add the order item to the list**/
            orderItemsList.add(orderItem);

            /**Add the item total price to the overall order price**/
            totalOrderPrice = totalOrderPrice.add(itemTotalPrice);
        }

        /**Set the order items list in the order**/
        order.setOrderItems(orderItemsList);

        /**Set the total price for the order**/
        order.setTotalPrice(totalOrderPrice);

        /**Save the order to the database**/
        ordersRepository.save(order);
    }
}


