package com.qortmdcks.crud.service;

import com.qortmdcks.crud.entity.Category;
import com.qortmdcks.crud.payload.CategoryDto;

import java.util.List;
public interface CategoryService {
    // 카테고리 추가, 카테고리 가져오기(전체, 상세), 카데고리 업데이트, 카데코리 삭제

    CategoryDto createCategory(CategoryDto categoryDto);

    List<CategoryDto> getAllCategory();

    CategoryDto getCategoryById(long id);

    void deleteCategoryById(long id);
//
    CategoryDto updateCategory(CategoryDto categoryDto, long id);

}
