package com.assessment.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductDTO {

	private long productId;
	
	@NotBlank
	private String productName;
	
	@NotBlank
	private String createdBy;
	
	private LocalDateTime createdOn;
	
	private String modifiedBy;
	
	private LocalDateTime modifiedOn;
	
}
