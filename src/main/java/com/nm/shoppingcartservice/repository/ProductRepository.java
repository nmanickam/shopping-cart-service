package com.nm.shoppingcartservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.nm.shoppingcartservice.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
