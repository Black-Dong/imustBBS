package top.codingdong.imustbbs.dto;

import lombok.Data;
import top.codingdong.imustbbs.exception.ImustBBSError;

/**
 * @author Dong
 * @date 2020/2/13 9:40
 */
@Data
public class ResultDto<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态
     */
    private String message;

    private T data;

    private ResultDto(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResultDto statusOf(ImustBBSError imustBBSError) {
        return new ResultDto(imustBBSError.getCode(), imustBBSError.getMessage());
    }

    public static ResultDto statusOf(Integer code, String message) {
        return new ResultDto(code, message);
    }

    public static ResultDto statusOfSuccess() {
        return new ResultDto(200, "请求成功");
    }

    public static <T> ResultDto statusOfSuccess(T t) {
        ResultDto resultDto = new ResultDto(200, "请求成功");
        resultDto.setData(t);
        return resultDto;
    }
}
