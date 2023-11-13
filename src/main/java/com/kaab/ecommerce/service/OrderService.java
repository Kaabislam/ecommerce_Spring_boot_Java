package com.kaab.ecommerce.service;

import com.kaab.ecommerce.dto.checkout.CheckOutItemDto;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Value("${BASE_URL}")
    private String baseUrl;

    @Value("${STRIPE_SECRET_KEY}")
    private String apiKey;
    public Session createSession(List<CheckOutItemDto> checkOutItemDtoList) throws StripeException {
        // success and failure url

        String successUrl = baseUrl + "payment/success";
        String failureUrl = baseUrl + "payment/failed";
        Stripe.apiKey = apiKey;
        List<SessionCreateParams.LineItem> sessionItemList = new ArrayList<>();
        for (CheckOutItemDto checkOutItemDto:checkOutItemDtoList){
            sessionItemList.add(createSessionLineItem(checkOutItemDto));
        }

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl(failureUrl)
                .setSuccessUrl(successUrl)
                .addAllLineItem(sessionItemList)
                .build();
        return Session.create(params);

    }

    private SessionCreateParams.LineItem createSessionLineItem(CheckOutItemDto checkOutItemDto) {
        return SessionCreateParams.LineItem.builder()
                .setPriceData(createPriceData(checkOutItemDto))
                .setQuantity(Long.parseLong(String.valueOf(checkOutItemDto.getQuantity())))
                .build();
    }

    private SessionCreateParams.LineItem.PriceData createPriceData(CheckOutItemDto checkOutItemDto) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("usd")
                .setUnitAmount((long) (checkOutItemDto.getPrice()*100))
                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                        .setName(checkOutItemDto.getProductName()).build()).build();

    }
}
