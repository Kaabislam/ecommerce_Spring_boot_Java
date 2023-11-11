package com.kaab.ecommerce.controller;

import com.kaab.ecommerce.common.ApiResponse;
import com.kaab.ecommerce.dto.ProductDto;
import com.kaab.ecommerce.model.Product;
import com.kaab.ecommerce.model.User;
import com.kaab.ecommerce.model.WishList;
import com.kaab.ecommerce.service.AuthenticationService;
import com.kaab.ecommerce.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {
    @Autowired
    WishListService wIshListService;

    @Autowired
    AuthenticationService authenticationService;

    // save product in wishlist

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product, @RequestParam("token") String  token){
        // authenticate the token
        authenticationService.authenticate(token);

        // find the user

        User user = authenticationService.getUser(token);


        // save the item in the wishlist
        WishList wishList = new WishList(user,product);
        wIshListService.createWishList(wishList);
        ApiResponse apiResponse = new ApiResponse(true,"Added to Wishlist");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

    }



    // get all wishlist item for a user
    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable ("token") String token){
        // authenticate the token
        authenticationService.authenticate(token);

        // find the user
        User user = authenticationService.getUser(token);

        List<ProductDto> productDtos = wIshListService.getWishListForUser(user);
        return new ResponseEntity<>(productDtos,HttpStatus.OK);

    }


}
