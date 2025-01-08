package com.question.pro.grocery_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grocery_item_id")
    private GroceryItems groceryItems;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false,precision = 10, scale = 2)
    private BigDecimal price_per_item;

    private BigDecimal totalPrice;

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public GroceryItems getGroceryItems() {
        return groceryItems;
    }

    public void setGroceryItems(GroceryItems groceryItems) {
        this.groceryItems = groceryItems;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice_per_item() {
        return price_per_item;
    }

    public void setPrice_per_item(BigDecimal price_per_item) {
        this.price_per_item = price_per_item;
    }
}
