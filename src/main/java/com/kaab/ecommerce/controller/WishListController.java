package com.kaab.ecommerce.controller;

import com.kaab.ecommerce.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishlist")
public class WishListController {
    @Autowired
    WishListService wIshListService;

    // save product in wishlist




    // get all wishlist item for a user
}
