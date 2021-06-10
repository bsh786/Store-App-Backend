package com.bashir.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.bashir.model.AddProductResponseModel;
import com.bashir.model.UpdateProductQuantityRequestModel;

@FeignClient(name="store-app-inventory-service")
public interface InventoryServiceInterface {

	@GetMapping(value="/product/id/{productId}")
	public ResponseEntity<AddProductResponseModel> getProductById(
			@PathVariable String productId); 
	
	@GetMapping(value="/product/quantity/{productId}")
	public ResponseEntity<Integer> getProductQuantity(
			@PathVariable String productId);
	
	@PutMapping(value="/product")
	public ResponseEntity<Integer> updateProductQuantity(
			UpdateProductQuantityRequestModel updateProductQuantity);
}
