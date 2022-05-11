package com.firatyildiz.LibraryManagementSystem.service;

import com.firatyildiz.LibraryManagementSystem.dto.requestDto.SaveCategoryRequestDto;
import com.firatyildiz.LibraryManagementSystem.dto.requestDto.UpdateCategoryRequestDto;
import com.firatyildiz.LibraryManagementSystem.dto.responseDto.CategoryResponseDto;
import com.firatyildiz.LibraryManagementSystem.entity.Category;
import com.firatyildiz.LibraryManagementSystem.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper modelMapper;

    public String saveCategory(SaveCategoryRequestDto saveCategoryRequestDto) {
        Category category = modelMapper.map(saveCategoryRequestDto, Category.class);

        category = categoryRepository.save(category);

        return category.getCategoryName() + " Has Been Successfully Created.";
    }

    public Category findCategory(Integer categoryId)
    {
        return categoryRepository.findById(categoryId).get();
    }

    public String updateCategory(UpdateCategoryRequestDto updateCategoryRequestDto)
    {
        int idCategoryRequest = updateCategoryRequestDto.getId();
        String nameCategoryRequest = updateCategoryRequestDto.getCategoryName();

        Optional<Category> authorOptional = categoryRepository.findById(idCategoryRequest);
        Category category = authorOptional.get();

        category.setCategoryName(nameCategoryRequest);

        categoryRepository.save(category);

        return "Change Saved Successfully.";
    }


    public String deleteCategoryById(Integer categoryId)
    {
        Category category = categoryRepository.findById(categoryId).get();
        categoryRepository.delete(category);
        return "The Category Deleted.";
    }

    public List<CategoryResponseDto> findAllCategory()
    {
        Iterable<Category> categories = categoryRepository.findAll();

        List<CategoryResponseDto> categoryResponseDtos = new ArrayList<>();

        for (Category category : categories) {
            CategoryResponseDto categoryResponseDto = modelMapper.map(category, CategoryResponseDto.class);
            categoryResponseDtos.add(categoryResponseDto);
        }

        return categoryResponseDtos;
    }
}
