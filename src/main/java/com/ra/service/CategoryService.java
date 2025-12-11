package com.ra.service;

import com.ra.model.dto.category.CategoryRequestDTO;
import com.ra.model.dto.category.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDTO> getCategories();
    CategoryResponseDTO saveCategory(CategoryRequestDTO categoryRequestDTO);
    CategoryResponseDTO updateCategory(CategoryRequestDTO categoryRequestDTO,Long id);
    void deleteCategory(Long id);
    CategoryResponseDTO findCategoryById(Long id);
}
