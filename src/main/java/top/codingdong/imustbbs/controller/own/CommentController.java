package top.codingdong.imustbbs.controller.own;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.codingdong.imustbbs.model.Comment;
import top.codingdong.imustbbs.model.User;
import top.codingdong.imustbbs.service.CommentService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Dong
 * @date 2020/2/11 22:58
 */
@Controller
@RequestMapping("/own")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    @ResponseBody
    public Object comment(@RequestBody Comment comment,
                          HttpServletRequest request){
        comment.setCreateTime(System.currentTimeMillis());
        comment.setUpdateTime(System.currentTimeMillis());

        User user = (User) request.getSession().getAttribute("user");
        comment.setCommentator(user.getId().longValue());
        commentService.insert(comment);

        return null;
    }
}
