/**
 * 
 */
package com.nm.shoppingcartservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nm.shoppingcartservice.ShoppingCartServiceApplication;
import com.nm.shoppingcartservice.example.ShoppingItemSelectService;
import com.nm.shoppingcartservice.message.ItemSelectMessage;
import com.nm.shoppingcartservice.model.CartItem;
import com.nm.shoppingcartservice.model.OrderedItem;
/**
 * @author N.Manickam
 *
 */
@RestController
public class ShoppingRestController {

	private static Logger LOG = LoggerFactory.getLogger(ShoppingCartServiceApplication.class);

    @Autowired
    ShoppingItemSelectService shoppingItemSelectService;
	  
    // Save
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/additems") // @PostMapping("/additems") - changed for testing from browser
    String addItems() {
    	shoppingItemSelectService.simulateBatchItemSelect();
    	return "Added";
    }
    
    // Save
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/additem")
    String addItem(@RequestParam String customerid,@RequestParam String productId,@RequestParam String count) {
    	
    	int customerID = (customerid!=null)?Integer.parseInt(customerid):0;
    	int productID = (productId!=null)?Integer.parseInt(productId):0;
    	int Count = (count!=null)?Integer.parseInt(count):0;
    	ItemSelectMessage selectedItem = new ItemSelectMessage(1, customerID, productID, Count);
    	shoppingItemSelectService.addSelectedItem(selectedItem);
    	return "Added";
    }
    
   
    @GetMapping("/getaddeditems")
    List<OrderedItem> getAddedItems() {
    	return shoppingItemSelectService.getSelectedItem();
    }
}
