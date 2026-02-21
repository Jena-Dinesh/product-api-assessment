package com.assessment.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.assessment.dto.ProductDTO;
import com.assessment.entity.Product;
import com.assessment.exception.ResourceNotFoundException;
import com.assessment.repo.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepo productRepo;

	@Override
	public Page<ProductDTO> getAllProducts(Pageable pageable) {
        return productRepo.findAll(pageable)
                .map(this::convertToDTO);
    }

	@Override
	public ProductDTO insertProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        product.setCreatedOn(LocalDateTime.now());
        Product saved = productRepo.save(product);
        return convertToDTO(saved);
    }

	@Override
	 public ProductDTO updateProductById(ProductDTO productDTO, Long productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + productId));

        product.setProductName(productDTO.getProductName());
        product.setModifiedBy(productDTO.getModifiedBy());
        product.setModifiedOn(LocalDateTime.now());

        Product updated = productRepo.save(product);
        return convertToDTO(updated);
    }

	@Override
	public ProductDTO getProductById(Long productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + productId));

        return convertToDTO(product);
    }

	@Override
	public void deleteProductById(Long productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + productId));

        productRepo.delete(product);
    }

	// Convert Entity -> DTO
    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setProductId(product.getId());
        dto.setProductName(product.getProductName());
        dto.setCreatedBy(product.getCreatedBy());
        dto.setCreatedOn(product.getCreatedOn());
        dto.setModifiedBy(product.getModifiedBy());
        dto.setModifiedOn(product.getModifiedOn());
        return dto;
    }

    // Convert DTO -> Entity
    private Product convertToEntity(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getProductId());
        product.setProductName(dto.getProductName());
        product.setCreatedBy(dto.getCreatedBy());
        product.setCreatedOn(
                dto.getCreatedOn() != null ? dto.getCreatedOn() : LocalDateTime.now()
        );
        product.setModifiedBy(dto.getModifiedBy());
        product.setModifiedOn(dto.getModifiedOn());
        return product;
    }
}
