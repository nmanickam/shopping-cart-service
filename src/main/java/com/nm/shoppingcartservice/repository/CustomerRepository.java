package com.nm.shoppingcartservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nm.shoppingcartservice.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
  Iterable<Customer> findAllByOrderByIdAsc();
}
