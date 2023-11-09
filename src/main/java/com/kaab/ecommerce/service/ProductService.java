package com.kaab.ecommerce.service;

import com.kaab.ecommerce.dto.ProductDto;
import com.kaab.ecommerce.model.Category;
import com.kaab.ecommerce.model.Product;
import com.kaab.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public void createProduct(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setCategory(category);
        productRepository.save(product);

    }
    public ProductDto getProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setCategoryId(product.getCategory().getId());
        return productDto;
    }

    public List<ProductDto> getAllProduct() {
        List<Product> allProducts = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product:allProducts){
            productDtos.add(getProductDto(product));
        }
        return productDtos;
    }

    public void updateProduct(ProductDto productDto, Integer productId) throws Exception {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(!optionalProduct.isPresent()){
            throw new Exception("Product is not present!");
        }
        Product product = optionalProduct.get();
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        productRepository.save(product);

    }
}
