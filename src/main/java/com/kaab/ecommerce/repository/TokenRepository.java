package com.kaab.ecommerce.repository;

import com.kaab.ecommerce.model.AuthenticationToken;
import com.kaab.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken,Integer> {

    AuthenticationToken findByUser(User user);
    AuthenticationToken findByToken(String token;
}
