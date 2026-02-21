package com.assessment.integration;

import static org.junit.jupiter.api.Assertions.*;

import com.assessment.dto.ProductDTO;
import com.assessment.service.ProductService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ProductServiceIntegrationTest {

    @Autowired
    private ProductService productService;

    @Test
    void testCreateAndFetchProduct() {
        ProductDTO dto = new ProductDTO();
        dto.setProductName("Integration Laptop");
        dto.setCreatedBy("TestUser");

        ProductDTO saved = productService.insertProduct(dto);

        assertNotNull(saved.getProductId());

        ProductDTO fetched = productService.getProductById(saved.getProductId());
        assertEquals("Integration Laptop", fetched.getProductName());
    }
}