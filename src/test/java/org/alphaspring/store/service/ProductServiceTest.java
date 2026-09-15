package org.alphaspring.store.service;

import org.alphaspring.store.entity.Product;
import org.alphaspring.store.repository.ProductRepository;
import org.alphaspring.store.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void getProductById_ReturnsProduct() {
        // --- 1. ARRANGE ---
        Long productId = 1L;
        Product dummyProduct = new Product();
        dummyProduct.setId(productId);
        dummyProduct.setName("Wireless Mouse");
        dummyProduct.setPrice(BigDecimal.valueOf(29.99)); // Fixed: use BigDecimal.valueOf()

        when(productRepository.findById(productId)).thenReturn(Optional.of(dummyProduct));

        // --- 2. ACT ---
        Product foundProduct = productService.getProductById(productId);

        // --- 3. ASSERT ---
        assertNotNull(foundProduct);
        assertEquals("Wireless Mouse", foundProduct.getName());
        assertEquals(BigDecimal.valueOf(29.99), foundProduct.getPrice()); // Fixed: compare BigDecimal to BigDecimal

        verify(productRepository, times(1)).findById(productId);
    }


    @Test
    void getProductById_NotFound_ThrowsException(){
        Long productId =99L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () ->{
            productService.getProductById(productId);
        });

        assertEquals("Product not found", exception.getMessage());

        verify(productRepository, times(1)).findById(productId);
    }
}