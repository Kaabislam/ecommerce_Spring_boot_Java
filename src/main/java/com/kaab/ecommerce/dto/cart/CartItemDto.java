package com.kaab.ecommerce.dto.cart;

import com.kaab.ecommerce.model.Cart;
import com.kaab.ecommerce.model.Product;

public class CartItemDto {
    private Integer id;
    private Integer qunatity;
    private Product product;

    public CartItemDto() {
    }
    public CartItemDto(Cart cart){
        this.id = cart.getId();
        this.qunatity = cart.getQuantity();
        this.product = cart.getProduct();
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQunatity() {
        return qunatity;
    }

    public void setQunatity(Integer qunatity) {
        this.qunatity = qunatity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
