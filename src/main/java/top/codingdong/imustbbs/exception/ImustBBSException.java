package top.codingdong.imustbbs.exception;

import top.codingdong.imustbbs.enums.ImustBBSErrorEnum;

/**
 * @author Dong
 * @date 2020/2/11 19:55
 */
public class ImustBBSException extends RuntimeException {

    private String message;

    private Integer code;

    public ImustBBSException(ImustBBSErrorEnum errorEnum) {
        this.message = errorEnum.getMessage();
        this.code = errorEnum.getCode();
    }


    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
