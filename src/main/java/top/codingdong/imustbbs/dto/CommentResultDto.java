package top.codingdong.imustbbs.dto;

import lombok.Data;
import top.codingdong.imustbbs.enums.CommentEnum;

/**
 * @author Dong
 * @date 2020/2/13 9:40
 */
@Data
public class CommentResultDto {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态
     */
    private String message;

    private CommentResultDto(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static CommentResultDto resultOf(CommentEnum commentEnum) {
        return new CommentResultDto(commentEnum.getCode(), commentEnum.getMessage());
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
