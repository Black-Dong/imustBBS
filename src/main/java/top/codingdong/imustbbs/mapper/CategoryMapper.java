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

    /**
     * 根据分类id修改分类信息
     *
     * @param category
     */
    @Update("update t_category set description = #{description} where id= #{id}")
    void update(Category category);

    /**
     * 查询nav的要展示的分类id与名称
     * @return
     */
    @Select("select c.id, c.name from t_category c " +
            "right join t_category_order o " +
            "on c.id = o.category_id")
    List<Category> selectNavCategory();

    @Select("select 1 from t_category where name = #{name} limit 1")
    Integer existsWithName(String name);
}
