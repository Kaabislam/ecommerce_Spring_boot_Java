package com.kaab.ecommerce.service;


import com.kaab.ecommerce.dto.cart.AddToCartDto;
import com.kaab.ecommerce.model.Cart;
import com.kaab.ecommerce.model.Product;
import com.kaab.ecommerce.model.User;
import com.kaab.ecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductService productService;
    public void addToCart(AddToCartDto addToCartDto, User user) {
        // validate if the product id is valid
        Product product = productService.findById(addToCartDto.getProductId());
        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setUser(user);
        cart.setQuantity(addToCartDto.getQuantity());
        cart.setCreatedDate(new Date());

        // save the cart
        cartRepository.save(cart);

    }
}
