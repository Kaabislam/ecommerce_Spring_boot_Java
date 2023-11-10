package com.kaab.ecommerce.service;

import com.kaab.ecommerce.model.WishList;
import com.kaab.ecommerce.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishListService {
    @Autowired
    WishListRepository wishListRepository;

    public void createWishList(WishList wishList) {
        wishListRepository.save(wishList);
    }
}
