package top.codingdong.imustbbs.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import top.codingdong.imustbbs.DTO.ResultDTO;
import top.codingdong.imustbbs.mapper.CategoryMapper;
import top.codingdong.imustbbs.mapper.TopicMapper;
import top.codingdong.imustbbs.po.Category;
import top.codingdong.imustbbs.service.CategoryService;

import javax.xml.transform.Result;
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

    @Autowired
    private TopicMapper topicMapper;

    @Override
    public List<Category> selectNavCategory() {
        return categoryMapper.selectNavCategory();
    }

    @Override
    public List<Category> selectAll() {
        return categoryMapper.selectAll();
    }

    @Override
    public List<Category> selectAll(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
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
                .andEqualTo("name", name);
        return (Category) categoryMapper.selectByExample(example);
    }

    @Override
    public ResultDTO updateCategory(Category category) {
        if (category.getId() == null || !categoryMapper.existsWithPrimaryKey(category.getId())) {
            return ResultDTO.errorOf("您要修改的分类不存在！！！");
        } else if ("".equals(category.getName())) {
            return ResultDTO.errorOf("分类名不能为空！！！");
        } else {
            categoryMapper.update(category);
            return ResultDTO.success();
        }
    }

    /**
     * 新增分类
     *
     * @param category
     * @return
     */
    @Override
    public ResultDTO addCategory(Category category) {
        try {
            if (categoryMapper.existsWithName(category.getName()) != null) {
                return ResultDTO.errorOf("分类名称已存在！！！");
            } else if ("".equals(category.getName().trim())) {
                return ResultDTO.errorOf("分类名不能为空！！！");
            }
            category.setCreateTime(System.currentTimeMillis());
            category.setUpdateTime(System.currentTimeMillis());
            category.setName(category.getName().trim());
            categoryMapper.insert(category);
            return ResultDTO.success();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResultDTO.errorOf("新增失败");
        }
    }


    @Override
    public ResultDTO deleteCategoryById(Integer categoryId) {
        Integer countPublicTopics = topicMapper.countPublicTopicsByCategoryId(categoryId);
        if (countPublicTopics != 0){
            return ResultDTO.errorOf("该分类下还有公开的帖子 "+ countPublicTopics + " 个！");
        }else {
            categoryMapper.deleteByPrimaryKey(categoryId);
            return ResultDTO.success("删除成功");
        }
    }

    @Override
    public List<Category> selectALLCategory() {

        return categoryMapper.selectAll();
    }
}
