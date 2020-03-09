package com.nm.shoppingcartservice.example;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import com.nm.shoppingcartservice.configuration.MessageConfig;
import com.nm.shoppingcartservice.message.ItemSelectMessage;
import com.nm.shoppingcartservice.model.Customer;
import com.nm.shoppingcartservice.model.OrderedItem;
import com.nm.shoppingcartservice.model.Product;
import com.nm.shoppingcartservice.repository.CartItemRepository;
import com.nm.shoppingcartservice.repository.CustomerRepository;
import com.nm.shoppingcartservice.repository.ProductRepository;

/**
 * @author N.Manickam
 *
 */
@Service
public class ShoppingItemSelectService {
  private static Logger log = LoggerFactory.getLogger(ShoppingItemSelectService.class);

  @Autowired
  ProductRepository productRepository;
  @Autowired
  CustomerRepository customerRepository;
  @Autowired
  JmsMessagingTemplate jmsMessagingTemplate;
  @Autowired
  CartItemRepository cartItemRepository;
  
  @PersistenceContext
  private EntityManager em;
  
  public void simulateBatchItemSelect() {
    Iterable<Product> products = productRepository.findAll();
    List<Product> productList = new ArrayList<>();
    products.forEach(product -> productList.add(product));

    Iterable<Customer> customers = customerRepository.findAllByOrderByIdAsc();
    List<Customer> customerList = new ArrayList<>();
    customers.forEach(customer -> {
      customerList.add(customer);
    });

    publishSelectedCartItem(new ItemSelectMessage(1, customerList.get(0).getId(), productList.get(0).getId(), 2));
    publishSelectedCartItem(new ItemSelectMessage(1, customerList.get(0).getId(), productList.get(0).getId(), -1));//
    publishSelectedCartItem(new ItemSelectMessage(1, customerList.get(0).getId(), productList.get(1).getId(), 1));
    publishSelectedCartItem(new ItemSelectMessage(2, customerList.get(1).getId(), productList.get(2).getId(), 3));
    publishSelectedCartItem(new ItemSelectMessage(1, customerList.get(0).getId(), productList.get(2).getId(), 1));
    publishSelectedCartItem(new ItemSelectMessage(3, customerList.get(2).getId(), productList.get(0).getId(), 1));
    publishSelectedCartItem(new ItemSelectMessage(3, customerList.get(2).getId(), productList.get(1).getId(), 5));
    publishSelectedCartItem(new ItemSelectMessage(3, customerList.get(2).getId(), productList.get(2).getId(), -1));//
    publishSelectedCartItem(new ItemSelectMessage(3, customerList.get(2).getId(), productList.get(3).getId(), 2));
    publishSelectedCartItem(new ItemSelectMessage(3, customerList.get(2).getId(), productList.get(3).getId(), -3));//
  }

  public void addSelectedItem(ItemSelectMessage selectedItem) {
	    publishSelectedCartItem(selectedItem);
  }
  public void publishSelectedCartItem(ItemSelectMessage message) {
    log.info("--SCS--[Sent]: " + message);
    jmsMessagingTemplate.convertAndSend(MessageConfig.CART_ITEM_SELECT_QUEUE, message);
  }
  
  public List<OrderedItem> getSelectedItem() {
	  String query = "SELECT ROWNUM() AS ID, t.* FROM ( "
	  		+ " SELECT CUST.NAME as customername,PROD.DESCRIPTION as productname, CITEM.QUANTITY as quantity  FROM CUSTOMER  CUST "
	  		+ " LEFT OUTER JOIN SHOPPING_CART SCART ON CUST.ID=SCART.CUSTOMER_ID "
	  		+ " JOIN CART_ITEM CITEM ON CITEM.SHOPPING_CART_ID=SCART.ID "
	  		+ " JOIN PRODUCT PROD on PROD.ID= CITEM.PRODUCT_ID GROUP BY CUST.NAME , CITEM.PRODUCT_ID"
	  		+ ") t ";
	  List<OrderedItem> orderedItems = em.createNativeQuery(query, OrderedItem.class).getResultList();
	    
	    return orderedItems;
  }
}
