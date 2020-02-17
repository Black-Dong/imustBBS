package top.codingdong.imustbbs.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Dong
 * @date 2020/2/17 19:30
 */
public class CheckUtils {

    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String checkEmail = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(checkEmail);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public static boolean checkMobileNumber(String mobileNumber) {
        boolean flag = false;
        try {
            // Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
            String checkMobileNumber = "^1[345789]\\d{9}$";
            Pattern regex = Pattern.compile(checkMobileNumber);
            Matcher matcher = regex.matcher(mobileNumber);
            flag = matcher.matches();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;

        }
        return flag;
    }
}
