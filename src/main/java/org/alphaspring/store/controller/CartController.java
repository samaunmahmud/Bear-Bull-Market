package org.alphaspring.store.controller;


import org.alphaspring.store.entity.Cart;
import org.alphaspring.store.entity.CartItem;
import org.alphaspring.store.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {


    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @GetMapping
    public ResponseEntity<Cart> getCart(Authentication authentication) {
        String username = authentication.getName();
        Cart cart  = cartService.getCartByUsername(username);
        return ResponseEntity.ok(cart);
    }


    @PostMapping("/items")
    public ResponseEntity<Cart> addItemToCart(Authentication authentication, @RequestParam Long productId, @RequestParam Integer quantity){
        String username = authentication.getName();
        Cart updatedCart = cartService.addProdutToCart(username, productId,quantity);
    return ResponseEntity.ok(updatedCart);
    }


}
