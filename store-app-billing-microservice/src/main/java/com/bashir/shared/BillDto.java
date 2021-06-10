package com.bashir.shared;

import java.util.List;
import java.util.Map;

import com.bashir.entity.BillItemModel;
import com.bashir.model.CreateEmployeeResponseModel;

public class BillDto {

	private Long billId;
	private Map<String, Integer> productIdAndQuantity;
	private List<BillItemModel> itemList;
	private int totalAmount;
	private String salesmanId;
	private CreateEmployeeResponseModel salesman;
	
	
	public Long getBillId() {
		return billId;
	}
	public void setBillId(Long billId) {
		this.billId = billId;
	}
	public Map<String, Integer> getProductIdAndQuantity() {
		return productIdAndQuantity;
	}
	public void setProductIdAndQuantity(Map<String, Integer> productIdAndQuantity) {
		this.productIdAndQuantity = productIdAndQuantity;
	}
	
	public List<BillItemModel> getItemList() {
		return itemList;
	}
	public void setItemList(List<BillItemModel> itemList) {
		this.itemList = itemList;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getSalesmanId() {
		return salesmanId;
	}
	public void setSalesmanId(String salesmanId) {
		this.salesmanId = salesmanId;
	}
	public CreateEmployeeResponseModel getSalesman() {
		return salesman;
	}
	public void setSalesman(CreateEmployeeResponseModel salesman) {
		this.salesman = salesman;
	}
	
	
	
}
