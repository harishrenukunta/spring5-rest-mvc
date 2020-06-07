package guru.springframework.services;

import guru.springframework.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {
    public List<CategoryDTO> getAllCategories();
    public CategoryDTO getCategoryByName(final String name);
}
