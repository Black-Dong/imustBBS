package top.codingdong.imustbbs.service;

import top.codingdong.imustbbs.DTO.ResultDTO;
import top.codingdong.imustbbs.model.Category;

import java.util.List;

/**
 * @author Dong
 * @date 2020/2/18 11:36
 */
public interface CategoryService {

    ResultDTO<Category> addCategory(Category category);

    List<Category> listCategory(Integer pageNumber,Integer pageSize);
}
