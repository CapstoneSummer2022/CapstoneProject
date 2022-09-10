package shop.services;

import shop.db.dto.CategoryDTO;
import shop.db.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> findCategoriesByParentCategoryIdIsNull();


    List<CategoryDTO> findCategoriesByIdNotIn(List<CategoryDTO> cId);

    Category getById(String id);

    <S extends Category> S save(S entity);

    List<CategoryDTO> findAllSubAndParCategories(Integer cId);

    List<CategoryDTO> findAll();

    Page<Category> findAll(Pageable pageable);
    boolean addCategory(CategoryDTO categoryDTO);
    boolean updateCategory(CategoryDTO categoryDTO,String strId);
    CategoryDTO findById(Integer id);
    boolean existsById(Integer integer);
    boolean removeChildCategory(Category child);
    boolean addChildCategory(Category child,Category parent);
    long count();
}
