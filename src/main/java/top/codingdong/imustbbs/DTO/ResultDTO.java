package top.codingdong.imustbbs.DTO;

import lombok.Data;

import java.util.Stack;

/**
 * @author Dong
 * @date 2020/2/26 14:59
 */
@Data
public class ResultDTO<T> {

    private Boolean status;

    private String message;

    private T data;

    private ResultDTO(Boolean status) {
        this.status = status;
    }

    private ResultDTO(Boolean status, T data) {
        this.status = status;
        this.data = data;
    }

    public ResultDTO(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ResultDTO success() {
        return new ResultDTO(true);
    }

    public static ResultDTO success(String message) {
        return new ResultDTO(true, message);
    }

    public static <T> ResultDTO success(T data) {
        return new ResultDTO(true, data);
    }

    public static ResultDTO errorOf(String message) {
        return new ResultDTO(false, message);
    }
}
