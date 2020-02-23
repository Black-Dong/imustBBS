package top.codingdong.imustbbs.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.codingdong.imustbbs.DTO.RegisterDTO;
import top.codingdong.imustbbs.DTO.ResultDTO;
import top.codingdong.imustbbs.enums.StatusEnum;
import top.codingdong.imustbbs.model.User;
import top.codingdong.imustbbs.service.UserService;
import top.codingdong.imustbbs.util.CheckUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Dong
 * @date 2020/2/23 12:04
 */
@Controller
@Api(tags = {"register"})
public class RegisterController {

    @Autowired
    private UserService userService;


    @GetMapping("/register.html")
    @ApiOperation("跳转注册页")
    public String register() {
        return "register";
    }

    @GetMapping("/checkUsername")
    @ApiOperation("用户名验证")
    @ResponseBody
    public ResultDTO checkUsernameExist(@RequestParam String username) {
        if (StringUtils.isBlank(username)){
            return ResultDTO.warnOf(StatusEnum.MISSING_REGISTER_INFORMETION);
        }
        User dbUser = userService.selectUserByUserName(username);
        if (dbUser != null){
            return ResultDTO.warnOf(StatusEnum.USERNAME_EXIST);
        }
        return ResultDTO.success();
    }

    @GetMapping("/checkEmail")
    @ApiOperation("邮箱验证")
    @ResponseBody
    public ResultDTO checkEmailExist(@RequestParam String email) {
        if (StringUtils.isBlank(email)){
            return ResultDTO.warnOf(StatusEnum.MISSING_REGISTER_INFORMETION);
        }
        User dbUser = userService.selectUserByEmail(email);
        if (dbUser != null){
            return ResultDTO.warnOf(StatusEnum.EMAIL_EXIST);
        }
        return ResultDTO.success();
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    @ResponseBody
    public ResultDTO register(@RequestBody RegisterDTO registerDTO,
                              HttpServletRequest request) {

        User user = new User();
        // 判断用户名、邮箱、密码、重复密码是否为空
        if (StringUtils.isAnyBlank(registerDTO.getUsername(), registerDTO.getEmail(), registerDTO.getRegisterPassword(), registerDTO.getRepeatPassword())) {
            // 有空时，密码置空
            return ResultDTO.warnOf(StatusEnum.MISSING_REGISTER_INFORMETION);
        }
        user.setUsername(registerDTO.getUsername());

        // 判断邮箱格式是否正确
        if (!CheckUtils.checkEmail(registerDTO.getEmail())) {
            return ResultDTO.warnOf(StatusEnum.EMAIL_FORMAT_ERROR);
        }
        user.setEmail(registerDTO.getEmail());

        // 判断密码与重复密码是否一致
        if (!StringUtils.equals(registerDTO.getRegisterPassword(), registerDTO.getRepeatPassword())) {
            return ResultDTO.warnOf(StatusEnum.DIFFERENT_PASSWORD);
        }
        user.setPassword(registerDTO.getRegisterPassword());

        // 判断邮箱或用户名是否已经存在，返回状态 二次验证
        ResultDTO<User> resultDTO = userService.checkEmailOrUsernameExist(user);
        // 查看状态是否为成功
        if (!(StatusEnum.SUCCESS.getCode()).equals(resultDTO.getCode())) {
            // 该邮箱已有用户注册，密码置空返回注册页
            return resultDTO;
        }

        // 注册用户，并返回注册用户
        User registerUser = userService.registerUser(user);

        // 将注册的用户加入session
        registerUser.setPassword("");
        request.getSession().setAttribute("loginUser", registerUser);

        // 操作成功状态
        return ResultDTO.success();
    }

}
