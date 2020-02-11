package top.codingdong.imustbbs.exception;

/**
 * @author Dong
 * @date 2020/2/11 20:16
 */
public enum ImustBBSErrorCodeEnum implements ImustBBSErrorCode {

    POST_NOT_FOUND("帖子已删除或不存在");


    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    ImustBBSErrorCodeEnum(String message) {
        this.message = message;
    }
}
