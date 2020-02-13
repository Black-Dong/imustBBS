package top.codingdong.imustbbs.enums;

/**
 * @author Dong
 * @date 2020/2/13 10:00
 */
public enum CommentEnum {

    // 评论父类
    REPLY_POST(1),
    REPLY_COMMENT(2),

    // 评论返回状态
    SUCCESS(200,"请求成功"),
    NOT_LOGIN(2000,"未登录用户不能进行评论，请登录后重试"),
    PARENT_ID_WRONG(20001,"未选择任何贴或评论进行回复，或原帖或评论已不存在"),
    TYPE_WRONG(20002,"回复类型不存在"),

    ;

    private Integer type;

    CommentEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }


    private String message;

    private Integer code;

    CommentEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
