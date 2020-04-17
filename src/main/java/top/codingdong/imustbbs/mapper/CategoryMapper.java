package top.codingdong.imustbbs.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;
import top.codingdong.imustbbs.po.Category;

import java.util.List;

/**
 * @author Dong
 * @date 2020/2/27 18:05
 */
public interface CategoryMapper extends Mapper<Category> {
    @Update("update t_category set description = #{description} where name = #{name}")
    void update(Category category);

    @Select("select c.id, c.name from t_category c " +
            "right join t_category_order o " +
            "on c.id = o.category_id")
    List<Category> selectNavCategory();
}
