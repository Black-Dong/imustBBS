package top.codingdong.imustbbs.util;

import java.util.Random;

/**
 * @author Dong
 * @date 2020/2/26 19:24
 */
public class MailUtil {

    public static String getSixRandom(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

}
