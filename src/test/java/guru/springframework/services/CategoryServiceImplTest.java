package guru.springframework.services;

import guru.springframework.api.v1.mapper.CategoryMapper;
import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.domain.Category;
import guru.springframework.repository.CategoryRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CategoryServiceImplTest {

    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void before(){
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository,CategoryMapper.INSTANCE);
    }

    @Test
    public void getAllCategories(){
        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Fruits");

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Nuts");

        List<Category> mockCategories = new ArrayList<>();
        mockCategories.add(category1);
        mockCategories.add(category2);

        when(categoryRepository.findAll()).thenReturn(mockCategories);

        final List<CategoryDTO> categories = categoryService.getAllCategories();
        assertThat(categories.size()).isEqualTo(mockCategories.size());
    }


    @Test
    public void getCategoryByName(){
        Category category = new Category();
        category.setId(2L);
        category.setName("pulses");

        when(categoryRepository.findByName(anyString())).thenReturn(category);
        final CategoryDTO categoryDTO = categoryService.getCategoryByName("Fruits");
        assertThat(categoryDTO).isNotNull().isEqualToComparingFieldByField(category);

    }

}