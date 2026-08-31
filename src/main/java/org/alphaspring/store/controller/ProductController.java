package org.alphaspring.store.controller;


import org.alphaspring.store.entity.Product;
import org.alphaspring.store.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    @GetMapping
    public List<Product> getProducts(){
        return productRepository.findAll();
    }


    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){

        productRepository.deleteById(id);
    }
    

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct){
        return productRepository.findById(id).map(product -> {
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            product.setStock(updatedProduct.getStock());
            return productRepository.save(product);

        }).orElseGet(()->{
            return null;

        });
    }




}
