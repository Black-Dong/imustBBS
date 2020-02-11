package top.codingdong.imustbbs.exception;

/**
 * @author Dong
 * @date 2020/2/11 19:55
 */
public class ImustBBSException extends RuntimeException {

    private String message;

    public ImustBBSException(String message) {
        super(message);
        this.message = message;
    }

    public ImustBBSException(ImustBBSErrorCodeEnum errorCodeEnum) {
        this(errorCodeEnum.getMessage());
    }


    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
