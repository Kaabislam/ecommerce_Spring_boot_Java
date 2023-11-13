package com.kaab.ecommerce.controller;

import com.kaab.ecommerce.dto.checkout.CheckOutItemDto;
import com.kaab.ecommerce.dto.checkout.StripeResponse;
import com.kaab.ecommerce.service.AuthenticationService;
import com.kaab.ecommerce.service.OrderService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    OrderService orderService;

    // stripe session checkout api

    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckOutItemDto> checkOutItemDtoList)
    throws StripeException {
        Session session = orderService.createSession(checkOutItemDtoList);

        StripeResponse stripeResponse = new StripeResponse(session.getId());
        return new ResponseEntity<>(stripeResponse, HttpStatus.OK);
    }
}
