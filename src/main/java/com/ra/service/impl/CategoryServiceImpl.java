package com.ra.service.impl;

import com.ra.model.dto.category.CategoryRequestDTO;
import com.ra.model.dto.category.CategoryResponseDTO;
import com.ra.model.entity.Category;
import com.ra.repository.CategoryRepository;
import com.ra.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<CategoryResponseDTO> getCategories() {
        List<Category>  categories = categoryRepository.findAll();
        List<CategoryResponseDTO> categoryResponseDTOS = new ArrayList<>();
        categoryResponseDTOS = categories.stream().
                map(category -> CategoryResponseDTO.builder()
                        .id(category.getId())
                        .categoryName(category.getCategoryName())
                        .status(category.isStatus()).build()
                ).toList();
        return categoryResponseDTOS;
    }

    @Override
    public CategoryResponseDTO saveCategory(CategoryRequestDTO categoryRequestDTO) {
        // convert tu DTO -> entity
        Category category = Category.builder().
                categoryName(categoryRequestDTO.getCategoryName())
                .status(categoryRequestDTO.isStatus()).build();
        Category savedCategory = categoryRepository.save(category);
        return CategoryResponseDTO.builder()
                .id(savedCategory.getId())
                .categoryName(savedCategory.getCategoryName())
                .status(savedCategory.isStatus())
                .build();
    }

    @Override
    public CategoryResponseDTO updateCategory(CategoryRequestDTO categoryRequestDTO, Long id) {
        // kiem tra co id ko ko co bao
        // convert tu DTO -> entity
        Category category = Category.builder().
                id(id).
                categoryName(categoryRequestDTO.getCategoryName())
                .status(categoryRequestDTO.isStatus()).build();
        Category savedCategory = categoryRepository.save(category);
        return CategoryResponseDTO.builder()
                .id(savedCategory.getId())
                .categoryName(savedCategory.getCategoryName())
                .status(savedCategory.isStatus())
                .build();
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryResponseDTO findCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        return CategoryResponseDTO.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .status(category.isStatus()).build();
    }
}
