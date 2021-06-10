package com.bashir.model;

import java.util.Map;

import javax.validation.constraints.NotNull;



public class NewBillRequestModel {

	@NotNull(message="Please enter atleast one product ID and quantity")
	private Map<String, Integer> productIdAndQuantity;
	private int totalAmount;
	
	private String salesmanId;
	
    public String getSalesmanId() {
		return salesmanId;
	}
	public void setSalesmanId(String salesmanId) {
		this.salesmanId = salesmanId;
	}
	public Map<String, Integer> getProductIdAndQuantity() {
		return productIdAndQuantity;
	}
	public void setProductIdAndQuantity(Map<String, Integer> productIdAndQuantity) {
		this.productIdAndQuantity = productIdAndQuantity;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	
	
	

}
