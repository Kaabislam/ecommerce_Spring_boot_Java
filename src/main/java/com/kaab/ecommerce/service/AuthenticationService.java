package com.kaab.ecommerce.service;

import com.kaab.ecommerce.exceptions.AuthenticationFailedException;
import com.kaab.ecommerce.model.AuthenticationToken;
import com.kaab.ecommerce.model.User;
import com.kaab.ecommerce.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService {
    @Autowired
    TokenRepository tokenRepository;
    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        return tokenRepository.findByUser(user);
    }

    // get the user by token
    public User getUser(String token){
        final AuthenticationToken authenticationToken = tokenRepository.findByToken(token);
        if(Objects.isNull(authenticationToken)){
            return null;
        }
        return authenticationToken.getUser();
    }

    public void authenticate(String token){
        // null check
        if(Objects.isNull(token)){
            throw new AuthenticationFailedException("token not present in the request ");
        }
        if(Objects.isNull(getUser(token))){
            throw new AuthenticationFailedException("Token is not valid");
        }

    }
}
