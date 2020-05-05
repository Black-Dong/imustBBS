package top.codingdong.imustbbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.codingdong.imustbbs.DTO.RePasswordDTO;
import top.codingdong.imustbbs.DTO.ResultDTO;
import top.codingdong.imustbbs.po.Reply;
import top.codingdong.imustbbs.po.Topic;
import top.codingdong.imustbbs.po.User;
import top.codingdong.imustbbs.service.ReplyService;
import top.codingdong.imustbbs.service.TopicService;
import top.codingdong.imustbbs.service.UserService;
import top.codingdong.imustbbs.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Dong
 * @date 2020/4/1 15:48
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private ReplyService replyService;

    /**
     * 用户主页
     *
     * @param id         用户Id
     * @param pageNumber 页数
     * @param model
     * @return
     */
    @GetMapping("/detail/{id}")
    public String userHome(@PathVariable Integer id,
                           @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
                           Model model) {

        User dbUser = userService.selectById(id);
        List<Topic> dbTopics = topicService.list(pageNumber, 10, id);

        List<Reply> dbReplies = replyService.listAndTopicByUserId(dbUser.getUserId(), pageNumber, 4);


        model.addAttribute("user", dbUser);
        model.addAttribute("topics", dbTopics);
        model.addAttribute("replies", dbReplies);

        return "/user/userHome";
    }


    /**
     * 修改基本信息
     *
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/modifyBasicInformation")
    @ResponseBody
    public ResultDTO modifyBasicInformation(@RequestBody User user, HttpSession session) {

        // 修改用户基本信息，返回修改后的信息
        User modifyUser = userService.modifyBasicInformation(user);
        session.setAttribute(Constants.CURRENT_USER, modifyUser);

        return ResultDTO.success("用户基本信息修改成功！");
    }

    /**
     * 修改密码
     *
     * @param repasswordDTO
     * @return
     */
    @PostMapping("/rePassword")
    @ResponseBody
    public ResultDTO rePassword(@RequestBody RePasswordDTO repasswordDTO) {

        if ("".equals(repasswordDTO.getNowPassword())) {
            return ResultDTO.errorOf("请输入原密码！");
        } else if ("".equals(repasswordDTO.getNewPassword())) {
            return ResultDTO.errorOf("请输入新密码！");
        } else if ("".equals(repasswordDTO.getRePassword())) {
            return ResultDTO.errorOf("请重复输入新密码！");
        } else if (!repasswordDTO.getNewPassword().equals(repasswordDTO.getRePassword())) {
            return ResultDTO.errorOf("新密码两次输入不一致！");
        }

        int flag = userService.rePassword(repasswordDTO);
        if (flag == 1) {
            return ResultDTO.success("密码修改成功");
        }
        return ResultDTO.errorOf("密码错误请重试");
    }


    @PostMapping("/uploadAvatarImg")
    @ResponseBody
    public ResultDTO uploadAvatarImg(MultipartFile file, HttpServletRequest request) {

        // 从session获取用户信息
        User currentUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);

        // 判断用户信息是否为空，null即用户未登录
        if (currentUser == null) {
            return ResultDTO.errorOf("用户未登录，不能上传头像");
        }
        if (file == null){
            return ResultDTO.errorOf("文件不能为空");
        }

        return userService.uploadAvatarImg(file,currentUser);



    }

}
