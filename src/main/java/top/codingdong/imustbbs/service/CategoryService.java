package top.codingdong.imustbbs.service;

import top.codingdong.imustbbs.DTO.ResultDTO;
import top.codingdong.imustbbs.po.Category;

import java.util.List;

/**
 * @author Dong
 * @date 2020/3/2 11:39
 */
public interface CategoryService {

    List<Category> selectNavCategory();

    List<Category> selectAll();

    List<Category> selectAll(Integer pageNumber, Integer pageSize);

    Category selectById(Integer id);

    Category selectByName(String name);

    /**
     * 修改分类信息
     *
     * @param category
     * @return
     */
    ResultDTO updateCategory(Category category);

    /**
     * 新增分类
     *
     * @param category
     * @return
     */
    ResultDTO addCategory(Category category);

    ResultDTO deleteCategoryById(Integer categoryId);
}
