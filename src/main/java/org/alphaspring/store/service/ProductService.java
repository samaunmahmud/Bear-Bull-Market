package org.alphaspring.store.service;


import org.alphaspring.store.entity.Product;
import org.alphaspring.store.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {


    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    public Product getProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }


    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }


    public Product updateProduct(Long id, Product updatedProduct){
        return productRepository.findById(id).map(existingProduct ->{
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            return productRepository.save(existingProduct);
        }).orElse(null);
    }




}
