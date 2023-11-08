package com.kaab.ecommerce.service;

import com.kaab.ecommerce.model.Category;
import com.kaab.ecommerce.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;
    public void createCategory(Category category){
        categoryRepo.save(category);
    }

    public List<Category> listCategory(){
        return categoryRepo.findAll();
    }
    public void editCategory(int categoryId,Category updateCategory)
    {
        Category category = categoryRepo.findById(categoryId).get();
        category.setCategoryName(updateCategory.getCategoryName());
        category.setDescription(updateCategory.getDescription());
        category.setImageUrl(updateCategory.getImageUrl());
        categoryRepo.save(category);
    }

    public boolean findById(int categoryId) {
        return categoryRepo.findById(categoryId).isPresent();
    }
}
