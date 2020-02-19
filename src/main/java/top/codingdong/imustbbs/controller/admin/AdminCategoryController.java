package top.codingdong.imustbbs.controller.admin;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import top.codingdong.imustbbs.DTO.ResultDTO;
import top.codingdong.imustbbs.enums.StatusEnum;
import top.codingdong.imustbbs.model.Category;
import top.codingdong.imustbbs.service.CategoryService;

/**
 * @author Dong
 * @date 2020/2/18 10:21
 */
@Controller
@Api(tags = {"admin_category"})
@RequestMapping("/admin")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories.html")
    @ApiOperation("跳转分类列表页")
    public String jumpCategory(@RequestParam(name = "pageNumber", defaultValue = "1") Integer pageNumber,
                               @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                               Model model) {
        PageInfo pageInfo = PageInfo.of(categoryService.listCategory(pageNumber, pageSize));
        model.addAttribute("pageInfo", pageInfo);
        return "admin/categories";
    }

    @GetMapping("/category.html")
    @ApiOperation("跳转分类新增页")
    public String jumpAddCategory() {
        return "admin/category-input";
    }

    @GetMapping("/category/{id}")
    @ApiOperation("跳转修改分类页")
    public String jumpEditCategory(@PathVariable(name = "id") Integer id,
                                   RedirectAttributes redirectAttributes,
                                   Model model) {

        Category dbCategory = categoryService.findCategoryById(id);

        if (dbCategory == null) {
            redirectAttributes
                    .addFlashAttribute("resultDTO", ResultDTO.warnOf(StatusEnum.CATEGORY_NOT_EXIST));
            return "redirect:/admin/categories.html";
        }

        model.addAttribute("category", dbCategory);
        return "admin/category-input";
    }

    /**
     * 新增分类，返回状态
     *
     * @param category
     * @return
     */
    @PostMapping("/category")
    @ApiOperation("新增或修改分类")
    @ResponseBody
    public ResultDTO<Category> category(Category category) {

        // 判断分类名称是否为空
        if (StringUtils.isBlank(category.getName())) {
            return ResultDTO.warnOf(StatusEnum.CATEGORY_NAME_IS_EMPTY);
        }

        ResultDTO<Category> resultDTO;

        // 判断有没有传递id
        if (category.getId() != null) {
            resultDTO = categoryService.updateCategoryById(category);
        } else {
            resultDTO = categoryService.createCategory(category);
        }
        return resultDTO;
    }

}
