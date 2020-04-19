package top.codingdong.imustbbs.controller.admin;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.codingdong.imustbbs.DTO.ResultDTO;
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
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    TopicService topicService;

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
     * 根据帖子id删除帖子，伪删除（修改帖子状态为不公开）
     *
     * @param topicId
     */
    @GetMapping("/deleteTopic")
    @ResponseBody
    public void deleteTopic(@RequestParam(name = "id") Integer topicId) {
        topicService.deleteById(topicId);
        return;
    }


    /**
     * 修改帖子的置顶状态
     *
     * @param topicId
     * @param topStatus
     */
    @GetMapping("/topTopic")
    @ResponseBody
    public void topTopic(@RequestParam(name = "id") Integer topicId,
                         @RequestParam("topStatus") boolean topStatus) {

        topicService.topTopicById(topicId, topStatus);
        return;
    }

    /**
     * 修改帖子的精品状态
     *
     * @param topicId
     * @param boutiqueStatus
     */
    @GetMapping("/boutiqueTopic")
    @ResponseBody
    public void boutiqueTopic(@RequestParam(name = "id") Integer topicId,
                              @RequestParam("boutiqueStatus") boolean boutiqueStatus) {

        topicService.boutiqueTopicById(topicId, boutiqueStatus);
        return;
    }


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
