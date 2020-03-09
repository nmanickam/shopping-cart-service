package com.nm.shoppingcartservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.nm.shoppingcartservice.model.CartItem;
import com.nm.shoppingcartservice.model.CartItemId;

public interface CartItemRepository extends CrudRepository<CartItem, CartItemId> {
}
