package com.assessment.service;

import org.springframework.data.domain.*;

import com.assessment.dto.ProductDTO;

public interface ProductService {

	Page<ProductDTO> getAllProducts(Pageable pageable);
	
	ProductDTO insertProduct(ProductDTO productDTO);
	
	ProductDTO updateProductById(ProductDTO productDTO, Long productId);
	
	ProductDTO getProductById(Long productId);
	
	void deleteProductById(Long productId);
}
