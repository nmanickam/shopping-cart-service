package com.nm.shoppingcartservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nm.shoppingcartservice.example.ShoppingItemSelectService;

/**
 * @author N.Manickam
 *
 */
@SpringBootApplication
public class ShoppingCartServiceApplication {
  private static Logger log = LoggerFactory.getLogger(ShoppingCartServiceApplication.class);

  @Autowired
  ShoppingItemSelectService shoppingItemSelectService;

  public static void main(String[] args) {
    SpringApplication.run(ShoppingCartServiceApplication.class, args);
  }

}
