package guru.springframework.api.v1.mapper;

import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    public static CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    public CategoryDTO categoryToCategoryDTO(final Category category);
}
