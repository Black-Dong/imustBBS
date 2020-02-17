package top.codingdong.imustbbs.DTO;

import lombok.Data;
import top.codingdong.imustbbs.enums.StatusEnum;

/**
 * @author Dong
 * @date 2020/2/17 12:31
 */
@Data
public class ResultDTO <T> {

    private Integer code;
    private String message;

    private T data;

    private ResultDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultDTO(Integer code, String message, T t) {
        this.code = code;
        this.message = message;
        this.data = t;
    }


    public static ResultDTO warnOf(Integer code, String message){
        return new ResultDTO(code,message);
    }

    public static ResultDTO warnOf(StatusEnum statusEnum){
        return new ResultDTO(statusEnum.getCode(),statusEnum.getMessage());
    }

    public static ResultDTO success(){
        return new ResultDTO(200,"操作成功");
    }
    public static <T> ResultDTO successOf(T t){
        return new ResultDTO(200,"操作成功",t);
    }
}
