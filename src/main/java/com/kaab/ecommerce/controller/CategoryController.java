package com.kaab.ecommerce.controller;

import com.kaab.ecommerce.common.ApiResponse;
import com.kaab.ecommerce.model.Category;
import com.kaab.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true,"A new Category Created"), HttpStatus.CREATED);
    }


    @GetMapping("/list")
    public List<Category> listCategory(){
        return categoryService.listCategory();
    }

    @PostMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") int categoryId,@RequestBody Category updateCategory){
        if(!categoryService.findById(categoryId)){
            return new ResponseEntity<>(new ApiResponse(false,"Category does not Exist"),HttpStatus.NOT_FOUND);
        }
        categoryService.editCategory(categoryId,updateCategory);
        return new ResponseEntity<>(new ApiResponse(true,"Category has been Updated"),HttpStatus.OK);
    }
}
