package top.codingdong.imustbbs.service;

import top.codingdong.imustbbs.DTO.ResultDTO;
import top.codingdong.imustbbs.model.Category;

/**
 * @author Dong
 * @date 2020/2/18 11:36
 */
public interface CategoryService {
    ResultDTO<Category> addCategory(Category category);
}
