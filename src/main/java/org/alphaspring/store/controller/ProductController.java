package org.alphaspring.store.controller;


import jakarta.validation.Valid;
import org.alphaspring.store.entity.Product;
import org.alphaspring.store.repository.CategoryRepository;
import org.alphaspring.store.repository.ProductRepository;
import org.alphaspring.store.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }


    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product){
        if(product.getCategory() == null || product.getCategory().getId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        boolean categoryExists = categoryRepository.existsById(product.getCategory().getId());

        if(!categoryExists){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);

    }







}
