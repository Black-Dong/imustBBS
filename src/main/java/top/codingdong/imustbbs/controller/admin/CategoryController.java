package top.codingdong.imustbbs.controller.admin;

import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tk.mybatis.mapper.entity.Example;
import top.codingdong.imustbbs.DTO.ResultDTO;
import top.codingdong.imustbbs.enums.StatusEnum;
import top.codingdong.imustbbs.mapper.CategoryMapper;
import top.codingdong.imustbbs.model.Category;
import top.codingdong.imustbbs.service.CategoryService;

/**
 * @author Dong
 * @date 2020/2/18 10:21
 */
@Controller
@Api(tags = {"admin_category"})
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category.html")
    public String jumpType(){
        return "admin/category";
    }

    @PostMapping("/category")
    public String category(Category category,
                           Model model){

        // 判断分类名称是否为空
        if (StringUtils.isBlank(category.getName())){
            model.addAttribute("resultDTO",ResultDTO.warnOf(StatusEnum.NAME_IS_EMPTY));
            return "admin/category";
        }

        ResultDTO<Category> resultDTO = categoryService.addCategory(category);

        if (!(StatusEnum.SUCCESS.getCode()).equals(resultDTO.getCode())){
            model.addAttribute("resultDTO",resultDTO);
        }
        return "admin/category";
    }

}
