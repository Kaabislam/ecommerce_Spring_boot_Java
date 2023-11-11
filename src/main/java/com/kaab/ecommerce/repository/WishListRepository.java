package com.kaab.ecommerce.repository;

import com.kaab.ecommerce.model.User;
import com.kaab.ecommerce.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList,Integer> {

    List<WishList> findAllByUserOrderByCreatedDateDesc(User user);
}
