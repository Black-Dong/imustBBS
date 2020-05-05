package top.codingdong.imustbbs.service.impl;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.github.pagehelper.PageHelper;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;
import top.codingdong.imustbbs.DTO.RePasswordDTO;
import top.codingdong.imustbbs.DTO.ResultDTO;
import top.codingdong.imustbbs.mapper.UserMapper;
import top.codingdong.imustbbs.po.User;
import top.codingdong.imustbbs.service.UserService;
import top.codingdong.imustbbs.util.Constants;
import top.codingdong.imustbbs.util.CryptographyUtil;

import javax.xml.crypto.Data;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Dong
 * @date 2020/2/26 15:37
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByUsername(String userName) {
        return userMapper.selectUserByUsername(userName);
    }

    @Override
    public User selectByEmail(String email) {
        return userMapper.selectUserByEmail(email);
    }

    @Override
    public void save(User user) {

        if (user.getUserId() != null) {
            userMapper.updateByPrimaryKeySelective(user);
        } else {
            userMapper.insertSelective(user);
        }
    }

    @Override
    public User selectById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectAllmember(Integer pageNumber, Integer pageSize) {
        Example example = new Example(User.class);
        example.createCriteria()
                .andEqualTo("roleName", "会员");
        PageHelper.startPage(pageNumber, pageSize);
        return userMapper.selectByExample(example);
    }

    @Override
    public void disableUser(Integer id) {
        userMapper.disableUserById(id);
    }

    @Override
    public void unDisableUser(Integer userId) {
        userMapper.unDisableUserById(userId);
    }

    @Override
    public List<User> selectAdmins(Integer pageNumber, Integer pageSize) {
        Example example = new Example(User.class);
        example.createCriteria()
                .andEqualTo("roleName", "管理员");
        PageHelper.startPage(pageNumber, pageSize);
        return userMapper.selectByExample(example);
    }

    @Override
    public void authorizeAdmin(Integer userId) {

        userMapper.modifyRoleNameToAdmin(userId);
    }

    @Override
    public void deauthorizeAdmin(Integer userId) {
        userMapper.modifyRoleNameToMember(userId);
    }


    /**
     * 根据传入的用户名，模糊查询用户列表，只查询5个
     *
     * @param searchName
     * @return
     */
    @Override
    public List<User> listLikeUsername(String searchName) {


        PageHelper.startPage(1, 5);
        List<User> usersLikeUsername = userMapper.listLikeUsername(searchName);

        return usersLikeUsername;
    }

    @Override
    public User modifyBasicInformation(User user) {
        userMapper.updateByPrimaryKeySelective(user);
        return userMapper.selectByPrimaryKey(user.getUserId());
    }

    @Override
    public int rePassword(RePasswordDTO repasswordDTO) {
        Example example = new Example(User.class);
        example.createCriteria()
                .andEqualTo("userId", repasswordDTO.getUserId())
                .andEqualTo("password", CryptographyUtil.md5(repasswordDTO.getNowPassword()));
        User dbUser = userMapper.selectOneByExample(example);
        if (dbUser != null) {
            userMapper.modifyPasswordById(dbUser.getUserId(), CryptographyUtil.md5(repasswordDTO.getNewPassword()));
            return 1;
        }
        return 0;
    }

    @Override
    public ResultDTO uploadAvatarImg(MultipartFile file, User currentUser) {
        String backUrl;
        try {
            backUrl = uploadAvatarImg(file);
        }catch (Exception e){
            e.printStackTrace();
            return ResultDTO.errorOf("上传图片错误！");
        }
        currentUser.setAvatar(backUrl);
        userMapper.uploadAvatarPath(currentUser);
        return ResultDTO.success("上传成功");
    }

    /**
     * @param
     * @return
     * @Description 上传用户头像
     * @author Dong
     * @date 2020/5/5 11:55
     */
    private String uploadAvatarImg(MultipartFile file) {

        // 创建OSSClient实例。
        OSS ossClient = null;
        try {

            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(Constants.END_POINT, Constants.ACCESS_KEY_ID, Constants.ACCESS_KEY_SECRET);

            //获取上传文件输入流
            InputStream inputStream = file.getInputStream();

            //获取文件名称(使用uuid避免重名文件覆盖问题)
            String originalFilename = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String fileName = uuid + originalFilename;
            //把文件按照日期进行分类(日期工具类使用的是joda-time，也可以使用java自带的工具类)
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String dataPath = simpleDateFormat.format(date);
//            String dataPath = new DateTimeLiteralExpression.DateTime().toString("yyyy/MM/dd");

            // 日期构成的文件夹路径和文件名称合并
            fileName = dataPath + "/" + fileName;

            //调用oss方法实现上传
            //第一个参数 Bucket名称
            //第二个参数 上传到oss文件路径和文件名称 a/b/c/123.jpg
            //第三个参数 上传文件输入流
            ossClient.putObject(Constants.BUCKET_NAME, fileName, inputStream);

            //设置返回的url有效期为10年
            Date expiration = new Date(System.currentTimeMillis() + 10 * 365 * 24 * 60 * 60 * 1000);
            GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(Constants.BUCKET_NAME, fileName, HttpMethod.GET);
            req.setExpiration(expiration);

            //回显url
            URL url = ossClient.generatePresignedUrl(req);

            return url.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
