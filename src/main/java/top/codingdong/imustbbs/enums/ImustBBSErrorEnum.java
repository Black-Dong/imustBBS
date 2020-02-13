package top.codingdong.imustbbs.enums;

import top.codingdong.imustbbs.exception.ImustBBSError;

/**
 * @author Dong
 * @date 2020/2/11 20:16
 */
public enum ImustBBSErrorEnum implements ImustBBSError {

    NOT_LOGIN(2000, "当前操作需要登录，请登录后重试"),
    POST_NOT_FOUND(2001, "帖子已删除或不存在"),
    POST_UPDATE_ERROR(2002, "更新错误！！！"),
    ERROR_4XX(2004, "请求错误，请确认后重试。"),
    ERROR_5XX(2005, "服务器错误，请稍后重试！！！"),
    ERROR_OTHER(2006, "未知错误！！！"),
    ;


    private String message;

    private Integer code;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }


    ImustBBSErrorEnum(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
}
