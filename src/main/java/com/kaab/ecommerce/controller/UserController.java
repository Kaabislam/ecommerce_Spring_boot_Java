package com.kaab.ecommerce.controller;

import com.kaab.ecommerce.common.ApiResponse;
import com.kaab.ecommerce.dto.ResponseDto;
import com.kaab.ecommerce.dto.user.SignInDto;
import com.kaab.ecommerce.dto.user.SignInResponseDto;
import com.kaab.ecommerce.dto.user.SignUpDto;
import com.kaab.ecommerce.model.User;
import com.kaab.ecommerce.service.AuthenticationService;
import com.kaab.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("user")
@RestController
public class UserController {
    // two api
    // signup
    // signin
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseDto signUp(@RequestBody SignUpDto signUpDto){
        return userService.signUp(signUpDto);
    }

    @PostMapping("/signin")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto){
        return userService.signIn(signInDto);
    }
    @GetMapping("/all")
    public List<User> allUser(){
        return userService.finall();
    }

    
}
