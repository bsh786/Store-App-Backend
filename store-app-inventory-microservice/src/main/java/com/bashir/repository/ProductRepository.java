package com.bashir.repository;

import org.springframework.data.repository.CrudRepository;

import com.bashir.entity.ProductEntity;

public interface ProductRepository extends CrudRepository<ProductEntity, Long>{

	public ProductEntity findByProductId(String productId);
	public ProductEntity findByProductName(String productName);
	public void deleteByProductId(String productId);
}
