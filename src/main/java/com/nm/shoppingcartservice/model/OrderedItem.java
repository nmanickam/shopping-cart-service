package com.nm.shoppingcartservice.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderedItem implements Serializable {
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 3173131686532541739L;
  @Id
  private int id;
  private String customername;
  private String productname;
  private int quantity;

  public OrderedItem() {
  }

  public OrderedItem(String customername, String productname, int quantity) {
    this.customername = customername;
    this.productname = productname;
    this.quantity = quantity;
  }

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCustomername() {
		return customername;
	}
	
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	
	public String getProductname() {
		return productname;
	}
	
	public void setProductname(String productname) {
		this.productname = productname;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderedItem [id=" + id + ", customername=" + customername + ", productname=" + productname
				+ ", quantity=" + quantity + "]";
	}
 
}
