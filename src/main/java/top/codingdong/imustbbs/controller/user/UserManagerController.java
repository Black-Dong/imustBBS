package top.codingdong.imustbbs.controller.user;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import top.codingdong.imustbbs.po.User;
import top.codingdong.imustbbs.service.CategoryService;
import top.codingdong.imustbbs.service.TopicService;
import top.codingdong.imustbbs.service.UserService;
import top.codingdong.imustbbs.util.Constants;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Dong
 * @date 2020/4/19 20:58
 */
@Controller
@RequestMapping("/user")
public class UserManagerController {

    @Autowired
    TopicService topicService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    /**
     * 跳转后台管理页面
     *
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/manager/{managerId}/{pageNumber}")
    public String topicManager(@PathVariable Integer managerId,
                               @PathVariable Integer pageNumber,
                               Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute(Constants.CURRENT_USER);
        List list = null;
        // 帖子管理
        if (managerId == 2) {
            list = topicService.list(pageNumber, 10, currentUser.getUserId());

            PageInfo pageInfo = PageInfo.of(list);
            model.addAttribute("pageInfo", pageInfo);

            model.addAttribute("activeManager", managerId);
            return "/user/myTopicManager";
        }

        // 用户管理
        if (managerId == 5) {
            list = userService.selectAllmember(pageNumber, 10);

            PageInfo pageInfo = PageInfo.of(list);
            model.addAttribute("pageInfo", pageInfo);

            if ("超级管理员".equals(currentUser.getRoleName())) {
                List<User> admins = userService.selectAdmins(pageNumber, 10);
                PageInfo<User> pageInfo_admin = PageInfo.of(admins);
                model.addAttribute("pageInfo_admin", pageInfo_admin);
            }

            model.addAttribute("activeManager", managerId);
            return "/user/userManager";
        }

        // 分类管理
        if (managerId == 6) {
            list = categoryService.selectAll(pageNumber, 10);

            PageInfo pageInfo = PageInfo.of(list);
            model.addAttribute("pageInfo", pageInfo);

            model.addAttribute("activeManager", managerId);
            return "/user/categoryManager";
        }

        return "/user/myTopicManager";
    }
}
