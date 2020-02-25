package top.codingdong.imustbbs.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.codingdong.imustbbs.service.CategoryService;

/**
 * @author Dong
 * @date 2020/2/19 9:38
 */
@Controller
@Api(tags = {"category"})
public class CategoryController {

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

    @GetMapping("/categories")
    @ApiOperation("获取分类列表")
    @ResponseBody
    public PageInfo categories(@RequestParam(name = "pageNumber", defaultValue = "1") Integer pageNumber,
                               @RequestParam(name = "pageSize", defaultValue = "999") Integer pageSize) {
        PageInfo pageInfo = PageInfo.of(categoryService.listCategory(pageNumber, pageSize));
        return pageInfo;
    }
}
