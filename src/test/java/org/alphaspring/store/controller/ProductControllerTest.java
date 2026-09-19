package org.alphaspring.store.controller;




import org.alphaspring.store.entity.Product;
import org.alphaspring.store.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class) // 1. Tells Spring: "Only load the web layer for ProductController"
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc; // 2. Injects our virtual browser

    @MockitoBean
    private ProductService productService; // 3. Mocks the service layer so we don't rely on real database logic

    @Test
    void getProductById_ShouldReturnProductJson() throws Exception {
        // --- ARRANGE ---
        Long productId = 1L;
        Product mockProduct = new Product();
        mockProduct.setId(productId);
        mockProduct.setName("Wireless Mouse");
        mockProduct.setPrice(BigDecimal.valueOf(29.99));

        // Tell the mocked service what to return when the controller calls it
        when(productService.getProductById(productId)).thenReturn(mockProduct);

        // --- ACT & ASSERT ---
        mockMvc.perform(get("/api/products/{id}", productId)) // Simulate a HTTP GET request
                .andExpect(status().isOk())                      // Expect HTTP 200 OK
                .andExpect(jsonPath("$.id").value(1))            // Expect JSON field 'id' to be 1
                .andExpect(jsonPath("$.name").value("Wireless Mouse")) // Expect name
                .andExpect(jsonPath("$.price").value(29.99));    // Expect price
    }
}