package com.kaab.ecommerce.controller;

import com.kaab.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    // post cart api



    // get all items for a user



    // delete a cart item for a user

}
