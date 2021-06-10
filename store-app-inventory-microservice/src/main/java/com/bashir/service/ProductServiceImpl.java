package com.bashir.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.bashir.entity.ProductEntity;
import com.bashir.model.UpdateProductQuantityRequestModel;
import com.bashir.repository.ProductRepository;
import com.bashir.shared.ProductDto;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	ProductRepository productRepository;
	
	
	public ProductServiceImpl(ProductRepository productRepository) {
	
		this.productRepository = productRepository;
		
	}
	
	
	
	@Override
	public ProductDto addNewProduct(ProductDto newProduct) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(
				MatchingStrategies.STRICT);
		
		ProductEntity product = modelMapper.map(newProduct, 
				     ProductEntity.class);
		
		productRepository.save(product);
		
		ProductEntity savedProduct = productRepository.findByProductId(
				newProduct.getProductId());
		
		ProductDto savedProductDto = modelMapper.map(savedProduct,
				ProductDto.class);
		
		return savedProductDto;
	}

	@Override
	public List<ProductDto> getAllProducts() {
		
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(
				MatchingStrategies.STRICT);
		
		List<ProductEntity> products = (List<ProductEntity>) 
				productRepository.findAll();

		List<ProductDto> productDtoList = new ArrayList<ProductDto>();
		
		for(ProductEntity product: products)
		{
			ProductDto productDto = modelMapper.map(product, ProductDto.class);
			productDtoList.add(productDto);
		}
		
		return productDtoList;
	}

	@Override
	public ProductDto getProductByName(String productName) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(
				MatchingStrategies.STRICT);
		
		ProductEntity product = productRepository.findByProductName(
				productName);
		
		ProductDto productDto = modelMapper.map(product, ProductDto.class);
		return productDto;
	}

	@Override
	public ProductDto getProductById(String productId) { 
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(
				MatchingStrategies.STRICT);
		
		ProductEntity product = productRepository.findByProductId(productId);
		
		ProductDto productDto =  modelMapper.map(product, ProductDto.class);
		return productDto;
	}



	@Override
	public String deleteProduct(String productId) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(
				MatchingStrategies.STRICT);
		
		productRepository.deleteByProductId(productId);
		
		return "Deleted";
	}



	@Override
	public Integer getProductQuantity(String productId) {
		
		ProductEntity product = productRepository.findByProductId(productId);
		Integer productQuantity = product.getQuantity();
		
		return productQuantity;
	}



	@Override
	public Integer updateProductQuantity(UpdateProductQuantityRequestModel updateProductQuantity) {
		
		
		System.out.println("Product ID: "+updateProductQuantity.getProductId());
		System.out.println("Bought quantity: "+updateProductQuantity.getBoughtQuantity());
		
		ProductEntity product = productRepository
				.findByProductId(updateProductQuantity.getProductId());
		
		Integer updatedQuantity = product.getQuantity()-updateProductQuantity.getBoughtQuantity();
		product.setQuantity(updatedQuantity);
		product = productRepository.save(product);
				
		return product.getQuantity();
	}

	
}
