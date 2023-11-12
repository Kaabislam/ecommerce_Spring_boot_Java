package com.kaab.ecommerce.controller;

import com.kaab.ecommerce.common.ApiResponse;
import com.kaab.ecommerce.dto.cart.AddToCartDto;
import com.kaab.ecommerce.dto.cart.CartDto;
import com.kaab.ecommerce.model.Product;
import com.kaab.ecommerce.model.User;
import com.kaab.ecommerce.service.AuthenticationService;
import com.kaab.ecommerce.service.CartService;
import com.kaab.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    AuthenticationService authenticationService;
    // post cart api

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
                                                 @RequestParam("token") String token){
        // find the user
        User user = authenticationService.getUser(token);
        cartService.addToCart(addToCartDto,user);

        return new ResponseEntity<>(new ApiResponse(true,"Added to cart"), HttpStatus.CREATED);
    }


    // get all items for a user

    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token){
        // find the user
        User user = authenticationService.getUser(token);

        // get cart items
        CartDto cartDto = cartService.listCartItems(user);

        return new ResponseEntity<>(cartDto,HttpStatus.OK);
    }


    // delete a cart item for a user

}
