package top.codingdong.imustbbs.controller.own;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.codingdong.imustbbs.dto.CommentDto;
import top.codingdong.imustbbs.dto.PostDto;
import top.codingdong.imustbbs.dto.ResultDto;
import top.codingdong.imustbbs.enums.CommentEnum;
import top.codingdong.imustbbs.model.Comment;
import top.codingdong.imustbbs.model.User;
import top.codingdong.imustbbs.service.CommentService;
import top.codingdong.imustbbs.service.PostService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Dong
 * @date 2020/2/11 22:58
 */
@Controller
@RequestMapping("/own")
@Api(description = "评论")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @ResponseBody
    @PostMapping("/comment")
    @ApiOperation("新增评论")
    public Object comment(@RequestBody Comment comment,
                          HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        // 判断用户是否登录
        if (user == null) {
            return ResultDto.statusOf(CommentEnum.NOT_LOGIN);
        }
        // 判断评论是否为空
        if (comment == null || StringUtils.isEmpty(comment.getContent().trim())){
            return ResultDto.statusOf(CommentEnum.CONTENT_IS_EMPTY);
        }

        // 判断评论是否有父类
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            return ResultDto.statusOf(CommentEnum.PARENT_ID_WRONG);
        }

        // 判断评论类型是否存在
        if (comment.getType() == null || !CommentEnum.isExistType(comment.getType())) {
            return ResultDto.statusOf(CommentEnum.TYPE_WRONG);
        } else {

            // 查看评论所回复的父 是否还存在
            if (CommentEnum.REPLY_COMMENT.getType().equals(comment.getType())) {
                // 回复评论，查看父评论是否存在
                Comment parent_comment = commentService.getById(comment.getParentId());
                if (parent_comment == null) {
                    return ResultDto.statusOf(CommentEnum.PARENT_ID_WRONG);
                }
            } else {
                // 回复帖子，查看原帖子是否存在
                PostDto parent_post = postService.getById(comment.getParentId());
                if (parent_post == null) {
                    return ResultDto.statusOf(CommentEnum.PARENT_ID_WRONG);
                }
                postService.incCommentCount(parent_post);
            }
        }


        comment.setCommentator(user.getId().longValue());
        commentService.insert(comment);


        return ResultDto.statusOf(CommentEnum.SUCCESS);
    }


    @ResponseBody
    @GetMapping("/comment/{id}")
    @ApiOperation("获取某一评论的二级评论")
    public ResultDto<List<CommentDto>> comments(@PathVariable(name = "id")Long id){
        List<CommentDto> commentDtos = commentService.listReplyByIdAndType(id,CommentEnum.REPLY_COMMENT);
        return ResultDto.statusOfSuccess(commentDtos);
    }
}
