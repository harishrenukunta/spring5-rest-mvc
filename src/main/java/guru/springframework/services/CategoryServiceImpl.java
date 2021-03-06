package guru.springframework.services;

import guru.springframework.api.v1.mapper.CategoryMapper;
import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.api.v1.model.CategoryListDTO;
import guru.springframework.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository,CategoryMapper catMapper){
        this.categoryMapper = catMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryListDTO getAllCategories() {
        List<CategoryDTO> categories =  categoryRepository.findAll().stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(toList());
        return new CategoryListDTO(categories);
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
    }
}
