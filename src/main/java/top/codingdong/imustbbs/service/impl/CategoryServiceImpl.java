package top.codingdong.imustbbs.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import top.codingdong.imustbbs.mapper.CategoryMapper;
import top.codingdong.imustbbs.po.Category;
import top.codingdong.imustbbs.service.CategoryService;

import java.util.List;

/**
 * @author Dong
 * @date 2020/3/2 11:40
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> selectTop5() {
        PageHelper.startPage(1, 5);
        return categoryMapper.selectAll();
    }

    @Override
    public List<Category> selectAll() {
        return categoryMapper.selectAll();
    }

    @Override
    public List<Category> selectAll(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        return categoryMapper.selectAll();
    }

    @Override
    public Category selectById(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public Category selectByName(String name) {
        Example example = new Example(Category.class);
        example.createCriteria()
                .andEqualTo("name",name);
        return (Category) categoryMapper.selectByExample(example);
    }

    @Override
    public void updateCategory(Category category) {
        categoryMapper.update(category);
    }
}
