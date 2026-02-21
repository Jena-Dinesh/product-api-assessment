package com.assessment.service;

import java.util.List;

import com.assessment.dto.ItemDTO;

public interface ItemService {

	List<ItemDTO> getItemsByProductId(Long productId);
}
