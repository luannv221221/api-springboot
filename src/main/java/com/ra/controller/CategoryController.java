package com.ra.controller;

import com.ra.model.dto.category.CategoryRequestDTO;
import com.ra.model.dto.category.CategoryResponseDTO;
import com.ra.model.dto.category.ResponseWrapper;
import com.ra.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public ResponseEntity<?> getAllCategories(){
        List<CategoryResponseDTO> categoryResponseDTOS = categoryService.getCategories();
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseWrapper.builder()
                        .message("Successfully retrieved categories")
                        .code(200)
                        .data(categoryResponseDTOS)
                        .build()
        );
    }
    @PostMapping
    public ResponseEntity<?> saveCategory(@RequestBody CategoryRequestDTO categoryRequestDTO){
        CategoryResponseDTO categoryResponseDTO = categoryService.saveCategory(categoryRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseWrapper.builder()
                        .message("Successfully saved category")
                        .code(HttpStatus.CREATED.value())
                        .data(categoryResponseDTO)
                        .build()
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDTO categoryRequestDTO){

        CategoryResponseDTO categoryResponseDTO = categoryService.updateCategory(categoryRequestDTO,id);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseWrapper.builder()
                        .message("Successfully updated category")
                        .code(HttpStatus.OK.value())
                        .data(categoryResponseDTO)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id){
        CategoryResponseDTO categoryResponseDTO = categoryService.findCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseWrapper.builder()
                        .message("Successfully retrieved category by "+id)
                        .code(HttpStatus.OK.value())
                        .data(categoryResponseDTO)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Long id){
        categoryService.deleteCategory(id);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
//                ResponseWrapper.builder().message("Successfully deleted category")
//                        .code(HttpStatus.NO_CONTENT.value())
//                        .data(null)
//        );
        return ResponseEntity.noContent().build();
    }
}
