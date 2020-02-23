package top.codingdong.imustbbs.enums;

/**
 * @author Dong
 * @date 2020/2/17 12:34
 */
public enum StatusEnum {

    SUCCESS(200, "操作成功"),
    LOGINUSER_NOT_EXIST(201, "用户未登录"),

    /*登录状态*/
    ACCOUNT_IS_EMPTY(2001, "用户名或邮箱不能为空！"),
    PASSWORD_IS_EMPTY(2002, "密码不能为空！"),
    USER_NOT_FOUND(2003, "账号或密码错误！"),

    /*注册状态*/
    MISSING_REGISTER_INFORMETION(2011, "注册信息不完整，请检查后重试！"),
    DIFFERENT_PASSWORD(2012, "两次密码输入不一致请重新输入"),
    USERNAME_EXIST(2013, "用户名已存在"),
    USERNAME_IS_EMPTY(2014, "用户名不能为空"),
    EMAIL_EXIST(2015, "邮箱已存在"),
    EMAIL_OR_USERNAME_EXIST(2016, "邮箱或用户名已存在"),
    EMAIL_FORMAT_ERROR(2017, "邮箱格式不正确"),

    /*分类与标签状态*/
    CATEGORY_NAME_IS_EMPTY(2021, "名称不能为空"),
    CATEGORY_EXIST(2022, "分类已存在"),
    CATEGORY_NOT_EXIST(2023, "分类已不存在"),


    /*帖子状态*/
    TOPIC_TITLE_IS_EMPTY(2031,"帖子标题不能为空"),
    TOPIC_CONTENT_IS_EMPTY(2032,"帖子内容不能为空"),
    TOPIC_CATEGORY_IS_EMPTY(2033,"请选择帖子分类"),
    TOPIC_NOT_EXIST(2034,"帖子已不存在"),

    /*评论状态*/
    REPLY_CONTENT_IS_EMPTY(2041,"回复内容不能为空"),
    REPLY_TOPIC_NOT_EXIST(2042,"回复的帖子已不存在"),
    REPLY_COMMENT_NOT_EXIST(2043,"回复的评论已不存在"),
    ;

    Integer code;
    String message;

    StatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
