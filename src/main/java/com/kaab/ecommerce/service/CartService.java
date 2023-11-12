package com.kaab.ecommerce.service;


import com.kaab.ecommerce.dto.cart.AddToCartDto;
import com.kaab.ecommerce.dto.cart.CartItemDto;
import com.kaab.ecommerce.dto.cart.CartDto;
import com.kaab.ecommerce.model.Cart;
import com.kaab.ecommerce.model.Product;
import com.kaab.ecommerce.model.User;
import com.kaab.ecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public CartDto listCartItems(User user) {
        List<Cart> carts = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<CartItemDto> cartItems = new ArrayList<>();
        double totalCost = 0.0;

        for(Cart cart:carts){
            CartItemDto cartItemDto = new CartItemDto(cart);
            totalCost += (cartItemDto.getQunatity() * cart.getProduct().getPrice());
            cartItems.add(cartItemDto);
        }
        CartDto cartDto = new CartDto();
        cartDto.setTotalCost(totalCost);
        cartDto.setCartItems(cartItems);
        return cartDto;
    }
}
