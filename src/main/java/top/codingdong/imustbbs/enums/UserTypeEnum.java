package top.codingdong.imustbbs.enums;

/**
 * @author Dong
 * @date 2020/2/17 15:44
 */
public enum UserTypeEnum {

    /*注册状态*/
    VIEWER(1),

    /*认证状态*/
    STUDENT(2),
    TEACHER(3),
    COMMUNITY(4),

    /*管理页状态*/
    ADMINISTRATOR(101),
    ;

    Integer type;

    UserTypeEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
