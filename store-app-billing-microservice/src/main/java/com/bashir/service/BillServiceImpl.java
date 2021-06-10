package com.bashir.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bashir.entity.BillEntity;
import com.bashir.entity.BillItemModel;
import com.bashir.model.AddProductResponseModel;
import com.bashir.model.CreateEmployeeResponseModel;
import com.bashir.model.UpdateProductQuantityRequestModel;
import com.bashir.repository.BillRepository;
import com.bashir.shared.BillDto;

import feign.FeignException;


@Service
@Transactional
public class BillServiceImpl implements BillService{

	BillRepository billRepository;
	InventoryServiceInterface inventoryService;
	UsersServiceInterface salesmanService;
	
	public BillServiceImpl(BillRepository billRepository,
			InventoryServiceInterface inventoryService,
			UsersServiceInterface salesmanService)  {
		
		this.billRepository = billRepository;
		this.inventoryService = inventoryService;
		this.salesmanService = salesmanService;
	}
	
	
	@Override
	public BillDto generateNewBill(BillDto billDto) throws Exception {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(
				MatchingStrategies.STRICT);
		
		List<BillItemModel> itemList = new ArrayList<BillItemModel>();
		
		Map<String, Integer> itemsMap = billDto.getProductIdAndQuantity();
		
		int totalAmount = 0;
		
		for(String productId:itemsMap.keySet())
		{
			ResponseEntity<AddProductResponseModel> inventoryResponse = 
					inventoryService.getProductById(productId);
			
			AddProductResponseModel product = inventoryResponse.getBody();
			
			BillItemModel billItem = modelMapper.map(product,
					BillItemModel.class);
			
			
			
			billItem.setTotalProductAmount(product.getPrice()*itemsMap.get(productId));
			billItem.setQuantity(itemsMap.get(productId));
			
			ResponseEntity<Integer> getItemQuantityResponse = inventoryService
					.getProductQuantity(productId);
			
			int availableItemQuantity = getItemQuantityResponse.getBody();
			System.out.println("Available Quantity: "+availableItemQuantity);
			
			if(availableItemQuantity<billItem.getQuantity())
			{
				throw new Exception("Item quantity is higher than available");
			}
					
		    
			totalAmount = totalAmount+billItem.getTotalProductAmount(); 
			itemList.add(billItem);
		}
		
		BillEntity bill = modelMapper.map(billDto, BillEntity.class);
		bill.setItemList(itemList);
		bill.setTotalAmount(totalAmount);
		
		bill = billRepository.save(bill);
		
		List<BillItemModel> itemsList = bill.getItemList();
		
		for(BillItemModel item:itemsList)
		{
			UpdateProductQuantityRequestModel productAndQuantity = new
					UpdateProductQuantityRequestModel();
			
			System.out.println("UPDATE ITEM ID: "+item.getProductId());
			System.out.println("UPDATE ITEM QUANTITY: "+item.getQuantity());
			productAndQuantity.setProductId(item.getProductId());
			productAndQuantity.setBoughtQuantity(item.getQuantity());
			
			ResponseEntity<Integer> updatedProductQuantityResponse = 
			inventoryService.updateProductQuantity(productAndQuantity);
			
			int updatedProductQuantity = -1;
			
			updatedProductQuantity = updatedProductQuantityResponse.getBody();

			if(updatedProductQuantity == -1)
			{
				throw new Exception("Could not update product");
			}
		}
		
		
	    ResponseEntity<CreateEmployeeResponseModel> salesmanServiceResponse
	    = null;
        
		try {
	    	salesmanServiceResponse = salesmanService
	    			.getSalesman(billDto.getSalesmanId());
		}
		catch(FeignException e)
		{
			e.printStackTrace();
		}
	    CreateEmployeeResponseModel salesman = salesmanServiceResponse
	    		                              .getBody();
	    
	    BillDto savedBillDto = modelMapper.map(bill, BillDto.class);
	    savedBillDto.setSalesman(salesman);
	    
		return savedBillDto;
		
	}


	@Override
	public List<BillDto> getAllBills() {
		
	 ModelMapper modelMapper = new ModelMapper();
	 modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies
			 .STRICT);
	 
	 List<BillEntity> billsList = (List<BillEntity>) billRepository.findAll();
	 List<BillDto> billDtoList = new ArrayList<BillDto>();
	 
	 for(BillEntity bill:billsList)
	 {
		 BillDto billDto = modelMapper.map(bill, BillDto.class);
		 ResponseEntity<CreateEmployeeResponseModel> salesmanServiceResponse =
				 salesmanService.getSalesman(bill.getSalesmanId());
		 
		 CreateEmployeeResponseModel salesman = salesmanServiceResponse
				                                .getBody();
		 billDto.setSalesman(salesman);
		 billDtoList.add(billDto);
		 
	 }
	 	
		return billDtoList;
	}


	@Override
	public BillDto getBill(Long billId) throws Exception {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies
				.STRICT);
		
		
		BillEntity bill = billRepository.findByBillId(billId);

		
		if(bill == null)
		{
			throw new Exception("Bill with id "+billId+" not found");
		}
		
		BillDto billDto = modelMapper.map(bill, BillDto.class);
		ResponseEntity<CreateEmployeeResponseModel> salesmanServiceResponse =
				salesmanService.getSalesman(billDto.getSalesmanId());
		
		CreateEmployeeResponseModel salesman = salesmanServiceResponse
				.getBody();
		
		billDto.setSalesman(salesman);
		
		return billDto;
	}

}
