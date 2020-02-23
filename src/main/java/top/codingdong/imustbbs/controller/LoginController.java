package top.codingdong.imustbbs.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import top.codingdong.imustbbs.DTO.LoginDTO;
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

    @PostMapping("/login")
    @ApiOperation("用户登录")
    @ResponseBody
    public ResultDTO<User> login(@RequestBody LoginDTO loginDTO,
                                 HttpServletRequest request) {

        if (StringUtils.isBlank(loginDTO.getAccount())) {
            // 账号为空时
            return ResultDTO.warnOf(StatusEnum.ACCOUNT_IS_EMPTY);
        } else if (StringUtils.isBlank(loginDTO.getPassword())) {
            // 密码为空时
            return ResultDTO.warnOf(StatusEnum.PASSWORD_IS_EMPTY);
        }

        // 验证账号密码是否正确，返回根据账号密码查询的用户
        User dbUser = userService.checkUser(loginDTO.getAccount(), loginDTO.getPassword());


        if (dbUser != null) {
            // 删除密码存入session
            dbUser.setPassword("");
            request.getSession().setAttribute("loginUser", dbUser);

            // 管理员跳转管理后台页面
            return ResultDTO.successOf(dbUser);

        } else {
            // 账号或密码错误，用户未找到
            return ResultDTO.warnOf(StatusEnum.USER_NOT_FOUND);
        }
    }

    @GetMapping("/logout")
    @ResponseBody
    public ResultDTO logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return ResultDTO.success();
    }

}
