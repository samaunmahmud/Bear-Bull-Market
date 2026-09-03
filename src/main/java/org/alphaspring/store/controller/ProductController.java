package org.alphaspring.store.controller;


import org.alphaspring.store.entity.Product;
import org.alphaspring.store.repository.ProductRepository;
import org.alphaspring.store.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;

    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }


    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){

        productService.deleteProduct(id);
    }
    

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct){
        return productService.updateProduct(id, updatedProduct);
    }




}
