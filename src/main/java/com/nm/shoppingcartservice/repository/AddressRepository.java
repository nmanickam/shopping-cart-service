package com.nm.shoppingcartservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.nm.shoppingcartservice.model.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {
}
