package Util;
import java.util.Random;

public class OTPGenerator {
    public static String generate() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }
}