package com.kaab.ecommerce.service;

import com.kaab.ecommerce.dto.ResponseDto;
import com.kaab.ecommerce.dto.user.SignInDto;
import com.kaab.ecommerce.dto.user.SignInResponseDto;
import com.kaab.ecommerce.dto.user.SignUpDto;
import com.kaab.ecommerce.exceptions.AuthenticationFailedException;
import com.kaab.ecommerce.exceptions.CustomException;
import com.kaab.ecommerce.model.AuthenticationToken;
import com.kaab.ecommerce.model.User;
import com.kaab.ecommerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationService authenticationService;

    @Transactional
    public ResponseDto signUp(SignUpDto signUpDto) {
        // check if the user is already present
        if(Objects.nonNull(userRepository.findByEmail(signUpDto.getEmail()))){
            throw new CustomException("User already Exist");
        }
        // hash the password
        String encryptedPassword = signUpDto.getPassword();
        try {
            encryptedPassword = hashPassword(signUpDto.getPassword());
        } catch (Exception e){
            e.printStackTrace();
        }
        User user = new User(signUpDto.getFirstName(), signUpDto.getLastName(),
                signUpDto.getEmail(), encryptedPassword);
        userRepository.save(user);

        final AuthenticationToken authenticationToken = new AuthenticationToken(user);
        authenticationService.saveConfirmationToken(authenticationToken);


        ResponseDto responseDto = new ResponseDto("success","User created Successfully");
        return responseDto;

    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(password.getBytes());
        byte[] digest = messageDigest.digest();
        String hash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hash;
    }

    public List<User> finall() {
        return userRepository.findAll();
    }

    public SignInResponseDto signIn(SignInDto signInDto) {
        // find user by email
        User user = userRepository.findByEmail(signInDto.getEmail());
        if(Objects.isNull(user)){
            throw new AuthenticationFailedException("user is not valid");
        }

        // hash the password and compare with DB
        try {
            if(!user.getPassword().equals(hashPassword(signInDto.getPassword()))){
                throw new ArithmeticException("Wrong Password");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }


        // if password match then retrieve the token
        AuthenticationToken token = authenticationService.getToken(user);
        if(Objects.isNull(token)){
            throw new CustomException("Token is not Present");
        }
        return new SignInResponseDto("Success",token.getToken());

    }
}
