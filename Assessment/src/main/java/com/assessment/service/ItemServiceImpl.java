package com.assessment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.dto.ItemDTO;
import com.assessment.entity.Item;
import com.assessment.entity.Product;
import com.assessment.exception.ResourceNotFoundException;
import com.assessment.repo.ItemRepo;
import com.assessment.repo.ProductRepo;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemRepo itemRepo;
	
	@Autowired
	private ProductRepo productRepo;

	@Override
	public List<ItemDTO> getItemsByProductId(Long productId) {
	    Product product = productRepo.findById(productId)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("Product not found with id: " + productId));

	    List<Item> items = itemRepo.findByProductId(productId);

	    return items.stream().map(item -> {
	        ItemDTO dto = new ItemDTO();
	        dto.setId(item.getId());
	        dto.setQuantity(item.getQuantity());
	        return dto;
	    }).toList();
	}
}
