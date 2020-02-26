package top.codingdong.imustbbs.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author Dong
 * @date 2020/2/26 15:46
 */
public class CryptographyUtil {

    private final static String SALT = "imustBBS";

    public static String md5(String str){
        return new Md5Hash(str,SALT).toString();
    }

    public static void main(String[] args) {
        String password = "imustBBS";
        System.out.println(md5(password));
    }
}
