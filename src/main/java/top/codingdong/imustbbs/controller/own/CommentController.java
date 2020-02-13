package top.codingdong.imustbbs.controller.own;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.codingdong.imustbbs.dto.CommentDto;
import top.codingdong.imustbbs.enums.CommentEnum;
import top.codingdong.imustbbs.model.Comment;
import top.codingdong.imustbbs.model.Post;
import top.codingdong.imustbbs.model.User;
import top.codingdong.imustbbs.service.CommentService;
import top.codingdong.imustbbs.service.PostService;

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

    @Autowired
    private PostService postService;

    @PostMapping("/comment")
    @ResponseBody
    public Object comment(@RequestBody Comment comment,
                          HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        // 判断用户是否登录
        if (user == null) {
            return CommentDto.resultOf(CommentEnum.NOT_LOGIN);
        }

        // 判断评论是否有父类
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            return CommentDto.resultOf(CommentEnum.PARENT_ID_WRONG);
        }

        // 判断评论类型是否存在
        if (comment.getType() == null || !CommentDto.isExistType(comment.getType())) {
            return CommentDto.resultOf(CommentEnum.TYPE_WRONG);
        } else {

            // 查看评论所回复的父 是否还存在
            if (CommentEnum.REPLY_COMMENT.getType().equals(comment.getType())) {
                // 回复评论，查看父评论是否存在
                Comment parent_comment = commentService.getById(comment.getParentId());
                if (parent_comment == null) {
                    return CommentDto.resultOf(CommentEnum.PARENT_ID_WRONG);
                }
            } else {
                // 回复帖子，查看原帖子是否存在
                Post parent_post = postService.getById(comment.getParentId());
                if (parent_post == null) {
                    return CommentDto.resultOf(CommentEnum.PARENT_ID_WRONG);
                }
                postService.incCommentCount(parent_post);
            }
        }


        comment.setCommentator(user.getId().longValue());
        commentService.insert(comment);


        return CommentDto.resultOf(CommentEnum.SUCCESS);
    }

/*    public void comment(){
        // 判断类型是否存在
        if (CommentEnum.REPLY_COMMENT.getType().equals(comment.getType())){
            //回复评论
        }else if (CommentEnum.REPLY_POST.getType().equals(comment.getType())){
            //回复帖子

        }else {
            // 回复类型不存在
        }
    }*/
}
