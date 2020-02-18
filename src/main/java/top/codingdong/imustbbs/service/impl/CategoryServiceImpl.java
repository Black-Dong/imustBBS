package top.codingdong.imustbbs.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import top.codingdong.imustbbs.DTO.ResultDTO;
import top.codingdong.imustbbs.enums.StatusEnum;
import top.codingdong.imustbbs.mapper.CategoryMapper;
import top.codingdong.imustbbs.model.Category;
import top.codingdong.imustbbs.service.CategoryService;

import java.util.List;

/**
 * @author Dong
 * @date 2020/2/18 11:37
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ResultDTO<Category> createCategory(Category category) {

        Category dbCategory = this.findCategoryByName(category.getName());

        if (dbCategory != null) {
            return ResultDTO.warnOf(StatusEnum.CATEGORY_EXIST);
        } else {
            category.setId(null);
            category.setCreateTime(System.currentTimeMillis());
            category.setUpdateTime(System.currentTimeMillis());
            categoryMapper.insertSelective(category);
            return ResultDTO.success();
        }
    }

    @Override
    public List<Category> listCategory(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize, "update_time desc");
        List<Category> categories = categoryMapper.selectAll();
        return categories;
    }

    @Override
    public Category findCategoryByName(String name) {
        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo("name", name);
        return categoryMapper.selectOneByExample(example);
    }

    @Override
    public Category findCategoryById(Integer id) {
        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo("id", id);
        return categoryMapper.selectOneByExample(example);
    }

    @Override
    public ResultDTO<Category> updateCategoryById(Category category) {

        Category dbCategory = this.findCategoryByName(category.getName());

        if (dbCategory != null) {
            return ResultDTO.warnOf(StatusEnum.CATEGORY_EXIST);
        } else {
            category.setUpdateTime(System.currentTimeMillis());
            categoryMapper.updateByPrimaryKeySelective(category);
            return ResultDTO.success();
        }
    }

}
