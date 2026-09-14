package org.alphaspring.store.service;

import jakarta.transaction.Transactional;
import org.alphaspring.store.entity.Cart;
import org.alphaspring.store.entity.CartItem;
import org.alphaspring.store.entity.Product;
import org.alphaspring.store.repository.CartItemRepository;
import org.alphaspring.store.repository.CartRepository;
import org.alphaspring.store.repository.ProductRepository;
import org.alphaspring.store.repository.UserRepository;
import org.alphaspring.store.entity.User;
import org.springframework.stereotype.Service;


@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;

    }

    @Transactional
    public Cart getCartByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));


        return cartRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Cart newCart = new Cart(user);
                    return cartRepository.save(newCart);
                });
    }



    @Transactional
    public Cart addProdutToCart(String username, Long productId, int quantity){
        Cart cart =getCartByUsername(username);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if(existingItem != null){
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            cartItemRepository.save(existingItem);

        }else{
            CartItem newItem = new CartItem(cart, product, quantity);
            cart.getItems().add(newItem);
            cartItemRepository.save(newItem);
        }

        return cart;


    }



}
