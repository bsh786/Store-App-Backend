package com.bashir.model;

import javax.validation.constraints.NotNull;

public class AddProductRequestModel {
	
	@NotNull(message="Product ID cannot be null")
	private String productId;
	
	@NotNull(message="Product name cannot be null")
	private String productName;
	
	@NotNull(message="Manufacturer cannot be null")
	private String manufacturer;
	
	@NotNull(message="Product price is mandatory")
	private int price;
	
	@NotNull(message="Product quantity is required")
	private int quantity;
	
	@NotNull(message="Product description is mandatory")
	private String productDescription;
	
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	
	

}
