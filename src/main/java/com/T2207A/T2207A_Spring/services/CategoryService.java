package com.T2207A.T2207A_Spring.services;

import com.T2207A.T2207A_Spring.entities.Category;
import com.T2207A.T2207A_Spring.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category category){
        return categoryRepository.findById(id)
                .map(c-> {
                    c.setName(category.getName());
                    c.setSlug(category.getSlug());
                    c.setStatus(category.getStatus());
                    return categoryRepository.save(c);
                })
                .orElseGet(()->{
                    category.setId(id);
                    return categoryRepository.save(category);
                });
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
