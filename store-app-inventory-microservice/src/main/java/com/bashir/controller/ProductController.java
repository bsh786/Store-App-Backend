package com.bashir.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bashir.model.AddProductRequestModel;
import com.bashir.model.AddProductResponseModel;
import com.bashir.model.UpdateProductQuantityRequestModel;
import com.bashir.service.ProductService;
import com.bashir.shared.ProductDto;

@RestController
@RequestMapping("/product")
public class ProductController {

	
	@Autowired
	ProductService productService;
	
	
	@GetMapping(path="/status/check")
	public String checkStatus()
	{
		return "WORKING";
	}
	
	@PostMapping
	public ResponseEntity<AddProductResponseModel> addProduct(
			@RequestBody AddProductRequestModel productRequest)
	{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(
				MatchingStrategies.STRICT);
		
		ProductDto productDto = modelMapper.map(productRequest, 
				ProductDto.class);
		
		ProductDto savedProductDto = productService.addNewProduct(productDto);
		
		AddProductResponseModel addedProduct = modelMapper.map(savedProductDto, 
				AddProductResponseModel.class);
		
		return new ResponseEntity<AddProductResponseModel>(addedProduct,
				HttpStatus.CREATED);
	}
	
	
	@GetMapping
	public ResponseEntity<List<AddProductResponseModel>> getAllProducts()
	{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies
				.STRICT);
		
		List<ProductDto> productDtoList = productService.getAllProducts();
		
		List<AddProductResponseModel> productList = new
				ArrayList<AddProductResponseModel>();
		
		
		for(ProductDto productDto: productDtoList)
		{
			AddProductResponseModel product = modelMapper.map(
					productDto, AddProductResponseModel.class);
			
			productList.add(product);
		}
		
		return new ResponseEntity<List<AddProductResponseModel>>(productList,
				HttpStatus.OK);
		
	}
	
	
	
	@GetMapping(path="/id/{id}")
	public ResponseEntity<AddProductResponseModel> getProductById(
			@PathVariable String id)
	{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(
				MatchingStrategies.STRICT);
		
		
		ProductDto productDto = productService.getProductById(id);
		
		AddProductResponseModel product = modelMapper.map(productDto, 
				AddProductResponseModel.class);
		
		return new ResponseEntity<AddProductResponseModel>(product, 
				HttpStatus.OK);
	}
	
	
	
	@GetMapping(value="/name/{name}")
	public ResponseEntity<AddProductResponseModel> getProductByName(
			@PathVariable String name)
	{
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(
				MatchingStrategies.STRICT);
		
		ProductDto productDto = productService.getProductByName(name);
		
		AddProductResponseModel product = modelMapper.map(productDto, 
				AddProductResponseModel.class);
		
		return new ResponseEntity<AddProductResponseModel>(product,
				HttpStatus.OK);
	}
	
	
	@GetMapping("/quantity/{productId}")
	public ResponseEntity<Integer> getProductQuantity(@PathVariable
			String productId)
	{
		Integer productQuantity = productService.getProductQuantity(productId);
		return new ResponseEntity<Integer>(productQuantity, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Integer> updateProductQuantity(
		 @RequestBody UpdateProductQuantityRequestModel updateProductQuantity)
	{
		System.out.println(updateProductQuantity.getProductId());
		System.out.println(updateProductQuantity.getBoughtQuantity());
		
		Integer updatedQuantity = productService
				.updateProductQuantity(updateProductQuantity);
		
		return new ResponseEntity<Integer>(updatedQuantity, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable 
			String productId)
	{
	    String deleteStatus = productService.deleteProduct(productId);	
	    
	    return new ResponseEntity<String>(deleteStatus, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
}
