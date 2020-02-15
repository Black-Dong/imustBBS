package top.codingdong.imustbbs.enums;

import top.codingdong.imustbbs.exception.ImustBBSError;

/**
 * @author Dong
 * @date 2020/2/13 10:00
 */
public enum CommentEnum implements ImustBBSError {

    // 评论父类
    REPLY_POST(1),
    REPLY_COMMENT(2),

    // 评论返回状态
    SUCCESS(200,"请求成功"),
    NOT_LOGIN(2000,"未登录用户不能进行评论，请登录后重试"),
    PARENT_ID_WRONG(20001,"未选择任何贴或评论进行回复，或原帖或评论已不存在"),
    TYPE_WRONG(20002,"回复类型不存在"),
    CONTENT_IS_EMPTY(20003,"回复内容不能为空"),

    ;

    private Integer type;

    private String message;

    private Integer code;

    CommentEnum(Integer type) {
        this.type = type;
    }

    CommentEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getType() {
        return type;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }


    public static boolean isExistType(Integer type){

        for (CommentEnum commentEnum : CommentEnum.values()) {
            if (type != null && type.equals(commentEnum.getType())){
                return true;
            }

        }

        return false;
    }
}
