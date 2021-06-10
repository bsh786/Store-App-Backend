package com.bashir.controller;


import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bashir.model.NewBillRequestModel;
import com.bashir.model.NewBillResponseModel;
import com.bashir.service.BillService;
import com.bashir.shared.BillDto;

@RestController
@RequestMapping("/bill")
public class BillController {
	
	@Autowired
	BillService billService;
	
	
	@GetMapping("/status/check")
	public String checkStatus()
	{
		return "WORKING";
	}

	@PostMapping
	public ResponseEntity<NewBillResponseModel>
	    generateNewBill(@RequestBody NewBillRequestModel newBill)
	{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(
				MatchingStrategies.STRICT);
		
		BillDto billDto = modelMapper.map(newBill, BillDto.class);
        BillDto savedBillDto = new BillDto();
		try {
			savedBillDto = billService.generateNewBill(billDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        NewBillResponseModel savedBill = modelMapper.map(savedBillDto, 
        		NewBillResponseModel.class);
        
		return new ResponseEntity<NewBillResponseModel>(savedBill,
				HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<NewBillResponseModel>> getAllBills()
	{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(
				MatchingStrategies.STRICT);
		
		List<BillDto> billDtoList = billService.getAllBills();
		List<NewBillResponseModel> billsList = new 
				        ArrayList<NewBillResponseModel>();
	
	    for(BillDto billDto: billDtoList)
	    {
	    	NewBillResponseModel bill = modelMapper.map(billDto, 
	    			NewBillResponseModel.class);
	    	billsList.add(bill);
	    }
	    
	    return new ResponseEntity<List<NewBillResponseModel>>(billsList,
	    		HttpStatus.OK);
	}
	
	
	@GetMapping(value="/{billId}")
	public ResponseEntity<NewBillResponseModel> getBill(@PathVariable
			Long billId)
	{
	   ModelMapper modelMapper = new ModelMapper();
	   modelMapper.getConfiguration().setMatchingStrategy(
			   MatchingStrategies.STRICT);
	   
	   BillDto billDto = new BillDto();
	   
	   try {
	
		   billDto = billService.getBill(billId);
	     
	   } catch (Exception e) {
	
		e.printStackTrace();
	    return new ResponseEntity<NewBillResponseModel>(HttpStatus.BAD_REQUEST);
	   }
	   
	   NewBillResponseModel bill = modelMapper.map(billDto, 
			   NewBillResponseModel.class);
	   
	   return new ResponseEntity<NewBillResponseModel>(bill, HttpStatus.OK);
	   
	}
	
	
	
}
