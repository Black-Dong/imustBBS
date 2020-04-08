package top.codingdong.imustbbs.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.codingdong.imustbbs.DTO.ResultDTO;
import top.codingdong.imustbbs.po.Category;
import top.codingdong.imustbbs.service.CategoryService;
import top.codingdong.imustbbs.service.UserService;

/**
 * 超级管理员控制类
 *
 * @author Dong
 * @date 2020/4/7 16:38
 */
@Controller
@RequestMapping("/super")
public class SuperAdminController {

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    /**
     * 普通用户授权为管理员
     *
     * @param userId
     */
    @GetMapping("/authorizeAdmin")
    @ResponseBody
    public void authorizeAdmin(@RequestParam(name = "userId") Integer userId) {
        userService.authorizeAdmin(userId);
        return;
    }

    /**
     * 管理员用户取消授权为普通用户
     *
     * @param userId
     */
    @GetMapping("/deauthorizeAdmin")
    @ResponseBody
    public void deauthorizeAdmin(@RequestParam(name = "userId") Integer userId) {
        userService.deauthorizeAdmin(userId);
        return;
    }

    /**
     * 跳转分类修改页
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/editCategory/{id}")
    public String modifyCategory(@PathVariable(name = "id")Integer id,
                                 Model model){
        Category category = categoryService.selectById(id);
        model.addAttribute("category",category);
        return "/user/editCategory";
    }

    @PostMapping("/modifyCategory")
    public @ResponseBody ResultDTO modifyCategory(@RequestBody Category category){

        categoryService.updateCategory(category);
        return ResultDTO.success();
    }
}
