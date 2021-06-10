package com.bashir.entity;


import java.util.List;


import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="bill")
public class BillEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long billId;
	
	@ElementCollection
	private List<BillItemModel> itemList;
	
	private Integer totalAmount;
	
	private String salesmanId;

	public long getBillId() {
		return billId;
	}

	public void setBillId(long billId) {
		this.billId = billId;
	}

	
	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(String salesmanId) {
		this.salesmanId = salesmanId;
	}

	public List<BillItemModel> getItemList() {
		return itemList;
	}

	public void setItemList(List<BillItemModel> itemList) {
		this.itemList = itemList;
	}


	
	
	
	
	
	
	
}
