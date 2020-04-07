package top.codingdong.imustbbs.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @GetMapping("/authorizeAdmin")
    @ResponseBody
    public void authorizeAdmin(@RequestParam(name = "userId") Integer userId) {
        userService.authorizeAdmin(userId);
        return;
    }
}
