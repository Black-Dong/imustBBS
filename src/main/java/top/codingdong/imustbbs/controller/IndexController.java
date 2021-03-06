package top.codingdong.imustbbs.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.codingdong.imustbbs.DTO.ResultDTO;
import top.codingdong.imustbbs.po.Category;
import top.codingdong.imustbbs.po.Topic;
import top.codingdong.imustbbs.service.CategoryService;
import top.codingdong.imustbbs.service.TopicService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页控制类
 *
 * @author Dong
 * @date 2020/2/26 13:15
 */
@Controller
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TopicService topicService;

    /**
     * 跳转首页
     *
     * @return
     */
    @GetMapping("/")
    public String index(Model model) {

        // 查询标签中的分类
        List<Category> categories = categoryService.selectNavCategory();
        model.addAttribute("categories", categories);

        // 查询更多分类，不在标签中的分类
//        List<Category> moreCategorys = categoryService.selectMoreCategorys();

        // 查询帖子列表
        List<Topic> topics = topicService.listAndUserAndCategory(1, 10);
        PageInfo<Topic> pageInfo = PageInfo.of(topics);
        model.addAttribute("pageInfo", pageInfo);

        // 激活分类为 0 （首页）
        model.addAttribute("activeCategory", 0);

        // 返回首页
        return "index";
    }


    /**
     * 首页，右侧
     *
     * @param model
     * @return
     */
    @GetMapping("/index_right")
    public @ResponseBody
    ResultDTO index_right(Model model) {

        // 置顶的 10条帖子信息 按时间倒序 作为首页右侧置顶帖子列表
        List<Topic> topTopics = topicService.listTopTopics(1, 10);


        // 精品的 10条帖子信息 按时间倒序 作为首页右侧置顶帖子列表
        List<Topic> boutiqueTopics = topicService.listBoutiqueTopics(1, 10);

        // 返回数据
        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("topTopics", topTopics);
        dataMap.put("boutiqueTopics", boutiqueTopics);
        return ResultDTO.success(dataMap);
    }


    /**
     * 跳转登录页
     *
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    /**
     * 跳转注册页
     *
     * @return
     */
    @GetMapping("/register")
    public String register() {
        return "user/register";
    }

    /**
     * 跳转密码找回页
     *
     * @return
     */
    @GetMapping("/findPassword")
    public String findPassword() {
        return "user/findPassword";
    }


    /**
     * 跳转搜索页
     *
     * @param title
     * @param model
     * @return
     */
    @RequestMapping("/search/{pageNumber}")
    public String search(String title, @PathVariable(name = "pageNumber") Integer pageNumber, Model model) {

        if ("".equals(title)) {
            return "search";
        }

        model.addAttribute("searchTitle", title);

        List<Topic> likeTopics = topicService.listLikeTitle(title, pageNumber);

        PageInfo<Topic> pageInfo = PageInfo.of(likeTopics);

        model.addAttribute("pageInfo", pageInfo);

        return "search";
    }

    /**
     * @param
     * @return
     * @Description 跳转更多分类
     * @author Dong
     * @date 2020/5/5 14:32
     */
    @GetMapping("/moreCategorys/{id}/{pageNum}")
    public String moreCategorys(@PathVariable Integer id, Model model,
                                @PathVariable Integer pageNum,
                                @RequestParam(defaultValue = "10") Integer pageSize) {

        // 查询所有分类名称
        List<Category> categories = categoryService.selectALLCategory();
        model.addAttribute("categories", categories);

        List<Topic> topics = null;
        if (id != 0){
            // 当前激活的分类id
            model.addAttribute("activeCategory", id);

            // 根据分类id查询 该分类 下的所有帖子
            topics = topicService.listAndUserAndCategoryByCategoryId(pageNum, pageSize, id);

            // 将贴子放入PageInfo返回
            PageInfo<Topic> pageInfo = PageInfo.of(topics);
            model.addAttribute("pageInfo", pageInfo);
        }


        return "moreCategorys";
    }
}
