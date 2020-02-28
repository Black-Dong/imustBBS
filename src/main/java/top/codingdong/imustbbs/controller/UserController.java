package top.codingdong.imustbbs.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.codingdong.imustbbs.DTO.ResultDTO;
import top.codingdong.imustbbs.po.Topic;
import top.codingdong.imustbbs.po.User;
import top.codingdong.imustbbs.mapper.TopicMapper;
import top.codingdong.imustbbs.service.TopicService;
import top.codingdong.imustbbs.service.UserService;
import top.codingdong.imustbbs.util.Constants;
import top.codingdong.imustbbs.util.CryptographyUtil;
import top.codingdong.imustbbs.util.MailUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 用户控制器
 *
 * @author Dong
 * @date 2020/2/26 14:56
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Resource
    JavaMailSender mailSender;

    @PostMapping("/register")
    @ResponseBody
    public ResultDTO register(@Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultDTO.errorOf(bindingResult.getFieldError().getDefaultMessage());
        } else if (userService.findByUserName(user.getUserName()) != null) {
            return ResultDTO.errorOf("用户名已存在，请更换");
        } else if (userService.findByEmail(user.getEmail()) != null) {
            return ResultDTO.errorOf("邮箱已存在，请更换");
        } else {
            user.setRegistrationDate(new Date());
            user.setLatelyLoginTime(new Date());
            user.setHeadPortrait("tou.jpg");
            user.setPassword(CryptographyUtil.md5(user.getPassword()));
            userService.save(user);
            return ResultDTO.success();
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultDTO login(User user, HttpSession session) {

        if (StringUtils.isBlank(user.getUserName())) {
            return ResultDTO.errorOf("请输入用户名");
        } else if (StringUtils.isBlank(user.getPassword())) {
            return ResultDTO.errorOf("请输入密码");
        } else {

            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), CryptographyUtil.md5(user.getPassword()));
            try {
                // 登录验证
                subject.login(token);
                String userName = SecurityUtils.getSubject().getPrincipal().toString();
                User dbUser = userService.findByUserName(userName);
                if (dbUser.isOff()) {
                    subject.logout();
                    return ResultDTO.errorOf("该用户已封禁，请联系管理页");
                } else {
                    dbUser.setLatelyLoginTime(new Date());
                    userService.save(dbUser);
                    session.setAttribute(Constants.CURRENT_USER, dbUser);
                    return ResultDTO.success();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return ResultDTO.errorOf("用户名或密码错误");
            }
        }
    }

    /**
     * 发送邮件
     *
     * @param session
     * @return
     */
    @PostMapping("/sendEmail")
    @ResponseBody
    public ResultDTO sendEmail(String email, HttpSession session) {

        if (StringUtils.isBlank(email)) {
            return ResultDTO.errorOf("邮箱不能为空");
        }

        // 验证邮件是否存在
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResultDTO.errorOf("邮箱不存在");
        }

        String mailCode = MailUtil.getSixRandom();
        // 发送邮件
        // 创建消息
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("imust_bbs@126.com");
        message.setTo(email);
        message.setSubject("科大论坛-用户找回密码");
        message.setText("您本次的验证码是: " + mailCode);

        // 发送消息
        mailSender.send(message);
        System.err.println(mailCode);
        // 验证码存入session
        session.setAttribute(Constants.MAIL_CODE, mailCode);
        session.setAttribute(Constants.USER_ID, user.getUserId());

        return ResultDTO.success();
    }

    /**
     * 邮件验证码判断
     *
     * @param session
     * @return
     */
    @PostMapping("/checkYzm")
    @ResponseBody
    public ResultDTO checkYzm(String yzm, HttpSession session) {

        if (StringUtils.isBlank(yzm)) {
            return ResultDTO.errorOf("验证码不能为空");
        }
        String mailCode = (String) session.getAttribute(Constants.MAIL_CODE);
        Integer userId = (Integer) session.getAttribute(Constants.USER_ID);
        if (!yzm.equals(mailCode)) {
            return ResultDTO.errorOf("验证码错误");
        }

        User dbUser = userService.findById(userId);
        dbUser.setPassword(CryptographyUtil.md5(Constants.DEFAULT_PASSWORD));
        userService.save(dbUser);
        return ResultDTO.success();
    }

}