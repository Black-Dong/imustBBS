package top.codingdong.imustbbs.enums;

/**
 * @author Dong
 * @date 2020/2/17 15:44
 */
public enum UserTypeEnum {

    VIEWER(1),
    STUDENT(2),
    TEACHER(3),
    COMMUNITY(4),
    ;

    Integer type;

    UserTypeEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
