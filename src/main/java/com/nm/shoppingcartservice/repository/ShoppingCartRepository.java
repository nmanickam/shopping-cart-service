package com.nm.shoppingcartservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.nm.shoppingcartservice.model.ShoppingCart;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Integer> {
}
