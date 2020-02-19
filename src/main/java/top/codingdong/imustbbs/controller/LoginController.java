package top.codingdong.imustbbs.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import top.codingdong.imustbbs.DTO.ResultDTO;
import top.codingdong.imustbbs.enums.StatusEnum;
import top.codingdong.imustbbs.enums.UserTypeEnum;
import top.codingdong.imustbbs.model.User;
import top.codingdong.imustbbs.service.UserService;
import top.codingdong.imustbbs.util.CheckUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Dong
 * @date 2020/2/17 9:33
 */
@Controller
@Api(tags = {"login"})
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login.html")
    @ApiOperation("跳转登录页")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public String login(String account, String password,
                        Model model,
                        HttpServletRequest request) {

        if (StringUtils.isBlank(account)) {
            // 账号为空时
            model.addAttribute("resultDTO", ResultDTO.warnOf(StatusEnum.ACCOUNT_IS_EMPTY));
            return "login";
        } else if (StringUtils.isBlank(password)) {
            // 密码为空时
            model.addAttribute("resultDTO", ResultDTO.warnOf(StatusEnum.PASSWORD_IS_EMPTY, account));
            return "login";
        }

        // 验证账号密码是否正确，返回根据账号密码查询的用户
        User user = userService.checkUser(account, password);


        if (user != null) {
            // 删除密码存入session
            user.setPassword("");
            request.getSession().setAttribute("loginUser", user);
            model.addAttribute("resultDTO", ResultDTO.success());
            // 判断管理员身份
            if (UserTypeEnum.ADMINISTRATOR.getType().equals(user.getType())) {
                // 管理员跳转管理后台页面
                return "index-admin";
            }
            // 操作成功
            return "index";
        } else {
            // 账号或密码错误，用户未找到
            model.addAttribute("resultDTO", ResultDTO.warnOf(StatusEnum.USER_NOT_FOUND));
            return "login";
        }
    }

    @GetMapping("/register.html")
    @ApiOperation("跳转注册页")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public String register(User user, String repeatPassword,
                           Model model,
                           HttpServletRequest request) {

        // 判断昵称、邮箱、密码、重复密码是否为空
        if (StringUtils.isAnyBlank(user.getNickName(), user.getEmail(), user.getPassword(), repeatPassword)) {
            // 有空时，密码置空
            user.setPassword("");
            model.addAttribute("resultDTO", ResultDTO.warnOf(StatusEnum.MISSING_REGISTER_INFORMETION, user));
            return "register";
        }

        // 判断邮箱格式是否正确
        if (!CheckUtils.checkEmail(user.getEmail())) {
            user.setPassword("");
            model.addAttribute("resultDTO", ResultDTO.warnOf(StatusEnum.EMAIL_FORMAT_ERROR, user));
            return "register";
        }

        // 判断密码与重复密码是否一致
        if (!StringUtils.equals(user.getPassword(), repeatPassword)) {
            user.setPassword("");
            model.addAttribute("resultDTO", ResultDTO.warnOf(StatusEnum.DIFFERENT_PASSWORD, user));
            return "register";
        }

        // 判断邮箱是否已经存在，返回状态
        ResultDTO<User> resultDTO = userService.checkEmailExist(user);
        // 查看状态是否为成功
        if (!(StatusEnum.SUCCESS.getCode()).equals(resultDTO.getCode())) {
            // 该邮箱已有用户注册，密码置空返回注册页
            user.setPassword("");
            resultDTO.setData(user);
            model.addAttribute("resultDTO", resultDTO);
            return "register";
        }

        // 注册用户，并返回注册用户
        User registerUser = userService.registerUser(user);

        // 将注册的用户加入session
        registerUser.setPassword("");
        request.getSession().setAttribute("loginUser", registerUser);

        // 操作成功状态
        model.addAttribute("ResultDTO", ResultDTO.success());

        return "index";
    }

}
