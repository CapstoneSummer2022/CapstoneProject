package shop.services.impl;

import shop.config.ModelMap;
import shop.db.dto.CategoryDTO;
import shop.db.entities.Category;
import shop.db.repositories.CategoryRepository;
import shop.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
     final  CategoryRepository categoryRepository;

     final ModelMap modelMap;

    @Override
    public List<CategoryDTO> findCategoriesByParentCategoryIdIsNull() {
        List<Category> categories = categoryRepository.findCategoriesByParentCategoryIdIsNull();
        return categories.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> findCategoriesByIdNotIn(List<CategoryDTO> cId) {
        List<Integer> cIds = new ArrayList<>();
        for (CategoryDTO c: cId
             ) {
            cIds.add(Integer.parseInt(c.getId()));
        }
        List<Category> categories = categoryRepository.findCategoriesByIdNotIn(cIds.toArray(new Integer[cIds.size()]));
        if(categories.isEmpty()){
            throw new NoSuchElementException("Không có danh mục nào");
        }
        else return categories.stream().map(this::convertToDto).collect(Collectors.toList());

    }
    @Override
    public Category getById(String id){
        try{
            Integer cId = Integer.parseInt(id);
            Optional<Category> category = categoryRepository.findById(cId);
            if(category.isEmpty()){
                throw new NoSuchElementException("Không tìm thấy danh mục này");
            }else return category.get();
        }catch (NumberFormatException e){
            throw  new NoSuchElementException("Không có danh mục này");
        }


    }
    CategoryDTO convertToDto(Category category){
        return modelMap.modelMapper().map(category,CategoryDTO.class);
    }
    @Override
    public <S extends Category> S save(S entity) {

        return categoryRepository.save(entity);
    }

    @Override
    public List<CategoryDTO> findAllSubAndParCategories(Integer cId) {
        List<Category> categories = categoryRepository.findAllSubAndParCategories(cId);
        return categories.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(this::convertToDto).collect(Collectors.toList());
    }


    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public CategoryDTO findById(Integer id) {
       return convertToDto(getCategoryById(id));
    }
    @Override
    public boolean addCategory(CategoryDTO categoryDTO){
        Category category = new Category(categoryDTO.getName());
            try{
                if(categoryDTO.getParentId()!=null){
                    Integer parentId = Integer.parseInt(categoryDTO.getParentId());
                    Optional<Category> optionalParentCategory = categoryRepository.findById(parentId);
                    if(optionalParentCategory.isEmpty()){
                        throw  new NoSuchElementException();
                    }
                    else {
                        Category parentCategory = optionalParentCategory.get();
                        category.setParentCategory(parentCategory);
                    }
                }
                categoryRepository.save(category);
                }
                catch (NumberFormatException|NoSuchElementException e){
                        throw new NoSuchElementException("Không tìm thấy danh mục này");
                }
        return true;
    }
    @Override
   public boolean updateCategory(CategoryDTO categoryDTO,String strId){
        Integer id = Integer.parseInt(strId);
        Category updatedCategory = getCategoryById(id);
        updatedCategory.setName(categoryDTO.getName());
        System.out.println(categoryDTO.getParentId()+"aooo");
        if(categoryDTO.getParentId()!=null){
        Integer parentId = Integer.parseInt(categoryDTO.getParentId());
        Category parent = getCategoryById(parentId);
        List<Category> categoriesSelect = categoryRepository.findAllSubAndParCategories(id);
        if(!categoriesSelect.contains(parent)) {
            throw new RuntimeException("Không thể chọn danh mục lớn này");
        }

        updatedCategory.setParentCategory(parent);
        }
        return categoryRepository.save(updatedCategory)!=null;
    }
    public boolean isChildCategory(Category category, Category parent) {
        return category.getParentCategory().equals(parent);
    }
    public Category getCategoryById(Integer id){
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isEmpty()){
            throw  new NoSuchElementException();
        }
        return categoryOptional.get();
    }
    @Override
    public boolean removeChildCategory(Category child){
        child.setParentCategory(null);
        return categoryRepository.save(child)!=null;
    }
    public boolean addChildCategory(Category child,Category parent){
        child.setParentCategory(parent);
        return categoryRepository.save(child)!=null;
    }
    @Override
    public boolean existsById(Integer integer) {
        return categoryRepository.existsById(integer);
    }

    @Override
    public long count() {
        return categoryRepository.count();
    }


}