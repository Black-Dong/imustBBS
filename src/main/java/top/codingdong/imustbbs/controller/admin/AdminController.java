package top.codingdong.imustbbs.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.codingdong.imustbbs.service.UserService;

/**
 * 管理员控制类
 *
 * @author Dong
 * @date 2020/3/7 21:03
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping("/disableUser")
    @ResponseBody
    /**
     * 禁用用户
     */
    public void disableUser(@RequestParam(name = "userId") Integer userId) {
        userService.disableUser(userId);
    }

    @GetMapping("/unDisableUser")
    @ResponseBody
    /**
     * 解除禁用用户
     */
    public void unDisableUser(@RequestParam(name = "userId") Integer userId) {
        userService.unDisableUser(userId);
    }
}
