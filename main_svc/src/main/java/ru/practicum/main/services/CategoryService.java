package ru.practicum.main.services;

import ru.practicum.main.dto.CategoryDto;
import ru.practicum.main.dto.NewCategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(NewCategoryDto newCategoryDto);

    List<CategoryDto> getCategories(Integer from, Integer size);

    CategoryDto getCategory(Integer catId);

    void deleteCategory(Long catId);

    CategoryDto updateCategory(Integer catId, CategoryDto categoryDto);
}
