package top.codingdong.imustbbs.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Dong
 * @date 2020/2/17 13:40
 */
public class MD5Utils {

    public static String code(String string){


        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(string.getBytes());
            byte[] digest = md5.digest();
            int i;
            StringBuffer stringBuffer = new StringBuffer("");
            for (int j = 0; j < digest.length; j++) {
                i = digest[j];
                if (i < 0){
                    i += 256;
                }
                if (i < 16){
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i));
            }

            /*32位加密*/
            return stringBuffer.toString();

            /*16位加密*/
//            return stringBuffer.toString().substring(8,24);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }
}
