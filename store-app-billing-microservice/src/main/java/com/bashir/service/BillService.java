package com.bashir.service;


import java.util.List;

import com.bashir.shared.BillDto;

public interface BillService {
	
	public BillDto generateNewBill(BillDto billDto) throws Exception;
	public List<BillDto> getAllBills();
	public BillDto getBill(Long billId) throws Exception;
	

}
