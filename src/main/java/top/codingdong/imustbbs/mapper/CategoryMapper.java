package top.codingdong.imustbbs.mapper;

import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;
import top.codingdong.imustbbs.po.Category;

/**
 * @author Dong
 * @date 2020/2/27 18:05
 */
public interface CategoryMapper extends Mapper<Category> {
    @Update("update t_category set description = #{description} where name = #{name}")
    void update(Category category);
}
