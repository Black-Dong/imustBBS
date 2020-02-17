package top.codingdong.imustbbs.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import top.codingdong.imustbbs.DTO.ResultDTO;
import top.codingdong.imustbbs.enums.StatusEnum;
import top.codingdong.imustbbs.model.User;
import top.codingdong.imustbbs.service.UserService;

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
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public String login(String account, String password,
                              Model model,
                              HttpServletRequest request){

        if (StringUtils.isBlank(account)){
            model.addAttribute("resultDTO",ResultDTO.warnOf(StatusEnum.ACCOUNT_IS_EMPTY));
            return "login";
        }else if (StringUtils.isBlank(password)){
            model.addAttribute("resultDTO",ResultDTO.warnOf(StatusEnum.PASSWORD_IS_EMPTY));
            return "login";
        }

        User user = userService.checkUser(account, password);


        if (user != null){
            // 删除密码存入session
            user.setPassword("");
            request.getSession().setAttribute("loginUser",user);
            model.addAttribute("resultDTO",ResultDTO.success());
            return "index";
        }else {
            model.addAttribute("resultDTO",ResultDTO.warnOf(StatusEnum.USER_NOT_FOUND));
            return "login";
        }
    }
}
