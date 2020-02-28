package top.codingdong.imustbbs.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.codingdong.imustbbs.mapper.ReplyMapper;
import top.codingdong.imustbbs.po.Reply;
import top.codingdong.imustbbs.po.User;
import top.codingdong.imustbbs.util.Constants;

import javax.servlet.http.HttpSession;

/**
 * @author Dong
 * @date 2020/2/28 17:41
 */
@Controller
@RequestMapping("/user")
public class UserReplyController {

    @Autowired
    private ReplyMapper replyMapper;

    @PostMapping("/reply")
    public String reply(Reply reply, HttpSession session) {
        User currentUser = (User) session.getAttribute(Constants.CURRENT_USER);

        reply.setUserId(currentUser.getUserId());
        reply.setCreateTime(System.currentTimeMillis());
        reply.setReplyType(1);

        replyMapper.insertSelective(reply);
        return "redirect:/detail/" + reply.getTopicId();
    }
}
