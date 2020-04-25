package top.codingdong.imustbbs.controller.admin;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.codingdong.imustbbs.po.User;
import top.codingdong.imustbbs.service.TopicService;
import top.codingdong.imustbbs.service.UserService;
import top.codingdong.imustbbs.util.Constants;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 管理员控制类
 *
 * @author Dong
 * @date 2020/3/7 21:03
 */
@Controller
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    UserService userService;

    /**
     * 禁用用户
     *
     * @param userId
     */
    @GetMapping("/disableUser")
    @ResponseBody
    public void disableUser(@RequestParam(name = "userId") Integer userId) {
        userService.disableUser(userId);
    }

    /**
     * 解除禁用用户
     *
     * @param userId
     */
    @GetMapping("/unDisableUser")
    @ResponseBody
    public void unDisableUser(@RequestParam(name = "userId") Integer userId) {
        userService.unDisableUser(userId);
    }

    /**
     * 根据传入的 searchName 按username精确查询该用户
     *
     * @param searchName
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/searchLikeUserName/{searchName}")
    public String searchLikeUserName(@PathVariable(name = "searchName") String searchName,
                                     HttpSession session,
                                     Model model) {

        model.addAttribute("searchName", searchName);
        if ("".equals(searchName)) {
            return "redirect: /user/manager/5/1";
        }

        // 根据searchName精确查询，
        User userByUserName = userService.selectByUsername(searchName);

        model.addAttribute("userByUserName", userByUserName);

        List list = null;
        list = userService.selectAllmember(1, 10);

        PageInfo pageInfo = PageInfo.of(list);
        model.addAttribute("pageInfo", pageInfo);
        User currentUser = (User) session.getAttribute(Constants.CURRENT_USER);
        if ("超级管理员".equals(currentUser.getRoleName())) {
            List<User> admins = userService.selectAdmins(1, 10);
            PageInfo<User> pageInfo_admin = PageInfo.of(admins);
            model.addAttribute("pageInfo_admin", pageInfo_admin);
        }

        model.addAttribute("activeManager", 5);

        return "/user/userManager";
    }

}
