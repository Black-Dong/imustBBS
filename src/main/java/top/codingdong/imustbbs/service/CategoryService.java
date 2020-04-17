package top.codingdong.imustbbs.service;

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

    void updateCategory(Category category);
}
