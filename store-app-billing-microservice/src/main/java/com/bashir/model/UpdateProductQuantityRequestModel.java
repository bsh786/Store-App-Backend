package com.bashir.model;

public class UpdateProductQuantityRequestModel {
	
	private String productId;
	private Integer boughtQuantity;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Integer getBoughtQuantity() {
		return boughtQuantity;
	}
	public void setBoughtQuantity(Integer boughtQuantity) {
		this.boughtQuantity = boughtQuantity;
	}
	
	

}
