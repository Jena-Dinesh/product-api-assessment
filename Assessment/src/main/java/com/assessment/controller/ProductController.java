package com.assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.assessment.dto.ItemDTO;
import com.assessment.dto.ProductDTO;
import com.assessment.service.ItemService;
import com.assessment.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ItemService itemService;

	@GetMapping
	public ResponseEntity<Page<ProductDTO>> getAllProducts(Pageable pageable) {
		return new ResponseEntity<>(productService.getAllProducts(pageable), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
		return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO dto) {
		return new ResponseEntity<>(productService.insertProduct(dto), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO dto, @PathVariable Long id) {
		return new ResponseEntity<>(productService.updateProductById(dto, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
		productService.deleteProductById(id);
		return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
	}
	
	@GetMapping("/{id}/items")
	public ResponseEntity<List<ItemDTO>> getItemsByProductId(@PathVariable Long id) {
	    List<ItemDTO> items = itemService.getItemsByProductId(id);
	    return ResponseEntity.ok(items);
	}
}