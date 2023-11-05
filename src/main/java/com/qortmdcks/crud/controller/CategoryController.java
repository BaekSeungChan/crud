package com.qortmdcks.crud.controller;

import com.qortmdcks.crud.entity.Category;
import com.qortmdcks.crud.payload.CategoryDto;
import com.qortmdcks.crud.payload.PostDto;
import com.qortmdcks.crud.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@Tag(name = "Category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping
    public CategoryDto createCategory(@Valid @RequestBody CategoryDto categoryDto){
        return categoryService.createCategory(categoryDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
//        return categoryService.getAllCategory();
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable(name = "id") long id, @Valid @RequestBody CategoryDto categoryDto){

        return ResponseEntity.ok(categoryService.updateCategory(categoryDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable(name = "id") long id){
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>("deleted post", HttpStatus.OK);
    }
}
