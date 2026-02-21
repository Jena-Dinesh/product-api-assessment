package com.assessment.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import com.assessment.dto.ProductDTO;
import com.assessment.entity.Product;
import com.assessment.repo.ProductRepo;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepo productRepo;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testGetProductById() {
        Product product = new Product(1L, "Laptop", "Dinesh",
                LocalDateTime.now(), null, null, null);

        when(productRepo.findById(1L)).thenReturn(Optional.of(product));

        ProductDTO dto = productService.getProductById(1L);

        assertEquals("Laptop", dto.getProductName());
        verify(productRepo, times(1)).findById(1L);
    }

    @Test
    void testInsertProduct() {
        ProductDTO dto = new ProductDTO();
        dto.setProductName("Mobile");
        dto.setCreatedBy("Admin");

        Product saved = new Product(1L, "Mobile", "Admin",
                LocalDateTime.now(), null, null, null);

        when(productRepo.save(any(Product.class))).thenReturn(saved);

        ProductDTO result = productService.insertProduct(dto);

        assertNotNull(result.getProductId());
        assertEquals("Mobile", result.getProductName());
    }
    
    @Test
    void testGetProductById_NotFound() {
        when(productRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            productService.getProductById(1L);
        });
    }
}