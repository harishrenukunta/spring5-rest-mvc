package guru.springframework.api.v1.mapper;

import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.domain.Category;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    private CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void testCategoryMapper(){
        Category dbCategory = new Category();
        dbCategory.setId(1L);
        dbCategory.setName("testDBCategory");
        final CategoryDTO catDTO = categoryMapper.categoryToCategoryDTO(dbCategory);
        assertThat(catDTO).isEqualToComparingFieldByField(dbCategory);
    }

}