package Util;

public class IdGenerator {

    private static int user = 1000;
    private static int acc = 100;
    private static int txn = 1000;

    public static String userId() {
        return "U" + (++user);
    }

    public static String accountId() {
        return "A" + (++acc);
    }

    public static String txnId() {
        return "T" + (++txn);
    }
}