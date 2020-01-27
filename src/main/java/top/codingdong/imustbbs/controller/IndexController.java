package top.codingdong.imustbbs.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.codingdong.imustbbs.model.Post;
import top.codingdong.imustbbs.model.User;
import top.codingdong.imustbbs.service.PostService;
import top.codingdong.imustbbs.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Dong
 * @date 2020/1/24 0:30
 */
@Controller
@Api(description = "首页")
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping("/")
    @ApiOperation("跳转首页")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "pageNumber",defaultValue = "1")Integer pageSize,
                        @RequestParam(name = "size",defaultValue = "1")Integer size
                        ) {

        // 获取cookie，自动登录
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())){
                    String token = cookie.getValue();
                    User user = userService.findByToken(token);
                    if (user != null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        PageInfo<Post> pageInfo = postService.listPost(pageSize, size);
        model.addAttribute("pageInfo",pageInfo);

        return "index";
    }

}
