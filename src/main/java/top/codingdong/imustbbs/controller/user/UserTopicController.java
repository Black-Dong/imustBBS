package top.codingdong.imustbbs.controller.user;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.codingdong.imustbbs.po.Category;
import top.codingdong.imustbbs.po.Topic;
import top.codingdong.imustbbs.po.User;
import top.codingdong.imustbbs.service.CategoryService;
import top.codingdong.imustbbs.service.TopicService;
import top.codingdong.imustbbs.service.UserService;
import top.codingdong.imustbbs.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 用户帖子控制类
 *
 * @author Dong
 * @date 2020/2/27 17:57
 */
@Controller
@RequestMapping("/user")
public class UserTopicController {

    @Autowired
    TopicService topicService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;


    /**
     * 跳转帖子发布页面
     *
     * @param model
     * @return
     */
    @GetMapping("/publishTopic")
    public String publishTopic(Model model) {
        List<Category> categories = categoryService.selectAll();
        model.addAttribute("categorys", categories);
        return "user/publishTopic";
    }

    /**
     * 帖子上传图片
     *
     * @return
     */
    @PostMapping("/uploadImage")
    @ResponseBody
    public Map<String, Object> uploadImage(MultipartFile file, HttpServletRequest request) throws IOException {

        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        if (file != null) {
            // 服务器图片存储路径
            String webapp = request.getServletContext().getRealPath("/");
//            String webapp = "/";
            try {
                // 文件名称，带路径
                String originalFilename = file.getOriginalFilename();
                // edge出错
//                String fileName = originalFilename.substring(originalFilename.lastIndexOf("/"));
                String imgSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
                // 保存服务器的图片名称，不带路径
                String serverFileName = UUID.randomUUID().toString().replaceAll("-", "") + imgSuffix;
                // 保存服务器的图片的路径 + 文件名称
                serverFileName = "/upload/" + serverFileName;

                System.out.println(serverFileName);
                // 图片的在服务器上面的物理路径
                File destFile = new File(webapp, serverFileName);
                // 生成upload目录
                File parentFile = destFile.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();// 自动生成upload目录
                }
                // 把上传的临时图片，复制到当前项目的webapp路径
                FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(destFile));

                map = new HashMap<>();
                map2 = new HashMap<>();
                //0表示成功，1失败
                map.put("code", 0);
                //提示消息
                map.put("msg", "上传成功");
                map.put("data", map2);
                //图片url
                map2.put("src", serverFileName);
                //图片名称，这个会显示在输入框里
                map2.put("title", serverFileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;

    }

    /**
     * 帖子发布与修改
     *
     * @param topic
     * @param session
     * @return
     */
    @PostMapping("/publishTopic")
    public String publishTopic(Topic topic, HttpSession session) {

        // 设置帖子最后更新时间
        topic.setUpdateTime(System.currentTimeMillis());

        // 设置发帖人
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        topic.setUserId(user.getUserId().longValue());

        System.err.println(topic);

        // 根据是否有Id，判断是新增还是修改
        if (topic.getId() != null) {
            // 修改帖子，设置帖子最后更新时间
            topic.setUpdateTime(System.currentTimeMillis());
            topicService.update(topic);
        } else {
            // 新增帖子，设置帖子创建时间 和 最后回复时间
            topic.setCreateTime(System.currentTimeMillis());
            topic.setLastReplyTime(System.currentTimeMillis());
            topicService.save(topic);
        }

        // 跳转到帖子详情页
        return "redirect:/detail/" + topic.getId();
    }

    /**
     * 跳转帖子修改页面
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/editTopic/{id}")
    public String publishTopic(@PathVariable Integer id, Model model) {
        Topic dbTopic = topicService.findById(id);
        model.addAttribute("dbTopic", dbTopic);
        List<Category> categories = categoryService.selectAll();
        model.addAttribute("categorys", categories);

        return "user/editTopic";
    }
}
