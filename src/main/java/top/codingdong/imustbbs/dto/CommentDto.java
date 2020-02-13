package top.codingdong.imustbbs.dto;

import lombok.Data;
import top.codingdong.imustbbs.enums.CommentEnum;

/**
 * @author Dong
 * @date 2020/2/13 9:40
 */
@Data
public class CommentDto {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态
     */
    private String message;

    private CommentDto(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static CommentDto resultOf(CommentEnum commentEnum) {
        return new CommentDto(commentEnum.getCode(), commentEnum.getMessage());
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
