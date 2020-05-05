package top.codingdong.imustbbs.service;

import top.codingdong.imustbbs.DTO.ResultDTO;
import top.codingdong.imustbbs.po.Category;

import java.util.List;

/**
 * @author Dong
 * @date 2020/3/2 11:39
 */
public interface CategoryService {

    /**
     * 查找nav上的分类 ：5个
     * @return
     */
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

    /**
     * 根据id删除分类
     *
     * @param categoryId
     * @return
     */
    ResultDTO deleteCategoryById(Integer categoryId);

    /**
     * 查询所有分类
     *
     * @return
     */
    List<Category> selectALLCategory();
}
