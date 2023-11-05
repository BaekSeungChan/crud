package com.qortmdcks.crud.service.impl;

import com.qortmdcks.crud.entity.Category;
import com.qortmdcks.crud.payload.CategoryDto;
import com.qortmdcks.crud.repository.CategoryRepository;
import com.qortmdcks.crud.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    private ModelMapper modelMapper;


    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper){
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public CategoryDto createCategory(CategoryDto categoryDto){
        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepository.save(category);

        return modelMapper.map(savedCategory, CategoryDto.class);
    }


    @Override
    public List<CategoryDto> getAllCategory(){
        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map((category)-> modelMapper
                .map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(long id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("No id"));

        return modelMapper.map(category, CategoryDto.class);

    }

    @Override
    public  CategoryDto updateCategory(CategoryDto categoryDto, long id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("No id"));

        category.setId(id);
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        Category updatedCategory = categoryRepository.save(category);
        return modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategoryById(long id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("No id"));
        categoryRepository.delete(category);
    }

}
