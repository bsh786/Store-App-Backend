package com.bashir.model;

import java.util.List;
import java.util.Map;

import com.bashir.entity.BillItemModel;

public class NewBillResponseModel {

	private Long billId;
	private List<BillItemModel> itemList;
	private int totalAmount;
	private CreateEmployeeResponseModel salesman;
	
	
	public List<BillItemModel> getItemList() {
		return itemList;
	}
	public void setItemList(List<BillItemModel> itemList) {
		this.itemList = itemList;
	}
	
	public Long getBillId() {
		return billId;
	}
	public void setBillId(Long billId) {
		this.billId = billId;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public CreateEmployeeResponseModel getSalesman() {
		return salesman;
	}
	public void setSalesman(CreateEmployeeResponseModel salesman) {
		this.salesman = salesman;
	}
	
	
	
	
	
}
