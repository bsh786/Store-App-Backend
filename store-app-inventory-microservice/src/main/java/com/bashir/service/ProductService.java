package com.bashir.service;

import java.util.List;

import com.bashir.model.UpdateProductQuantityRequestModel;
import com.bashir.shared.ProductDto;

public interface ProductService {

	public ProductDto addNewProduct(ProductDto newProduct);
    public List<ProductDto> getAllProducts();
    public ProductDto getProductByName(String productName);
    public ProductDto getProductById(String productId);
    public String deleteProduct(String productId);
    public Integer getProductQuantity(String productId);
    public Integer updateProductQuantity(UpdateProductQuantityRequestModel updateProductQuantity);
    
}
