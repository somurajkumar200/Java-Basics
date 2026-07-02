package Util;
public class ValidationUtil {

    public static boolean isValidMobile(String mobile) {
        return mobile != null && mobile.matches("[6-9][0-9]{9}");
    }

    public static boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }
}