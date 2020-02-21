package top.codingdong.imustbbs.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.codingdong.imustbbs.DTO.ResultDTO;
import top.codingdong.imustbbs.enums.StatusEnum;
import top.codingdong.imustbbs.mapper.ReplyMapper;
import top.codingdong.imustbbs.model.Reply;
import top.codingdong.imustbbs.model.User;
import top.codingdong.imustbbs.service.ReplyService;
import top.codingdong.imustbbs.service.TopicService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Dong
 * @date 2020/2/21 17:01
 */
//@Controller
@Api(tags = {"user_replay"})
@RestController
@RequestMapping("/user")
public class UserReplyController {


    @Autowired
    private ReplyService replyService;

    @Autowired
    private TopicService topicService;

    @PostMapping("/reply")
    @ApiOperation("新增顶级回复")
    public ResultDTO createReply(@RequestBody Reply reply,
                                 HttpServletRequest request) {

        User loginUser = (User) request.getSession().getAttribute("loginUser");
        /**
         * todo:
         *  给登录用户赋值，后期需要过滤器过滤
         */
        if (loginUser == null){
//            return ResultDTO.warnOf(StatusEnum.LOGINUSER_NOT_EXIST);
//            reply.setUserId(loginUser.getId());
//            reply.setUserName(loginUser.getUsername());
        }

        if (StringUtils.isBlank(reply.getContent())) {
            return ResultDTO.warnOf(StatusEnum.REPLY_CONTENT_IS_EMPTY);
        }
        if (reply.getTopicId() == null || topicService.findTopicById(reply.getTopicId()) == null){
            return ResultDTO.warnOf(StatusEnum.REPLY_TOPIC_NOT_EXIST);
        }


        return replyService.createReply(reply);
    }


}
