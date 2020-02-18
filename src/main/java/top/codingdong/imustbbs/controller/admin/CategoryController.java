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
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories.html")
    @ApiOperation("跳转分类管理页")
    public String jumpType(@RequestParam(name = "pageNumber", defaultValue = "1") Integer pageNumber,
                           @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                           Model model) {
        PageInfo pageInfo = PageInfo.of(categoryService.listCategory(pageNumber, pageSize));
        model.addAttribute("pageInfo", pageInfo);
        return "admin/categories";
    }

    @GetMapping("/category.html")
    @ApiOperation("跳转分类新增页")
    public String jumpAddType() {
        return "admin/category";
    }

    /**
     * 新增分类，返回状态
     * 成功状态跳转categories，失败状态提示状态信息
     *
     * @param category
     * @param model
     * @return
     */
    @PostMapping("/category")
    @ApiOperation("新增或修改分类")
    public String category(Category category,
                           RedirectAttributes redirectAttributes,
                           Model model) {

        // 判断分类名称是否为空
        if (StringUtils.isBlank(category.getName())) {
            model.addAttribute("resultDTO", ResultDTO.warnOf(StatusEnum.NAME_IS_EMPTY));
            return "admin/category";
        }

        ResultDTO<Category> resultDTO = null;

        // 判断有没有传递id
        if (category.getId() != null) {
            resultDTO = categoryService.updateCategoryById(category);
            model.addAttribute("category", category);
        } else {
            resultDTO = categoryService.createCategory(category);
            model.addAttribute("category", null);
        }
        model.addAttribute("resultDTO", resultDTO);
        if (!(StatusEnum.SUCCESS.getCode()).equals(resultDTO.getCode())) {
            return "admin/category";
        }
        redirectAttributes.addFlashAttribute("resultDTO", resultDTO);
        return "redirect:/admin/categories.html";
    }


    @GetMapping("/category/{id}")
    @ApiOperation("跳转修改分类页")
    public String editCategory(@PathVariable(name = "id") Integer id,
                               RedirectAttributes redirectAttributes,
                               Model model) {

        Category category = categoryService.findCategoryById(id);

        if (category == null) {
            redirectAttributes
                    .addFlashAttribute("resultDTO", ResultDTO.warnOf(StatusEnum.CATEGORY_NOT_EXIST));
            return "redirect:/admin/categories.html";
        }

        model.addAttribute("category", category);
        return "admin/category";

    }

}
